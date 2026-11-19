package com.fraudshield.investigation.validation;

import com.fraudshield.investigation.messaging.event.FlaggedTransactionEvent;

public final class InvestigationValidator 
{
	
private InvestigationValidator() 
{
}

	// Validate kafka event information
    public static void validateFlaggedEvent(FlaggedTransactionEvent e) {

            if  (e == null ||
            e.transaction_id() == null ||
            e.transaction_id() <= 0 ||
            e.user_id() == null ||
            e.user_id() <= 0 ||
            e.amount() == null ||
            e.amount() <= 0 ||
            e.risk_score() == null ||
            e.risk_score() < 0 ||
            e.risk_score() > 100 ||
            e.reason() == null ||
            e.reason().isBlank()) {

            throw new IllegalArgumentException( "Invalid flagged transaction event fields");
               
               }
    }

    // Validate investigation case_status
    public static void validateCaseStatus(String case_status)
    {

        if (case_status == null ||
            (!"OPEN".equals(case_status) &&
             !"IN_PROGRESS".equals(case_status))) 
        {

           throw new IllegalArgumentException("Status must be OPEN or IN_PROGRESS");
                    
               
        }
    }

    // Validate investigation result
    public static void validateInvestigationResult(String result) {

        if (result == null ||
            (!"FRAUD".equals(result) &&
             !"GENUINE".equals(result))) {

            throw new IllegalArgumentException("Result must be FRAUD or GENUINE");
                
           
        }
    }

}

