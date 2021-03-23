package com.bnaqica.loanapp.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class LoanApplicationErrorResponse {
    private LocalDateTime date;
    private int status;
    private String message;

    public LoanApplicationErrorResponse(int status, Exception e) {
        this.date = LocalDateTime.now();
        this.status = status;
        this.message = e.getMessage();
    }
}
