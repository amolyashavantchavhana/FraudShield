package com.fraudshield.investigation.messaging.event;

public record InvestigationCompletedEvent(Integer case_id,Integer transaction_id,String result)
{

}
