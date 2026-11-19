package com.fraudshield.investigation.mapper;

import com.fraudshield.investigation.dto.response.FlaggedReasponse;
import com.fraudshield.investigation.entity.FlaggedTransaction;

public class FlaggedTransactionMapper
{
private FlaggedTransactionMapper()//prevent to create obj of mapper class
{
	
}
public static FlaggedReasponse toReasponse(FlaggedTransaction f)
{
	return FlaggedReasponse.from(f);
}
}
