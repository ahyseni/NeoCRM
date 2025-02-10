package com.mrkt.admin.web.api.v1;

import com.mrkt.admin.exception.MrktpNotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MrktpNotFoundException.class)
    public ResponseEntity<Object> handleMrktpNotFound(MrktpNotFoundException exception, WebRequest request)
    {
        return handleExceptionInternal(exception,exception.getMrktpError(),new HttpHeaders(),
                HttpStatus.NOT_FOUND,request);
    }
    @ExceptionHandler(
            {IllegalArgumentException.class,
            PropertyReferenceException.class}
    )
    public ResponseEntity<Object> handleBadRequest(Exception exception, WebRequest request)
    {
        return handleExceptionInternal(exception,exception.getMessage(),new HttpHeaders(),
                HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaughtException(Exception exception, WebRequest request)
    {
        return handleExceptionInternal(exception,exception.getMessage(),new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,request);
    }


}
