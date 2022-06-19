package com.railway.railwayservice.Exceptions;

import com.railway.railwayservice.dtos.common.ResponseWrapperDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Controller advisor.
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    /**
     * Handle city not found exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleCityNotFoundException(ItemNotFoundException ex, WebRequest request) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseWrapperDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle item quantity exceed exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ItemQuantityException.class)
    public ResponseEntity<Object> handleItemQuantityExceedException(ItemQuantityException ex, WebRequest request) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseWrapperDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle item count decrement exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ItemCountDecrementException.class)
    public ResponseEntity<Object> handleItemCountDecrementException(ItemCountDecrementException ex, WebRequest request) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseWrapperDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Run time exception handling response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(RuntimeExceptionHere.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> runTimeExceptionHandling(RuntimeExceptionHere ex, WebRequest request) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseWrapperDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Item all ready existing exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ItemAllReadyExistingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> ItemAllReadyExistingException(ItemAllReadyExistingException ex, WebRequest request) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseWrapperDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Input not valid exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(InputNotValidException.class)
    public ResponseEntity<Object> inputNotValidException(RuntimeExceptionHere ex, WebRequest request) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto(false, ex.getMessage(), null);
        return new ResponseEntity<>(responseWrapperDto, HttpStatus.BAD_GATEWAY);
    }
}
