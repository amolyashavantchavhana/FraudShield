package com.fraudshield.investigation.dto.request;

import java.time.LocalDateTime;

public record CreateCaseRequest(Integer case_id,Integer flagged_id,Integer transaction_id,Integer investigator_id, String case_status,String remarks ,LocalDateTime flagged_at) 
{

}
