package com.bnaqica.loanapp.testhelper;

import com.bnaqica.loanapp.entity.LoanApplication;
import com.bnaqica.loanapp.entity.LoanApplicationRate;
import com.bnaqica.loanapp.entity.LoanRateSheet;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

public class LoanApplicationTestHelper {

    public static LoanApplication getLoanApplicationAfterCreation() {
        return LoanApplication.builder()
                .id(1L)
                .currentLoanAmount(new BigDecimal("12763.98"))
                .firstName("James")
                .creditScore(720)
                .monthlyIncome(new BigDecimal("7000.00"))
                .phone("9876543210")
                .postalCode("80012")
                .vehicleMake("Toyota")
                .vehicleMileage(32987)
                .vehicleModel("Highlander")
                .vehicleYear(2019)
                .build();
    }

    public static LoanApplicationRate getLoanApplicationRateSelected() {
        return  LoanApplicationRate.builder()
                .id(7L)
                .loanTerm(72)
                .loanAmount(new BigDecimal("12000.00"))
                .interestRate(new BigDecimal("3.19"))
                .loanApplication(getLoanApplicationAfterCreation())
                .build();
    }

    public static List<LoanApplicationRate> getLoanRateListForApplication() {
        LoanApplicationRate lar1 = LoanApplicationRate.builder()
                .id(6L)
                .loanTerm(60)
                .loanAmount(new BigDecimal("12000.00"))
                .interestRate(new BigDecimal("2.99"))
                .build();

        LoanApplicationRate lar3 = LoanApplicationRate.builder()
                .id(8L)
                .loanTerm(84)
                .loanAmount(new BigDecimal("12000.00"))
                .interestRate(new BigDecimal("3.74"))
                .build();

        return asList(lar1, getLoanApplicationRateSelected(), lar3);
    }

    public static LoanApplication getLoanApplicationWithRates() {
        return LoanApplication.builder()
                .currentLoanAmount(new BigDecimal("12763.98"))
                .firstName("James")
                .creditScore(720)
                .monthlyIncome(new BigDecimal("7000.00"))
                .phone("9876543210")
                .postalCode("80012")
                .vehicleMake("Toyota")
                .vehicleMileage(32987)
                .vehicleModel("Highlander")
                .vehicleYear(2019)
                .rates(getLoanRateListForApplication())
                .build();
    }

    public static List<LoanRateSheet> getLoanRateSheetFromDB() {
        LoanRateSheet lrs1 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(60)
                .minCreditScore(781)
                .loanRate(new BigDecimal("2.99"))
                .build();

        LoanRateSheet lrs2 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(60)
                .maxCreditScore(780)
                .minCreditScore(731)
                .loanRate(new BigDecimal("3.19"))
                .build();

        LoanRateSheet lrs3 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(60)
                .maxCreditScore(730)
                .minCreditScore(681)
                .loanRate(new BigDecimal("3.74"))
                .build();

        LoanRateSheet lrs4 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(60)
                .maxCreditScore(680)
                .minCreditScore(621)
                .loanRate(new BigDecimal("5.99"))
                .build();

        LoanRateSheet lrs5 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(72)
                .minLoanTerm(61)
                .minCreditScore(781)
                .loanRate(new BigDecimal("3.64"))
                .build();

        LoanRateSheet lrs6 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(72)
                .minLoanTerm(61)
                .maxCreditScore(780)
                .minCreditScore(731)
                .loanRate(new BigDecimal("3.84"))
                .build();

        LoanRateSheet lrs7 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(72)
                .minLoanTerm(61)
                .maxCreditScore(730)
                .minCreditScore(681)
                .loanRate(new BigDecimal("4.39"))
                .build();

        LoanRateSheet lrs8 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(72)
                .minLoanTerm(61)
                .maxCreditScore(680)
                .minCreditScore(621)
                .loanRate(new BigDecimal("6.49"))
                .build();

        LoanRateSheet lrs9 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(84)
                .minLoanTerm(73)
                .minCreditScore(781)
                .loanRate(new BigDecimal("4.24"))
                .build();

        LoanRateSheet lrs10 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(84)
                .minLoanTerm(73)
                .maxCreditScore(780)
                .minCreditScore(731)
                .loanRate(new BigDecimal("4.44"))
                .build();

        LoanRateSheet lrs11 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(84)
                .minLoanTerm(73)
                .maxCreditScore(730)
                .minCreditScore(681)
                .loanRate(new BigDecimal("5.29"))
                .build();

        LoanRateSheet lrs12 = LoanRateSheet.builder()
                .minVehicleYear(2019)
                .maxLoanTerm(84)
                .minLoanTerm(73)
                .maxCreditScore(680)
                .minCreditScore(621)
                .loanRate(new BigDecimal("7.54"))
                .build();

        LoanRateSheet lrs13 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(60)
                .minCreditScore(781)
                .loanRate(new BigDecimal("3.24"))
                .build();

        LoanRateSheet lrs14 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(60)
                .maxCreditScore(780)
                .minCreditScore(731)
                .loanRate(new BigDecimal("3.44"))
                .build();

        LoanRateSheet lrs15 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(60)
                .maxCreditScore(730)
                .minCreditScore(681)
                .loanRate(new BigDecimal("3.99"))
                .build();

        LoanRateSheet lrs16 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(60)
                .maxCreditScore(680)
                .minCreditScore(621)
                .loanRate(new BigDecimal("6.24"))
                .build();
        LoanRateSheet lrs17 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(72)
                .minLoanTerm(61)
                .minCreditScore(781)
                .loanRate(new BigDecimal("3.89"))
                .build();

        LoanRateSheet lrs18 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(72)
                .minLoanTerm(61)
                .maxCreditScore(780)
                .minCreditScore(731)
                .loanRate(new BigDecimal("4.09"))
                .build();

        LoanRateSheet lrs19 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(72)
                .minLoanTerm(61)
                .maxCreditScore(730)
                .minCreditScore(681)
                .loanRate(new BigDecimal("4.64"))
                .build();

        LoanRateSheet lrs20 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(72)
                .minLoanTerm(61)
                .maxCreditScore(680)
                .minCreditScore(621)
                .loanRate(new BigDecimal("7.09"))
                .build();
        LoanRateSheet lrs21 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(84)
                .minLoanTerm(73)
                .minCreditScore(781)
                .loanRate(new BigDecimal("4.59"))
                .build();

        LoanRateSheet lrs22 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(84)
                .minLoanTerm(73)
                .maxCreditScore(780)
                .minCreditScore(731)
                .loanRate(new BigDecimal("4.94"))
                .build();

        LoanRateSheet lrs23 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(84)
                .minLoanTerm(73)
                .maxCreditScore(730)
                .minCreditScore(681)
                .loanRate(new BigDecimal("5.79"))
                .build();

        LoanRateSheet lrs24 = LoanRateSheet.builder()
                .maxVehicleYear(2018)
                .minVehicleYear(2015)
                .maxLoanTerm(84)
                .minLoanTerm(73)
                .maxCreditScore(680)
                .minCreditScore(621)
                .loanRate(new BigDecimal("8.19"))
                .build();

        return asList(lrs1, lrs2, lrs3, lrs4, lrs5, lrs6, lrs7, lrs8, lrs9, lrs10, lrs11, lrs12,
                lrs13, lrs14, lrs15, lrs16, lrs17, lrs18, lrs19, lrs20, lrs21, lrs22, lrs23, lrs24);
    }

}
