package com.bnaqica.loanapp.processor;

import com.bnaqica.loanapp.dataAccess.LoanApplicationRateRepository;
import com.bnaqica.loanapp.dataAccess.LoanApplicationRepository;
import com.bnaqica.loanapp.dataAccess.LoanSheetRepository;
import com.bnaqica.loanapp.entity.LoanApplication;
import com.bnaqica.loanapp.entity.LoanApplicationRate;
import com.bnaqica.loanapp.entity.LoanRateSheet;
import com.bnaqica.loanapp.error.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
@Slf4j
public class LoanApplicationProcessor {
    private LoanSheetRepository loanSheetRepository;
    private LoanApplicationRepository loanApplicationRepository;
    private LoanApplicationRateRepository loanApplicationRateRepository;

    public LoanApplicationProcessor(LoanSheetRepository loanSheetRepository,
                                    LoanApplicationRepository loanApplicationRepository,
                                    LoanApplicationRateRepository loanApplicationRateRepository) {
        this.loanSheetRepository = loanSheetRepository;
        this.loanApplicationRepository = loanApplicationRepository;
        this.loanApplicationRateRepository = loanApplicationRateRepository;
    }

    public LoanApplication getLoanRatesForApplication(Long applicationId) {
        LoanApplication loanApplication = getLoanApplication(applicationId);
        int size = loanApplicationRateRepository.findAllByLoanApplication(loanApplication).size();

        if (size == 0) {
            List<LoanRateSheet> loanRates = getLoanRates(loanApplication.getCreditScore(), loanApplication.getVehicleYear());

            for (LoanRateSheet loanRate : loanRates) {
                LoanApplicationRate loanApplicationRate = LoanApplicationRate.builder()
                        .loanAmount(loanApplication.getCurrentLoanAmount())
                        .interestRate(loanRate.getLoanRate())
                        .loanTerm(loanRate.getMaxLoanTerm())
                        .loanApplication(loanApplication)
                        .build();

                loanApplicationRateRepository.save(loanApplicationRate);
            }
        }

        return getLoanApplication(applicationId);
    }

    public List<LoanRateSheet> getLoanRates(int creditScore, int vehicleYear) {
        Iterable<LoanRateSheet> allRates = loanSheetRepository.findAll();

        List<LoanRateSheet> rates = new ArrayList<>();
        allRates.forEach(rates::add);

        return rates.stream()
                .filter(loanRate -> isApplicantQualifiesForRate(loanRate, creditScore, vehicleYear))
                .collect(Collectors.toList());
    }

    public LoanApplication updateRateForLoanApplication(Long applicationId, Long rateId) {
        getLoanApplication(applicationId);
        validateRateId(applicationId, rateId);
        loanApplicationRepository.setLoanRate(rateId, applicationId);

        return getLoanApplication(applicationId);
    }

    public LoanApplication getLoanApplication(Long applicationId) {
        return loanApplicationRepository.findById(applicationId).orElseThrow(
                () -> new ResourceNotFoundException(format("Loan Application with Id: %s not found", applicationId)));
    }

    public void validateRateId(Long applicationId, Long rateId) {
        LoanApplicationRate loanApplicationRate = loanApplicationRateRepository.findById(rateId).orElseThrow(
                () -> new ResourceNotFoundException(format("Loan Application Rate Id: %s not found", rateId)));

        if (applicationId != loanApplicationRate.getLoanApplication().getId()) {
            throw new ResourceNotFoundException(format("Loan Application Rate Id: %s not found in loan Application Id: %s", rateId, applicationId));
        }
    }

    private boolean isApplicantQualifiesForRate(LoanRateSheet loanRate, int creditScore, int vehicleYear) {
        return (creditScore >= loanRate.getMinCreditScore()) &&
                (loanRate.getMaxCreditScore() == null || creditScore <= loanRate.getMaxCreditScore()) &&
                (vehicleYear >= loanRate.getMinVehicleYear()) &&
                (loanRate.getMaxVehicleYear() == null || vehicleYear <= loanRate.getMaxVehicleYear());
    }

}