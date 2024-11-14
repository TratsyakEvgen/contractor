package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.request.ClientFilter;
import by.bysend.contractor.dto.response.ResponseClient;
import by.bysend.contractor.mapper.ClientMapper;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.repository.ClientRepository;
import by.bysend.contractor.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultClientService implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Page<ResponseClient> getUserClients(long userId, Pageable pageable, ClientFilter clientFilter) {
        Page<Client> clientPage = clientRepository.findAllByUserUseFilter(userId, pageable, clientFilter);
        List<Long> listId = clientPage.stream()
                .map(Client::getId)
                .toList();
        Map<Long, Client> clientMap = clientRepository.findAllByIdIn(listId)
                .stream()
                .collect(Collectors.toMap(Client::getId, Function.identity()));
        return clientPage.map(Client::getId)
                .map(clientMap::get)
                .map(clientMapper::getResponseClient);
    }
}
