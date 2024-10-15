package by.bysend.contractor.controller;


import by.bysend.contractor.dto.ErrorDTO;
import by.bysend.contractor.service.exception.ErrorCode;
import by.bysend.contractor.service.exception.ServiceException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    private final Map<ErrorCode, Supplier<ResponseEntity<ErrorDTO>>> responsEntitySupplierMap = new HashMap<>();

    {
        responsEntitySupplierMap.put(ErrorCode.SERVICE_ERROR, () -> new ResponseEntity<>(
                new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Что-то пошло не так"),
                HttpStatus.INTERNAL_SERVER_ERROR
        ));
        responsEntitySupplierMap.put(ErrorCode.USER_ALREADY_EXISTS, () -> new ResponseEntity<>(
                new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Пользователь уже существует"),
                HttpStatus.BAD_REQUEST
        ));
        responsEntitySupplierMap.put(ErrorCode.USER_NO_EXISTS, () -> new ResponseEntity<>(
                new ErrorDTO(HttpStatus.NOT_FOUND.value(), "Пользователь не существует"),
                HttpStatus.NOT_FOUND
        ));
        responsEntitySupplierMap.put(ErrorCode.INCORRECT_PASSWORD, () -> new ResponseEntity<>(
                new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Неверный пароль"),
                HttpStatus.BAD_REQUEST
        ));
    }

    @ExceptionHandler(ConstraintViolationException.class)

    public ResponseEntity<ErrorDTO> processConstraintViolationException(ConstraintViolationException e) {
        log.info(e.getMessage());
        String messages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        return new ResponseEntity<>(new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), messages), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorDTO> processServiceException(ServiceException e) {
        log.warn(e.getMessage());
        return Optional.ofNullable(responsEntitySupplierMap.get(e.getErrorCode()))
                .orElseGet(() -> responsEntitySupplierMap.get(ErrorCode.SERVICE_ERROR))
                .get();
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorDTO> processSignatureException(SignatureException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "Невалидный refresh токен"),
                HttpStatus.BAD_REQUEST
        );
    }
}

