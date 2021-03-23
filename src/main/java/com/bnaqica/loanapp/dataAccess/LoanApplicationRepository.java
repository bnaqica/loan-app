package com.bnaqica.loanapp.dataAccess;

import com.bnaqica.loanapp.entity.LoanApplication;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LoanApplicationRepository extends CrudRepository<LoanApplication, Long> {

    @Modifying
    @Transactional
    @Query("update LoanApplication la set la.selectedLoanRateId = ?1 where la.id = ?2")
    void setLoanRate(Long loanRateSelected, Long loanApplicationId);

}
