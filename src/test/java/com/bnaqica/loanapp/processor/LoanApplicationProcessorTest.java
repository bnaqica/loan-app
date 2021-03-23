package com.bnaqica.loanapp.processor;

import com.bnaqica.loanapp.dataAccess.LoanApplicationRateRepository;
import com.bnaqica.loanapp.dataAccess.LoanApplicationRepository;
import com.bnaqica.loanapp.dataAccess.LoanSheetRepository;
import com.bnaqica.loanapp.entity.LoanApplication;
import com.bnaqica.loanapp.entity.LoanApplicationRate;
import com.bnaqica.loanapp.entity.LoanRateSheet;
import com.bnaqica.loanapp.error.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.bnaqica.loanapp.testhelper.LoanApplicationTestHelper.getLoanApplicationAfterCreation;
import static com.bnaqica.loanapp.testhelper.LoanApplicationTestHelper.getLoanApplicationRateSelected;
import static com.bnaqica.loanapp.testhelper.LoanApplicationTestHelper.getLoanApplicationWithRates;
import static com.bnaqica.loanapp.testhelper.LoanApplicationTestHelper.getLoanRateListForApplication;
import static com.bnaqica.loanapp.testhelper.LoanApplicationTestHelper.getLoanRateSheetFromDB;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class LoanApplicationProcessorTest {
    @Mock
    private LoanSheetRepository loanSheetRepository;

    @Mock
    private LoanApplicationRepository loanApplicationRepository;

    @Mock
    private LoanApplicationRateRepository loanApplicationRateRepository;

    private LoanApplicationProcessor loanApplicationProcessor;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        loanApplicationProcessor = new LoanApplicationProcessor(loanSheetRepository, loanApplicationRepository, loanApplicationRateRepository);
    }

    @Test
    public void getLoanRatesForApplication() {
        LoanApplication loanApplication = getLoanApplicationAfterCreation();
        when(loanApplicationRepository.findById(1L)).thenReturn(Optional.of(loanApplication));
        when(loanApplicationRateRepository.findAllByLoanApplication(loanApplication)).thenReturn(emptyList());
        when(loanSheetRepository.findAll()).thenReturn(getLoanRateSheetFromDB());

        loanApplicationProcessor.getLoanRatesForApplication(1L);

        verify(loanApplicationRateRepository, times(3)).save(ArgumentMatchers.any(LoanApplicationRate.class));
    }

    @Test
    public void getLoanRatesForApplication_RatesAlreadyInDB() {
        LoanApplication loanApplication = getLoanApplicationAfterCreation();
        when(loanApplicationRepository.findById(1L)).thenReturn(Optional.of(loanApplication));
        when(loanApplicationRateRepository.findAllByLoanApplication(loanApplication)).thenReturn(getLoanRateListForApplication());
        when(loanSheetRepository.findAll()).thenReturn(getLoanRateSheetFromDB());

        loanApplicationProcessor.getLoanRatesForApplication(1L);

        verify(loanApplicationRateRepository, times(0)).save(ArgumentMatchers.any(LoanApplicationRate.class));
    }

    @Test
    public void getLoanApplication() {
        LoanApplication loanApplication = getLoanApplicationAfterCreation();
        when(loanApplicationRepository.findById(1L)).thenReturn(Optional.of(getLoanApplicationAfterCreation()));

        LoanApplication resultLoanApplication = loanApplicationProcessor.getLoanApplication(1L);

        assertNotNull(resultLoanApplication);
        assertEquals(loanApplication, resultLoanApplication);
    }

    @Test
    public void getLoanApplication_IdMissingInDB() {
        String expectedMessage = "Loan Application with Id: 1 not found";
        when(loanApplicationRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                loanApplicationProcessor.getLoanApplication(1L));

        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void updateRateForLoanApplication() {
        getLoanRateListForApplication();
        LoanApplication loanApplication = getLoanApplicationWithRates();
        when(loanApplicationRepository.findById(1L)).thenReturn(Optional.of(loanApplication));
        when(loanApplicationRateRepository.findById(7L)).thenReturn(Optional.of(getLoanApplicationRateSelected()));
        when(loanApplicationRateRepository.findAllByLoanApplication(loanApplication)).thenReturn(getLoanRateListForApplication());
        when(loanSheetRepository.findAll()).thenReturn(getLoanRateSheetFromDB());

        loanApplicationProcessor.updateRateForLoanApplication(1L, 7L);

        verify(loanApplicationRepository, times(1)).setLoanRate(7L, 1L);
    }

    @Test
    public void updateRateForLoanApplication_InvalidRateId() {
        String expectedMessage = "Loan Application Rate Id: 7 not found";
        LoanApplication loanApplication = getLoanApplicationAfterCreation();
        when(loanApplicationRepository.findById(1L)).thenReturn(Optional.of(loanApplication));
        when(loanApplicationRateRepository.findAllByLoanApplication(loanApplication)).thenReturn(getLoanRateListForApplication());
        when(loanSheetRepository.findAll()).thenReturn(getLoanRateSheetFromDB());

        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                loanApplicationProcessor.updateRateForLoanApplication(1L, 7L));

        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void getLoanRates_Tier1CreditScoreNewerVehicle() {
        getLoanRatesCreditAndVehicleInRange(813, 2020, "2.99", "3.64", "4.24");
    }

    @Test
    public void getLoanRates_Tier1CreditNewerVehicleEdge() {
        getLoanRatesCreditAndVehicleInRange(781, 2019, "2.99", "3.64", "4.24");
    }

    @Test
    public void getLoanRates_Tier1CreditOlderVehicle() {
        getLoanRatesCreditAndVehicleInRange(834, 2018, "3.24", "3.89", "4.59");
    }

    @Test
    public void getLoanRates_Tier1CreditOlderVehicleEdge() {
        getLoanRatesCreditAndVehicleInRange(781, 2015, "3.24", "3.89", "4.59");
    }

    @Test
    public void getLoanRates_Tier2CreditNewerVehicle() {
        getLoanRatesCreditAndVehicleInRange(780, 2021, "3.19", "3.84", "4.44");
    }

    @Test
    public void getLoanRates_Tier2CreditOlderVehicle() {
        getLoanRatesCreditAndVehicleInRange(731, 2016, "3.44", "4.09", "4.94");
    }

    @Test
    public void getLoanRates_Tier3CreditNewerVehicle() {
        getLoanRatesCreditAndVehicleInRange(681, 2020, "3.74", "4.39", "5.29");
    }

    @Test
    public void getLoanRates_Tier3CreditOlderVehicle() {
        getLoanRatesCreditAndVehicleInRange(730, 2018, "3.99", "4.64", "5.79");
    }

    @Test
    public void getLoanRates_Tier4CreditNewerVehicle() {
        getLoanRatesCreditAndVehicleInRange(621, 2019, "5.99", "6.49", "7.54");
    }

    @Test
    public void getLoanRates_Tier4CreditOlderVehicle() {
        getLoanRatesCreditAndVehicleInRange(680, 2017, "6.24", "7.09", "8.19");
    }

    @Test
    public void getLoanRates_CreditOutOfRange() {
        getLoanRatesCreditOrVehicleOutOfRange(580, 2017);
    }

    @Test
    public void getLoanRates_VehicleYearOutOfRange() {
        getLoanRatesCreditOrVehicleOutOfRange(797, 2012);
    }

    @Test
    public void getLoanRates_VehicleYearAndCreditOutOfRange() {
        getLoanRatesCreditOrVehicleOutOfRange(580, 2013);
    }

    private void getLoanRatesCreditAndVehicleInRange(int creditScore, int vehicleYear, String rateFor60months, String rateFor72months, String rateFor84months) {
        when(loanSheetRepository.findAll()).thenReturn(getLoanRateSheetFromDB());

        List<LoanRateSheet> loanRates = loanApplicationProcessor.getLoanRates(creditScore, vehicleYear);

        assertNotNull(loanRates);
        assertEquals(loanRates.size(), 3);
        assertEquals(loanRates.get(0).getLoanRate(), new BigDecimal(rateFor60months));
        assertEquals(loanRates.get(0).getMaxLoanTerm(), 60);
        assertEquals(loanRates.get(1).getLoanRate(), new BigDecimal(rateFor72months));
        assertEquals(loanRates.get(1).getMaxLoanTerm(), 72);
        assertEquals(loanRates.get(2).getLoanRate(), new BigDecimal(rateFor84months));
        assertEquals(loanRates.get(2).getMaxLoanTerm(), 84);
    }

    private void getLoanRatesCreditOrVehicleOutOfRange(int creditScore, int vehicleYear) {
        when(loanSheetRepository.findAll()).thenReturn(getLoanRateSheetFromDB());

        List<LoanRateSheet> loanRates = loanApplicationProcessor.getLoanRates(creditScore, vehicleYear);

        assertNotNull(loanRates);
        assertEquals(loanRates.size(), 0);
    }

}