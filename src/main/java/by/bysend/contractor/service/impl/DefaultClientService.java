package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.client.PagebaleUserClientsFilterDTO;
import by.bysend.contractor.dto.client.UserClientsDTO;
import by.bysend.contractor.dto.page.PageDTO;
import by.bysend.contractor.mapper.ClientMapper;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.repository.ClientRepository;
import by.bysend.contractor.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class DefaultClientService implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public PageDTO<UserClientsDTO> getUserClients(PagebaleUserClientsFilterDTO filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        Page<Client> clientPage = clientRepository.findAllUseFilter(filter, pageable);
        List<Long> idList = clientPage.stream()
                .map(Client::getClientId)
                .toList();
        List<Client> clients = clientRepository.findAllByClientIdIn(idList);
        PageImpl<Client> page = new PageImpl<>(clients, pageable, clientPage.getTotalElements());
        return clientMapper.getPageDTO(page);
    }
}
