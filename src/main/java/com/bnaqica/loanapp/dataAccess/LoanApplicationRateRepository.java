package com.bnaqica.loanapp.dataAccess;

import com.bnaqica.loanapp.entity.LoanApplication;
import com.bnaqica.loanapp.entity.LoanApplicationRate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanApplicationRateRepository extends CrudRepository<LoanApplicationRate, Long> {
    List<LoanApplicationRate> findAllByLoanApplication(LoanApplication application);
}
