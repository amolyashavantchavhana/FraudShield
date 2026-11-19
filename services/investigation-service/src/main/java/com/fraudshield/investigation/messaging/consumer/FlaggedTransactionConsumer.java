package com.fraudshield.investigation.messaging.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fraudshield.investigation.messaging.event.FlaggedTransactionEvent;
import com.fraudshield.investigation.service.FraudInvestigationService;

@Component
public class FlaggedTransactionConsumer
{
 private final FraudInvestigationService service;
 
 public FlaggedTransactionConsumer(FraudInvestigationService service)
 {
	 this.service=service; 
 
}
 


@KafkaListener(topics="flagged_transactions",groupId="fraud_investigation_group")//topic we are storing flagged transaction

public void consume(FlaggedTransactionEvent e)
{
	System.out.println("Received Flagged Transaction:"+e.transaction_id());
	
	
	service.ingestflagged(e);
	
	System.out.println("Transaction Processed successfully:"+e.transaction_id());
}
}

