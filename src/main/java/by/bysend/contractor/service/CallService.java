package by.bysend.contractor.service;

import by.bysend.contractor.dto.request.CreateCall;
import by.bysend.contractor.dto.request.UpdateCall;
import by.bysend.contractor.dto.response.ResponseCall;
import jakarta.validation.Valid;

public interface CallService {
    ResponseCall create(long clientId, @Valid CreateCall createCall);

    ResponseCall update(long clientId, long callId, @Valid UpdateCall updateCall);

    void delete(long clientId, long callId);
}
