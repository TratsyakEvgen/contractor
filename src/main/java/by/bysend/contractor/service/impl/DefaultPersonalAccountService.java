package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.user.PersonalAccountDTO;
import by.bysend.contractor.mapper.PersonalAccountMapper;
import by.bysend.contractor.model.PersonalAccount;
import by.bysend.contractor.model.entity.*;
import by.bysend.contractor.repository.AccountRepository;
import by.bysend.contractor.repository.ClientRepository;
import by.bysend.contractor.repository.OrderRepository;
import by.bysend.contractor.service.PersonalAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class DefaultPersonalAccountService implements PersonalAccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final PersonalAccountMapper personalAccountMapper;

    @Override
    public PersonalAccountDTO getPersonalAccount(long userId) {
        User user = new User().setUserId(userId);
        List<Account> accounts = accountRepository.findAllByUser(user);
        List<Client> clients = clientRepository.findAllByUser(user);
        BigDecimal totalReward = getTotalReward(clients);
        long meetingCount = getMeetingCount(clients);
        long ordersCount = getOrdersCount(clients);

        PersonalAccount personalAccount = new PersonalAccount().setAccounts(accounts)
                .setOrdersCount(ordersCount)
                .setMeetingsCount(meetingCount)
                .setReward(totalReward);

        return personalAccountMapper.getPersonalAccountDto(personalAccount);
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
                .map(Reward::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
