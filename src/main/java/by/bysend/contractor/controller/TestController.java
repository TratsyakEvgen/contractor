package by.bysend.contractor.controller;

import by.bysend.contractor.model.entity.User;
import by.bysend.contractor.repository.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Hidden
public class TestController {
    private final UserRepository userRepository;

    @GetMapping
    private Page<User> user() {
        Page<User> all = userRepository.findAll(PageRequest.of(1, 1));
        System.out.println(all.getContent());
        return all;
    }

    @PostMapping
    private void admin(@RequestParam("file") MultipartFile file) throws IOException {

        System.out.println(file.getResource().getFilename());
    }


}
