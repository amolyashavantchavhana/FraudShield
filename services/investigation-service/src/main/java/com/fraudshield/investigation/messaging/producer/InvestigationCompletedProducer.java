package com.fraudshield.investigation.messaging.producer;

import org.springframework.stereotype.Component;

import com.fraudshield.investigation.messaging.event.InvestigationCompletedEvent;

import org.springframework.kafka.core.KafkaTemplate;

@Component
public class InvestigationCompletedProducer
{
private final KafkaTemplate<String,InvestigationCompletedEvent>kafkaTemplate;

public InvestigationCompletedProducer(KafkaTemplate<String,InvestigationCompletedEvent>kafkaTemplate)
{
	this.kafkaTemplate=kafkaTemplate;
}

public void publish(InvestigationCompletedEvent e)
{
	kafkaTemplate.send("investigation_completed",String.valueOf(e.case_id()),e);//get the case id and convert into string to send investigation completed
}

}
