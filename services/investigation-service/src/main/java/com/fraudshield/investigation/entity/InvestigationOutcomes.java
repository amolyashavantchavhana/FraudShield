
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
@Table(name = "Investigation_Outcomes")
public class InvestigationOutcomes {

    @Id
    @Column(name = "outcome_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int outcome_id;

   
    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="case_id",referencedColumnName="case_id")//fk and creating reference of InvestigationCases
    private InvestigationCases InvestigationCase;

   @Column(name="result")
   private String result;
   
   @Column(name="remarks")
   private String remarks;
   
   @Column(name = "completed_at")
    private LocalDateTime completed_at;

   
    public int getoutcome_id() {
        return outcome_id;
    }

    public void setoutcome_id(int outcome_id) {
        this.outcome_id = outcome_id;
    }

  
    
    public String getresult()
    {
    	return result;
    }
    public void setresult(String result)
    {
    	this.result=result;
    }
    public String getremarks()
    {
    	return remarks;
    }
    public void setremarks(String remarks)
    {
    	this.remarks=remarks;
    }

    public LocalDateTime getcompleted_at() {
        return completed_at;
    }

    public void setcompleted_at(LocalDateTime completed_at) {
        this.completed_at = completed_at;
    }
    public void setInvestigationCase(InvestigationCases c)
    {
    	this.InvestigationCase=c;
    }
    public InvestigationCases getInvestigationcase()
    {
    	return InvestigationCase;
    }
}
