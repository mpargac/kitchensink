package com.kitchensink.kitchensink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Member not found")
public class MemberNotFoundException extends RuntimeException {

}
