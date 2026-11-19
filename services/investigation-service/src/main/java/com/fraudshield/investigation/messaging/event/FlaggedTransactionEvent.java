package com.fraudshield.investigation.messaging.event;



public record FlaggedTransactionEvent(Integer transaction_id,Integer user_id,Integer amount,Integer risk_score,String reason) 
{

}