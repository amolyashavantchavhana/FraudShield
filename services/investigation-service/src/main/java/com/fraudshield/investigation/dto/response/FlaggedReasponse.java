package com.fraudshield.investigation.dto.response;



import java.time.LocalDateTime;

import com.fraudshield.investigation.entity.FlaggedTransaction;

public record FlaggedReasponse(Integer flagged_id,Integer transaction_id,Integer user_id,Integer amount,Integer risk_score,String reason,String status,LocalDateTime flagget_at)
{
	public static FlaggedReasponse from(FlaggedTransaction f)
	{
		return new FlaggedReasponse(f.getflagged_id(),f.gettransaction_id(),f.getuser_id(),f.getamount(),f.getrisk_score(),f.getreason(),f.getstatus(),f.getflagged_at());
	}
	
}