package by.bysend.contractor.service.impl;

import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.repository.ClientRepository;
import by.bysend.contractor.service.ClientEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DefaultClientEntityService implements ClientEntityService {
    private final ClientRepository clientRepository;

    @Override
    public Client getClient(long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ServiceException(
                        String.format("Client with id %d not found", clientId), ErrorCode.ENTITY_NOT_EXISTS)
                );
    }
}
