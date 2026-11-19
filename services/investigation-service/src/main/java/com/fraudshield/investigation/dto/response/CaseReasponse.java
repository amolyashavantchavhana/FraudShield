package com.fraudshield.investigation.dto.response;

import java.time.LocalDateTime;

import com.fraudshield.investigation.entity.InvestigationCases;

public record CaseReasponse(Integer case_id,Integer Flagged_id,Integer transaction_id,Integer investigator_id,String case_status,String remarks,LocalDateTime created_at)
{

	public static CaseReasponse from(InvestigationCases ic) 
	{
      return new CaseReasponse(ic.getcase_id(),ic.getFlaggedTransaction().getflagged_id(),ic.gettransaction_id(),ic.getinvestigator_id(),ic.getcase_status(),ic.getRemarks(),ic.getCreatedAt());
	}

}
