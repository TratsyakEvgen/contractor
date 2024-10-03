package by.bysend.contractor.controller;

import by.bysend.contractor.model.dto.TelegramRegistrationDTO;
import by.bysend.contractor.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    private void registration(@Valid @RequestBody TelegramRegistrationDTO telegramRegistrationDTO){
        userService.registration(telegramRegistrationDTO);
    }
}
