package by.bysend.contractor.service.impl;

import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.model.entity.Role;
import by.bysend.contractor.model.entity.User;
import by.bysend.contractor.repository.AuthDataRepository;
import by.bysend.contractor.repository.RoleRepository;
import by.bysend.contractor.repository.UserInfoRepository;
import by.bysend.contractor.repository.UserRepository;
import by.bysend.contractor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthDataRepository authDataRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Lock lock;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void create(User user) {
        Long telegramId = user.getUserInfo().getTelegramId();
        Role role = roleRepository.findByRole("USER")
                .orElseThrow(() -> new ServiceException("Role USER not found", ErrorCode.INTERNAL_SERVER_ERROR));
        lock.lock();
        try {
            throwIfUserExists(telegramId);
            throwIfLoginExists(telegramId);
            user.getAuthData().setRole(role);
            userRepository.save(user);
        } finally {
            lock.unlock();
        }

    }


    private void throwIfLoginExists(Long telegramId) {
        userInfoRepository.findByTelegramId(telegramId)
                .ifPresent(authData -> {
                    throw new ServiceException(String.format("User with login %d already exists", telegramId),
                            ErrorCode.USER_ALREADY_EXISTS
                    );
                });
    }

    private void throwIfUserExists(Long telegramId) {
        authDataRepository.findByLogin(telegramId.toString())
                .ifPresent(authData -> {
                    throw new ServiceException(
                            String.format("User with telegramID %d already exists", telegramId),
                            ErrorCode.USER_ALREADY_EXISTS
                    );
                });
    }
}
