package com.bnaqica.loanapp.controller;

import com.bnaqica.loanapp.entity.LoanRateSheet;
import com.bnaqica.loanapp.service.LoanRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/loan-rate")
public class LoanRateController {
    private LoanRateService loanRateService;

    public LoanRateController(LoanRateService loanRateService) {
        this.loanRateService = loanRateService;
    }

    @GetMapping
    public List<LoanRateSheet> getLoanApplicationRates(@Param("creditScore") int creditScore,
                                                       @Param("vehicleYear") int vehicleYear) {
        return loanRateService.getLoanRates(creditScore, vehicleYear);
    }

}
