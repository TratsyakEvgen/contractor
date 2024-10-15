package by.bysend.contractor.service;

import by.bysend.contractor.dto.user.PersonalAccountDTO;
import jakarta.validation.constraints.Min;

public interface PersonalAccountService {
    PersonalAccountDTO getPersonalAccount(@Min(value = 1, message = "Id пользователя должно быть не меньше 1") long userId);
}
