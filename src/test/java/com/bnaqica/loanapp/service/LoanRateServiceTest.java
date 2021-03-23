package com.bnaqica.loanapp.service;

import com.bnaqica.loanapp.entity.LoanRateSheet;
import com.bnaqica.loanapp.processor.LoanApplicationProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class LoanRateServiceTest {

    @Mock
    private LoanApplicationProcessor loanApplicationProcessor;

    private LoanRateService loanRateService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        loanRateService = new LoanRateService(loanApplicationProcessor);
    }

    @Test
    public void getLoanRates() {
        LoanRateSheet loanRateSheet = LoanRateSheet.builder()
                .loanRate(new BigDecimal("12763.98"))
                .build();
        when(loanApplicationProcessor.getLoanRates(679, 2019)).thenReturn(asList(loanRateSheet));

        List<LoanRateSheet> loanRates = loanRateService.getLoanRates(679, 2019);

        assertNotNull(loanRates);
    }

}