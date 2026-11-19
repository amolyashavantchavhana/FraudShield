package com.fraudshield.investigation.service;

import java.util.List;

import com.fraudshield.investigation.dto.request.CreateCaseRequest;
import com.fraudshield.investigation.dto.request.RecordOutcomeRequest;
import com.fraudshield.investigation.dto.request.UpdatecaseRequest;
import com.fraudshield.investigation.dto.response.CaseReasponse;
import com.fraudshield.investigation.dto.response.FlaggedReasponse;
import com.fraudshield.investigation.dto.response.OutcomeReasponse;
import com.fraudshield.investigation.messaging.event.FlaggedTransactionEvent;

public interface FraudInvestigationService 
{
FlaggedReasponse ingestflagged(FlaggedTransactionEvent event);

List<FlaggedReasponse>Allflagged();

CaseReasponse CreateCase(CreateCaseRequest request);

List<CaseReasponse>AllCases();

CaseReasponse Updatecase( int id,UpdatecaseRequest request);

OutcomeReasponse RecordOutcome(RecordOutcomeRequest request);


}
