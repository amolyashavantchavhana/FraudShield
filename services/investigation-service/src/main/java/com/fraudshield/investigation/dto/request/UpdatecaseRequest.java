package com.fraudshield.investigation.dto.request;

import com.fraudshield.investigation.enums.CaseStatus;

import jakarta.validation.constraints.NotNull;

public record UpdatecaseRequest( String case_status,int investigator_id,String remarks) 
{

}
