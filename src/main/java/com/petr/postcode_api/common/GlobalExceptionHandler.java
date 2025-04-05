package com.petr.postcode_api.common;

//import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.petr.postcode_api.common.exceptions.PostcodeNotFoundException;

//@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostcodeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handlePostcodeNotFoundException(PostcodeNotFoundException ex) {
        //log.error("Postcode not found: {}", ex.getMessage());
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage());
    }
}
