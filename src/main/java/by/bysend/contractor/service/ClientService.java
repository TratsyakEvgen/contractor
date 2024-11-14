package by.bysend.contractor.service;

import by.bysend.contractor.dto.request.ClientFilter;
import by.bysend.contractor.dto.response.ResponseClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ResponseClient> getUserClients(long userId, Pageable pageable, ClientFilter clientFilter);
}
