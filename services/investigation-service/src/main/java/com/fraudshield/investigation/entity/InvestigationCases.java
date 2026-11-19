package com.fraudshield.investigation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Investigation_Cases")
public class InvestigationCases 
{
	
	
    @Id
    @Column(name = "case_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//pk 
    private int case_id;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="flagged_id",referencedColumnName="flagged_id")//fk and we creating reference of flagged transaction in investigation case
	private FlaggedTransaction flaggedTransaction;
	
    
    @Column(name="transaction_id")
    private int transaction_id;

    @Column(name = "investigator_id")
    private int investigator_id;

    @Column(name = "case_status")
    private String case_status;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
 
	public int getcase_id() {
        return case_id;
    }

    public void setcase_id(int case_id) {
        this.case_id = case_id;
    }


    public int gettransaction_id()
    {
    	return transaction_id;
    }
    public void settransaction_id(int transaction_id)
    {
    	this.transaction_id=transaction_id;
    }
 
    public int getinvestigator_id() {
        return investigator_id;
    }

    public void setinvestigator_id(int investigator_id) {
        this.investigator_id = investigator_id;
    }

    // Getter and Setter for caseStatus
    public String getcase_status() {
        return case_status;
    }

    public void setcase_status(String case_status) {
        this.case_status=case_status;
    }

  
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setFlaggedTransaction(FlaggedTransaction f)
    {
        this.flaggedTransaction = f;
    }
    public FlaggedTransaction getFlaggedTransaction()
    {
    	return flaggedTransaction;
    }
}

