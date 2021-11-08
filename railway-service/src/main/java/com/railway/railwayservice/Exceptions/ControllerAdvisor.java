package com.railway.railwayservice.Exceptions;

import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleCityNotFoundException(ItemNotFoundException ex, WebRequest request) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseWrapperDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*@ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNodataFoundException(
            NoDataFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "No cities found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }*/

    @ExceptionHandler(RuntimeExceptionHere.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> runTimeExceptionHandling(RuntimeExceptionHere ex, WebRequest request) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseWrapperDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
