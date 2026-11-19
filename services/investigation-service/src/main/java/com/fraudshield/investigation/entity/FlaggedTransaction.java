package com.fraudshield.investigation.entity;

import java.time.Instant;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Flagged_Transaction")
public class FlaggedTransaction 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="flagged_id")
private int flagged_id;

@Column(name="transaction_id")
private int transaction_id;

@Column(name="user_id")
private int user_id;

@Column(name="amount")
private int amount;                     //Attribute we use to store data 
                                        //@entity:it is java class it will store data in proper table like relational database/@table:mapping entity class to database table
@Column(name="risk_score")              //@id:primary key @column:mapping attibute to database table column
private int risk_score;

@Column(name="reason")
private String reason;

@Column(name="status")
private String status;

@Column(name="flagged_at")
private LocalDateTime flagged_at;


public int getflagged_id()                   //here we are using getter and setter methode to get a value and set value we use getter and setter
{
	return flagged_id;
}
public void setFlagged_id(int flagged_id)
{
	this.flagged_id=flagged_id;
}
public int gettransaction_id()
{
	return transaction_id;
}
public void settransaction_id(int transaction_id)
{
	this.transaction_id=transaction_id;
}
public int getuser_id()
{
	return user_id;
}
public void setuser_id(int user_id)
{
	this.user_id=user_id;
}
public int getamount()
{
	return amount;
}
public void setamount(int amount)
{
	this.amount=amount;
}
public int getrisk_score()
{
	return risk_score;
}
public void setrisk_score(int risk_score)
{
	this.risk_score=risk_score;
}
public String getreason()
{
	return reason;
}
public void setreason(String reason)
{
	this.reason=reason;
}
public String getstatus()
{
	return status;
}
public void setstatus(String status)
{
	this.status=status;
}
public LocalDateTime getflagged_at()
{
	return flagged_at;
}
public void setflagged_at(LocalDateTime flagged_at)
{
	this.flagged_at=flagged_at;
}
}





