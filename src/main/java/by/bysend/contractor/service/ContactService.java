package by.bysend.contractor.service;

import by.bysend.contractor.dto.request.CreateContact;
import by.bysend.contractor.dto.request.UpdateContact;
import by.bysend.contractor.dto.response.ResponseContact;
import jakarta.validation.Valid;

import java.util.List;

public interface ContactService {
    List<ResponseContact> getAll(long clientId);

    ResponseContact create(long clientId, @Valid CreateContact createContact);

    ResponseContact update(long clientId, long contactId, @Valid UpdateContact updateContact);

    void delete(long clientId, long contactId);
}
