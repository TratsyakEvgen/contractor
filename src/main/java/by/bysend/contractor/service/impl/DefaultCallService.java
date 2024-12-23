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
import by.bysend.contractor.service.CallService;
import by.bysend.contractor.service.ClientEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class DefaultCallService implements CallService {
    private final CallMapper callMapper;
    private final CallRepository callRepository;
    private final ClientEntityService clientEntityService;

    @Override
    public ResponseCall create(long clientId, CreateCall createCall) {
        Client client = clientEntityService.getClient(clientId);
        Call call = callMapper.toCall(createCall);
        call.setClient(client);
        callRepository.save(call);
        return callMapper.toResponseCall(call);
    }

    @Override
    public ResponseCall update(long clientId, long callId, UpdateCall updateCall) {
        Call call = getCallByClientIdAndId(clientId, callId);
        callMapper.update(updateCall, call);
        callRepository.save(call);
        return callMapper.toResponseCall(call);
    }

    @Override
    public void delete(long clientId, long callId) {
        callRepository.deleteByClientIdAndId(callId, callId);
    }

    @Override
    public List<ResponseCall> getAll(long clientId) {
        return callRepository.findAllByClientIdOrderByLocalDateTimeAsc(clientId)
                .stream()
                .map(callMapper::toResponseCall)
                .toList();
    }

    private Call getCallByClientIdAndId(long clientId, long callId) {
        return callRepository.findByClientIdAndId(clientId, callId)
                .orElseThrow(() -> new ServiceException(
                        String.format("Call with id %d for client with id %d not found", callId, clientId),
                        ErrorCode.ENTITY_NOT_EXISTS)
                );
    }
}


