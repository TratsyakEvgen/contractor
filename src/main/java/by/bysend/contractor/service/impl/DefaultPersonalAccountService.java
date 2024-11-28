package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.response.ResponsePersonalAccount;
import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.mapper.PersonalAccountMapper;
import by.bysend.contractor.model.PersonalAccount;
import by.bysend.contractor.model.entity.Account;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.model.entity.Order;
import by.bysend.contractor.model.entity.Reward;
import by.bysend.contractor.repository.AccountRepository;
import by.bysend.contractor.repository.ClientRepository;
import by.bysend.contractor.repository.OrderRepository;
import by.bysend.contractor.repository.UserRepository;
import by.bysend.contractor.service.PersonalAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultPersonalAccountService implements PersonalAccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final PersonalAccountMapper personalAccountMapper;

    @Override
    public ResponsePersonalAccount getPersonalAccount(long userId) {
        checkIfUserExists(userId);

        List<Client> clients = clientRepository.findAllByUserId(userId);
        BigDecimal totalReward = getTotalReward(clients);
        long meetingCount = getMeetingCount(clients);
        long ordersCount = getOrdersCount(clients);
        List<Account> accounts = accountRepository.findAllByUserId(userId);

        PersonalAccount personalAccount = new PersonalAccount().setAccounts(accounts)
                .setOrdersCount(ordersCount)
                .setMeetingsCount(meetingCount)
                .setReward(totalReward);

        return personalAccountMapper.toResponsePersonalAccount(personalAccount);
    }

    private void checkIfUserExists(long userId) {
        userRepository.findById(userId)
                .orElseThrow(() ->
                        new ServiceException(String.format("User with id %d not found", userId), ErrorCode.ENTITY_NOT_EXISTS)
                );
    }

    private long getOrdersCount(List<Client> clients) {
        return clients.stream()
                .mapToLong(client -> client.getOrders().size())
                .sum();
    }

    private long getMeetingCount(List<Client> clients) {
        return clients.stream()
                .mapToLong(client -> client.getMeetings().size())
                .sum();
    }

    private BigDecimal getTotalReward(List<Client> clients) {
        return orderRepository.findAllByClientIn(clients)
                .stream()
                .map(Order::getReward)
                .filter(Objects::nonNull)
                .map(Reward::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
