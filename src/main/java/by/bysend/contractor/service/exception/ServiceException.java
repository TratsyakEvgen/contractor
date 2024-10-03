package by.bysend.contractor.service.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    private final ErrorCode errorCode;

    public ServiceException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
