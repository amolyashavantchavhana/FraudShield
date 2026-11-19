package com.fraudshield.investigation.mapper;

import com.fraudshield.investigation.dto.response.CaseReasponse;
import com.fraudshield.investigation.entity.InvestigationCases;

public class InvestigationCasesMapper 
{
private InvestigationCasesMapper()
{
	
}
public static CaseReasponse toReasponse(InvestigationCases r)
{
	return CaseReasponse.from(r);
}
}
