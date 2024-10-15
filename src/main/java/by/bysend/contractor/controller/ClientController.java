package by.bysend.contractor.controller;

import by.bysend.contractor.dto.client.PagebaleUserClientsFilterDTO;
import by.bysend.contractor.dto.client.UserClientsDTO;
import by.bysend.contractor.dto.page.PageDTO;
import by.bysend.contractor.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/users/{id}/clients")
    public PageDTO<UserClientsDTO> getUserClients(@PathVariable long id, @ModelAttribute PagebaleUserClientsFilterDTO filter) {
        filter.setUserId(id);
        return clientService.getUserClients(filter);
    }
}
