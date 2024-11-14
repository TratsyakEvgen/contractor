package by.bysend.contractor.service.impl;

import by.bysend.contractor.dto.request.CreateCall;
import by.bysend.contractor.dto.request.UpdateCall;
import by.bysend.contractor.dto.response.ResponseCall;
import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import by.bysend.contractor.mapper.CallMapper;
import by.bysend.contractor.model.entity.Call;
import by.bysend.contractor.model.entity.Client;
import by.bysend.contractor.repository.CallRepository;
import by.bysend.contractor.repository.ClientRepository;
import by.bysend.contractor.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class DefaultCallService implements CallService {
    private final CallMapper callMapper;
    private final CallRepository callRepository;
    private final ClientRepository clientRepository;

    @Override
    public ResponseCall create(long clientId, CreateCall createCall) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ServiceException(
                        String.format("Client with id %d not found", clientId), ErrorCode.ENTITY_NOT_EXISTS)
                );
        Call call = callMapper.getCall(createCall);
        call.setClient(client);
        callRepository.save(call);
        return callMapper.getResponseCall(call);
    }

    @Override
    public ResponseCall update(long clientId, long callId, UpdateCall updateCall) {
        Call call = callRepository.findByClientIdAndId(clientId, callId)
                .orElseThrow(() -> new ServiceException(
                        String.format("Call with id %d for client with id %d not found", callId, clientId),
                        ErrorCode.ENTITY_NOT_EXISTS)
                );
        callMapper.update(updateCall, call);
        callRepository.save(call);
        return callMapper.getResponseCall(call);
    }

    @Override
    public void delete(long clientId, long callId) {
        callRepository.deleteByClientIdAndId(callId, callId);
    }
}


