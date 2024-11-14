package by.bysend.contractor.controller;

import by.bysend.contractor.dto.request.CreateCall;
import by.bysend.contractor.dto.request.UpdateCall;
import by.bysend.contractor.dto.response.ResponseCall;
import by.bysend.contractor.service.CallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients/{clientId}/calls")
public class CallController {
    private final CallService callService;

    @PostMapping
    public ResponseCall create(@PathVariable long clientId, @RequestBody CreateCall createCall) {
        return callService.create(clientId, createCall);
    }

    @PutMapping("/{callId}")
    public ResponseCall update(@PathVariable long clientId, @PathVariable long callId, @RequestBody UpdateCall updateCall) {
        return callService.update(clientId, callId, updateCall);
    }

    @DeleteMapping("/{callId}")
    public void delete(@PathVariable long clientId, @PathVariable long callId) {
        callService.delete(clientId, callId);
    }
}
