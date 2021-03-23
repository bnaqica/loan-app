package com.bnaqica.loanapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class LoanRateSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer maxVehicleYear;

    private Integer minVehicleYear;

    private Integer maxLoanTerm;

    private Integer minLoanTerm;

    private Integer maxCreditScore;

    private Integer minCreditScore;

    private BigDecimal loanRate;
}
