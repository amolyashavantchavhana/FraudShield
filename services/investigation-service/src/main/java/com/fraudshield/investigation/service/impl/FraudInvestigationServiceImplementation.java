package com.fraudshield.investigation.service.impl;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fraudshield.investigation.exception.ConflictExecption;
import com.fraudshield.investigation.exception.NotFoundException;
import com.fraudshield.investigation.dto.request.CreateCaseRequest;
import com.fraudshield.investigation.dto.request.RecordOutcomeRequest;
import com.fraudshield.investigation.dto.request.UpdatecaseRequest;
import com.fraudshield.investigation.dto.response.CaseReasponse;

import com.fraudshield.investigation.dto.response.FlaggedReasponse;
import com.fraudshield.investigation.dto.response.OutcomeReasponse;
import com.fraudshield.investigation.entity.FlaggedTransaction;
import com.fraudshield.investigation.entity.InvestigationCases;
import com.fraudshield.investigation.entity.InvestigationOutcomes;
import com.fraudshield.investigation.messaging.event.FlaggedTransactionEvent;
import com.fraudshield.investigation.repository.FlaggedTransactionRepository;
import com.fraudshield.investigation.repository.InvestigationCasesRepository;
import com.fraudshield.investigation.repository.InvestigationOutcomesRepository;
import com.fraudshield.investigation.service.FraudInvestigationService;
import com.fraudshield.investigation.validation.InvestigationValidator;

@Service
@Transactional
public class FraudInvestigationServiceImplementation implements FraudInvestigationService
{
	
	private final FlaggedTransactionRepository flags;
	private final InvestigationCasesRepository cases;
	private final InvestigationOutcomesRepository outcomes;
	
	//constructor injection
	
public FraudInvestigationServiceImplementation(FlaggedTransactionRepository flags,InvestigationCasesRepository cases, InvestigationOutcomesRepository outcomes)
{
	this.flags=flags;
	this.cases=cases;
	this.outcomes=outcomes;
}

//flagged transaction information receiving from Kafka

	@Override
	@Transactional
	public FlaggedReasponse ingestflagged(FlaggedTransactionEvent e)
	{
		InvestigationValidator.validateFlaggedEvent(e);//here validation the flagged event 

		    // Check the transaction already exists
		    Optional<FlaggedTransaction> existing =
		            flags.findBytransaction_id(e.transaction_id());

		    if (existing.isPresent()) 
		    {
		        throw new ConflictExecption("Flagged Transaction already exists with trasaction Id:"+e.transaction_id());
		    }

		    // Create entity object
		    FlaggedTransaction f = new FlaggedTransaction();

		    // Set Kafka event data
		    f.settransaction_id(e.transaction_id());
		    f.setuser_id(e.user_id());
		    f.setamount(e.amount());
		    f.setrisk_score(e.risk_score());
		    f.setreason(e.reason());

		    // set the status and DateTime
		    f.setstatus("FLAGGED");
		    f.setflagged_at(LocalDateTime.now());

		    //save the entity to Mysql
		    FlaggedTransaction saved = flags.save(f);

		    // covert entity to dto Reasponse
		    return FlaggedReasponse.from(saved);
		}
	
	//listing all flagged Reasponse
	@Override
	@Transactional(readOnly = true)
	public List<FlaggedReasponse> Allflagged() {

	    return flags.findAll()
	            .stream()
	            .map(FlaggedReasponse::from)
	            .toList();
	}
	
	
   //creating a new  case
	@Override
	@Transactional
	public CaseReasponse CreateCase(CreateCaseRequest r)
	{
		
	FlaggedTransaction f=flags.findBytransaction_id(r.transaction_id()).orElseThrow(()->new NotFoundException("Flagged Transaction is not found")); //finding the flag transaction if it is present stored in flaggedtransaction f and not find means show notfoundexception
	
	if(cases.findBytransaction_id(r.transaction_id()).isPresent()) //finding the case if case present throw conflict exception otherwise execute new statement.
	{
		throw new ConflictExecption("Case already exists for transaction");
	}
	
	InvestigationCases in = new InvestigationCases(); //creating new statement
		
	in.setFlaggedTransaction(f);  
	
    in.settransaction_id(f.gettransaction_id());
    in.setinvestigator_id(r.investigator_id());
    in.setcase_status("OPEN");
    in.setRemarks(r.remarks());
    in.setCreatedAt(LocalDateTime.now());
    

	 InvestigationCases savedCase=cases.save(in);
	 
	 return CaseReasponse.from(savedCase);
	
	
	}
	//listing all cases
	@Override
	@Transactional(readOnly = true)
	public List<CaseReasponse> AllCases()
	{
		return cases.findAll()
				.stream()
				.map(CaseReasponse::from)
				.toList();
	}

	@Override
	@Transactional
	public CaseReasponse Updatecase(int id, UpdatecaseRequest r)
	{
		InvestigationCases c=cases.findById(id).orElseThrow(()->new NotFoundException("Case not found"));//finding the case
		
		if("CLOSED".equals(c.getcase_status()))
		{
			throw new ConflictExecption("Closed case cannot be changed");//we check in the caseinvestigation casestatus is close it throw exception other process or open means execute
		}
		if("CLOSED".equals(r.case_status()))
				
		{
		throw new ConflictExecption( "Use outcome endpoint to close case");//here we check the status recieved by client
		           
		}
		
		InvestigationValidator.validateCaseStatus(r.case_status());//validation case_status
	
		c.setcase_status(r.case_status());
		c.setinvestigator_id(r.investigator_id());//set the information recieved by client to reasponse dto
		c.setRemarks(r.remarks());
		
		return CaseReasponse.from(cases.save(c));//return to dto
	}
	

	@Override
	@Transactional
	public OutcomeReasponse RecordOutcome(RecordOutcomeRequest r) 
	{
		InvestigationCases c=cases.findById(r.case_id()) .orElseThrow(() ->new NotFoundException("Case not found"));//find the case
		
		
      if("CLOSED".equals(c.getcase_status()))
      {
    	  throw new ConflictExecption("Case already closed");//we check closed cases
      }
      
      InvestigationValidator.validateInvestigationResult(r.result());//validation case result
      
      InvestigationOutcomes o=new InvestigationOutcomes(); //creating object of outcomeclass
      
      o.setInvestigationCase(c);
      o.setresult(r.result());        //here we set the value what we get from client
      o.setremarks(r.remarks());
      o.setcompleted_at(LocalDateTime.now());
      
      
      InvestigationOutcomes saved=outcomes.save(o); //save the values
      
      c.setcase_status("CLOSED");//set case_status
      c.setRemarks(r.remarks());
      cases.save(c);
      
      return OutcomeReasponse.from(saved);  //return to dto
      
      
      
      
	}
}
