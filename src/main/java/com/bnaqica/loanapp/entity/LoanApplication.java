package com.bnaqica.loanapp.entity;

import com.bnaqica.loanapp.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.CreateUpdateView.class)
    private Long id;

    private BigDecimal currentLoanAmount;

    private String firstName;

    private int creditScore;

    private BigDecimal monthlyIncome;

    private String phone;

    private String postalCode;

    private String vehicleMake;

    private int vehicleMileage;

    private String vehicleModel;

    private int vehicleYear;

    @OneToMany(mappedBy = "loanApplication")
    @JsonView(Views.RatesView.class)
    private List<LoanApplicationRate> rates;

    private Long selectedLoanRateId;

}
