package by.bysend.contractor.controller;

import by.bysend.contractor.dto.user.PersonalAccountDTO;
import by.bysend.contractor.service.PersonalAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonalAccountController {
    private final PersonalAccountService personalAccountService;

    @GetMapping("users/{id}/personalAccount")
    public PersonalAccountDTO getPersonalAccount(@PathVariable long id){
        return personalAccountService.getPersonalAccount(id);
    }
}
