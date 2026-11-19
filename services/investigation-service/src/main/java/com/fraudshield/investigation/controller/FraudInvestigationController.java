package com.fraudshield.investigation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fraudshield.investigation.dto.request.CreateCaseRequest;
import com.fraudshield.investigation.dto.request.DemoFlaggedRequest;
import com.fraudshield.investigation.dto.request.RecordOutcomeRequest;
import com.fraudshield.investigation.dto.request.UpdatecaseRequest;
import com.fraudshield.investigation.dto.response.CaseReasponse;
import com.fraudshield.investigation.dto.response.FlaggedReasponse;
import com.fraudshield.investigation.dto.response.OutcomeReasponse;

import com.fraudshield.investigation.messaging.event.FlaggedTransactionEvent;
import com.fraudshield.investigation.service.FraudInvestigationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/FraudShield")
public class FraudInvestigationController
{
private final  FraudInvestigationService service;

public FraudInvestigationController(FraudInvestigationService s)
{
	service=s;
}

@GetMapping("/Allflagged")
@ResponseStatus(HttpStatus.OK)
public List <FlaggedReasponse>Allflagged()
{
	return service.Allflagged();
}


@PostMapping("/demoFlag")
@ResponseStatus(HttpStatus.CREATED)
public FlaggedReasponse demoFlag(@Valid @RequestBody DemoFlaggedRequest r)
{

    return service.ingestflagged(new FlaggedTransactionEvent( r.transaction_id(),  r.user_id(), r.amount(),r.risk_score(), r.reason()));
}


@PostMapping("/CreateCase")
@ResponseStatus(HttpStatus.CREATED)
public CaseReasponse CreateCase(@Valid @RequestBody CreateCaseRequest r)
{
	return service.CreateCase(r);
}

@GetMapping("/AllCases")
@ResponseStatus(HttpStatus.OK)
public List<CaseReasponse>AllCases()
{
	return service.AllCases();
}

@PutMapping("/Updatecase/{id}")
@ResponseStatus(HttpStatus.OK)
public CaseReasponse Updatecase( @PathVariable int id,@Valid @RequestBody UpdatecaseRequest r)
{
    return service.Updatecase(id, r);
}

@PostMapping("/RecordOutcome")
@ResponseStatus(HttpStatus.CREATED)
public OutcomeReasponse RecordOutcome(@Valid @RequestBody RecordOutcomeRequest r)
{
	return service.RecordOutcome(r);
	}
}

