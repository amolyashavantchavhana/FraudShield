package com.fraudshield.investigation.mapper;

import com.fraudshield.investigation.dto.response.OutcomeReasponse;
import com.fraudshield.investigation.entity.InvestigationOutcomes;

public class InvestigationOutcomesMapper 
{
private InvestigationOutcomesMapper()
{
	
}
public static OutcomeReasponse toReasponse(InvestigationOutcomes r)
{
	return OutcomeReasponse.from(r);
}
}
