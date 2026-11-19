package com.fraudshield.investigation.dto.response;

import java.time.LocalDateTime;

import com.fraudshield.investigation.entity.InvestigationOutcomes;

public record OutcomeReasponse(Integer outcome_id,Integer case_id,String result,String remarks,LocalDateTime completed_at) 
{
	public static OutcomeReasponse from(InvestigationOutcomes io)
	{
		return new OutcomeReasponse(io.getoutcome_id(),io.getInvestigationcase().getcase_id(),io.getresult(),io.getremarks(),io.getcompleted_at());
	}

}
