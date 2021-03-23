package com.bnaqica.loanapp.controller;

import com.bnaqica.loanapp.entity.LoanApplication;
import com.bnaqica.loanapp.service.LoanApplicationService;
import com.bnaqica.loanapp.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/loan-application")
public class LoanApplicationController {
    private LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @PostMapping
    @JsonView(Views.CreateUpdateView.class)
    public LoanApplication processLoanApplication(@RequestBody LoanApplication loanApplication) {
        log.debug("Processing new loan application : {}", loanApplication);
        return loanApplicationService.createLoanApplication(loanApplication);
    }

    @GetMapping("/{applicationId}")
    public LoanApplication processLoanApplication(@PathVariable Long applicationId) {
        log.debug("Retrieving loan application information with application id: {}", applicationId);
        return loanApplicationService.getLoanApplication(applicationId);
    }

    @GetMapping("/rates/{applicationId}")
    @JsonView(Views.RatesView.class)
    public LoanApplication getLoanApplicationRates(@PathVariable Long applicationId) {
        log.debug("Process and retrieve available loan rates for application id: {}", applicationId);
        return loanApplicationService.getLoanApplicationRates(applicationId);
    }

    @PutMapping("/{applicationId}/{loanRateId}")
    @JsonView(Views.CreateUpdateView.class)
    public LoanApplication setSelectedLoanRateForApplication(@PathVariable Long applicationId, @PathVariable Long loanRateId) {
        log.debug("Update loan application id: {} with loan rate id: {}", applicationId, loanRateId);
        return loanApplicationService.setLoanRateForApplication(applicationId, loanRateId);
    }

}
