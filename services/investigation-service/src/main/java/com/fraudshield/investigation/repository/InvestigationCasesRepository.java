package com.fraudshield.investigation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fraudshield.investigation.entity.InvestigationCases;

public interface InvestigationCasesRepository
        extends JpaRepository<InvestigationCases, Integer> {

	@Query("SELECT c FROM InvestigationCases c WHERE c.transaction_id = :transaction_id")
    Optional<InvestigationCases> findBytransaction_id(
            @Param("transaction_id") int transaction_id);
}