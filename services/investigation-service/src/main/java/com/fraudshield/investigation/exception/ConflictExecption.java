package com.fraudshield.investigation.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictExecption extends RuntimeException
{
public ConflictExecption(String message)
{
	super(message);
}
}
