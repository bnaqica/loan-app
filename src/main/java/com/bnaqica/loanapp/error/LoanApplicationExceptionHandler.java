package com.bnaqica.loanapp.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class LoanApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public LoanApplicationErrorResponse handleResourceNotFoundException(Exception ex) {
        return getErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({DataRetrievalFailureException.class, Exception.class})
    public LoanApplicationErrorResponse handleException(Exception ex) {
        return getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private LoanApplicationErrorResponse getErrorResponse(Exception ex, HttpStatus status) {
        log.error(ex.getMessage(), ex);
        return new LoanApplicationErrorResponse(status.value(), ex);
    }

}
