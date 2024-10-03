package by.bysend.contractor.repository;

import by.bysend.contractor.model.entity.Role;
import by.bysend.contractor.model.entity.name.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
