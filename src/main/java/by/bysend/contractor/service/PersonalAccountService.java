package by.bysend.contractor.service;

import by.bysend.contractor.dto.response.ResponsePersonalAccount;

public interface PersonalAccountService {
    ResponsePersonalAccount getPersonalAccount(long userId);
}
