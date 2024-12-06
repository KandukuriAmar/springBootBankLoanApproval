package com.amar.service;

import com.amar.model.Loanapprover;
import java.util.List;

public interface loanApproverService {
    Loanapprover addLoanapprover(Loanapprover loanapprover);
    String deleteLoanapprover(String email);
    Loanapprover updateLoanapprover(Loanapprover loanapprover);
    Loanapprover getLoanapprover(String email);
    public String validloginapprover(Loanapprover approver);
}
