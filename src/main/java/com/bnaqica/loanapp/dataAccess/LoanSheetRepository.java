package com.bnaqica.loanapp.dataAccess;

import com.bnaqica.loanapp.entity.LoanRateSheet;
import org.springframework.data.repository.CrudRepository;

public interface LoanSheetRepository extends CrudRepository<LoanRateSheet, Integer> {

}
