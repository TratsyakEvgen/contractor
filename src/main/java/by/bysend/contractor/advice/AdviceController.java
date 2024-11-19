package by.bysend.contractor.advice;


import by.bysend.contractor.dto.response.ResponseError;
import by.bysend.contractor.exception.ErrorCode;
import by.bysend.contractor.exception.ServiceException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class AdviceController {
    private final Map<ErrorCode, Function<ServiceException, ResponseEntity<ResponseError>>> responsEntitySupplierMap = new HashMap<>();

    {
        responsEntitySupplierMap.put(ErrorCode.INTERNAL_SERVER_ERROR, e -> new ResponseEntity<>(
                new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
                HttpStatus.INTERNAL_SERVER_ERROR
        ));
        responsEntitySupplierMap.put(ErrorCode.ENTITY_ALREADY_EXISTS, e -> new ResponseEntity<>(
                new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                HttpStatus.BAD_REQUEST
        ));
        responsEntitySupplierMap.put(ErrorCode.ENTITY_NOT_EXISTS, e -> new ResponseEntity<>(
                new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        ));
        responsEntitySupplierMap.put(ErrorCode.INCORRECT_PASSWORD, e -> new ResponseEntity<>(
                new ResponseError(HttpStatus.BAD_REQUEST.value(), "Неверный пароль"),
                HttpStatus.BAD_REQUEST
        ));
    }

    @ExceptionHandler(ConstraintViolationException.class)

    public ResponseEntity<ResponseError> processConstraintViolationException(ConstraintViolationException e) {
        log.info(e.getMessage());
        String messages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        return new ResponseEntity<>(new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), messages), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseError> processServiceException(ServiceException e) {
        log.warn(e.getMessage());
        return Optional.ofNullable(responsEntitySupplierMap.get(e.getErrorCode()))
                .orElseGet(() -> responsEntitySupplierMap.get(ErrorCode.INTERNAL_SERVER_ERROR))
                .apply(e);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseError> processSignatureException(SignatureException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(
                new ResponseError(HttpStatus.BAD_REQUEST.value(), "Невалидный refresh токен"),
                HttpStatus.BAD_REQUEST
        );
    }
}

