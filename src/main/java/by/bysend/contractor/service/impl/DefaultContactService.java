package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.request.CreateContact;
import by.bysend.contractor.dto.request.UpdateContact;
import by.bysend.contractor.dto.response.ResponseContact;
import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.mapper.ContactMapper;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.model.entity.Contact;
import by.bysend.contractor.repository.ContactRepository;
import by.bysend.contractor.service.ClientEntityService;
import by.bysend.contractor.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class DefaultContactService implements ContactService {
    private final ContactRepository contactRepository;
    private final ClientEntityService clientEntityService;
    private final ContactMapper contactMapper;

    @Override
    public List<ResponseContact> getAll(long clientId) {
        return contactRepository.findAllByClientId(clientId)
                .stream()
                .map(contactMapper::toResponseContact)
                .toList();
    }

    @Override
    public ResponseContact create(long clientId, CreateContact createContact) {
        Client client = clientEntityService.getClient(clientId);
        Contact contact = contactMapper.toContact(createContact);
        contact.setClient(client);
        contactRepository.save(contact);
        return contactMapper.toResponseContact(contact);
    }

    @Override
    public ResponseContact update(long clientId, long contactId, UpdateContact updateContact) {
        Contact contact = getContactByClientIdAndId(clientId, contactId);
        contactMapper.update(updateContact, contact);
        contactRepository.save(contact);
        return contactMapper.toResponseContact(contact);
    }

    @Override
    public void delete(long clientId, long contactId) {
        contactRepository.deleteByClientIdAndId(clientId, contactId);
    }


    private Contact getContactByClientIdAndId(long clientId, long contactId) {
        return contactRepository.findByClientIdAndId(clientId, contactId)
                .orElseThrow(() -> new ServiceException(
                        String.format("Contact with id %d for client with id %d not found", contactId, clientId),
                        ErrorCode.ENTITY_NOT_EXISTS)
                );
    }
}
