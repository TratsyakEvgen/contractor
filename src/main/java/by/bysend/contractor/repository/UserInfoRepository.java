package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByTelegramId(long telegramId);
}
