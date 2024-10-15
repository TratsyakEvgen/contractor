package by.bysend.contractor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
class ContractorApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void contextLoads() {
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }

}
