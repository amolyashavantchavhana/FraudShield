package com.fraudshield.investigation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fraudshield.investigation.entity.InvestigationOutcomes;

public interface InvestigationOutcomesRepository extends JpaRepository<InvestigationOutcomes,Integer>
{

}
