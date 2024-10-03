package by.bysend.contractor.config;

import by.bysend.contractor.model.dto.TelegramRegistrationDTO;
import by.bysend.contractor.model.entity.AuthData;
import by.bysend.contractor.security.JwtFilter;
import jakarta.servlet.DispatcherType;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.createTypeMap(TelegramRegistrationDTO.class, AuthData.class)
                .addMappings(
                        mapper -> mapper.map(
                                telegramRegistrationDTO -> telegramRegistrationDTO.getTelegramId().toString(),
                                (authData, login) -> authData.setLogin((String) login)
                        )
                );

        return modelMapper;
    }

    @Bean
    public Lock lock() {
        return new ReentrantLock();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter filter) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/tokens/**").permitAll()
                                .requestMatchers("/users").permitAll()
                                .requestMatchers("/test").permitAll()
                                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
