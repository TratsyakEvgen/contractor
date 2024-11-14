package by.bysend.contractor.controller;

import by.bysend.contractor.dto.request.ClientFilter;
import by.bysend.contractor.dto.response.ResponseClient;
import by.bysend.contractor.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/users/{userId}/clients")
    public Page<ResponseClient> getUserClients(@PathVariable long userId, Pageable pageable, ClientFilter clientFilter) {
        return clientService.getUserClients(userId, pageable, clientFilter);
    }
}
