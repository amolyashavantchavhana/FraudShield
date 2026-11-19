package com.fraudshield.investigation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fraudshield.investigation.entity.FlaggedTransaction;
import com.fraudshield.investigation.entity.InvestigationCases;

public interface FlaggedTransactionRepository extends JpaRepository<FlaggedTransaction,Integer>
{
	 @Query("SELECT e FROM FlaggedTransaction e WHERE e.transaction_id= :transaction_id")
	Optional<FlaggedTransaction> findBytransaction_id(@Param("transaction_id")int transaction_id);

}
