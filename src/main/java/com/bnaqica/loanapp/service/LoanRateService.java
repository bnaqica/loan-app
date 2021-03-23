package com.bnaqica.loanapp.service;

import com.bnaqica.loanapp.entity.LoanRateSheet;
import com.bnaqica.loanapp.processor.LoanApplicationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoanRateService {
    private LoanApplicationProcessor loanApplicationProcessor;

    public LoanRateService(LoanApplicationProcessor loanApplicationProcessor) {
        this.loanApplicationProcessor = loanApplicationProcessor;
    }

    public List<LoanRateSheet> getLoanRates(int creditScore, int vehicleYear) {
        return loanApplicationProcessor.getLoanRates(creditScore, vehicleYear);
    }

}
