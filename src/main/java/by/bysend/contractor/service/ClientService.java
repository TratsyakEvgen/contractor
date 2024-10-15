package by.bysend.contractor.service;

import by.bysend.contractor.dto.client.PagebaleUserClientsFilterDTO;
import by.bysend.contractor.dto.client.UserClientsDTO;
import by.bysend.contractor.dto.page.PageDTO;
import jakarta.validation.Valid;

public interface ClientService {
    PageDTO<UserClientsDTO> getUserClients(@Valid PagebaleUserClientsFilterDTO filterDTO);
}
