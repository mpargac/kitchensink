package com.kitchensink.kitchensink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Member email already exists")
public class MemberEmailExistsException extends RuntimeException {
}
