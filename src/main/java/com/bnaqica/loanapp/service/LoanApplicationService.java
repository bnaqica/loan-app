package com.bnaqica.loanapp.service;

import com.bnaqica.loanapp.dataAccess.LoanApplicationRepository;
import com.bnaqica.loanapp.entity.LoanApplication;
import com.bnaqica.loanapp.processor.LoanApplicationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoanApplicationService {
    private LoanApplicationRepository loanApplicationRepository;
    private LoanApplicationProcessor loanApplicationProcessor;

    public LoanApplicationService(LoanApplicationRepository loanApplicationRepository,
                                  LoanApplicationProcessor loanApplicationProcessor) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.loanApplicationProcessor = loanApplicationProcessor;
    }

    public LoanApplication createLoanApplication(LoanApplication loanApplication) {
        return loanApplicationRepository.save(loanApplication);
    }

    public LoanApplication getLoanApplicationRates(Long applicationId) {
        return loanApplicationProcessor.getLoanRatesForApplication(applicationId);
    }

    public LoanApplication setLoanRateForApplication(Long applicationId, Long loanRateId) {
        return loanApplicationProcessor.updateRateForLoanApplication(applicationId, loanRateId);
    }

    public LoanApplication getLoanApplication(Long applicationId) {
        return loanApplicationProcessor.getLoanApplication(applicationId);
    }

}
