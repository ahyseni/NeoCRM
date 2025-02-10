package com.mrkt.admin.web.api.v1;

import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class BaseController {

    protected final static String Response = "Not available yet!";
    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseBase> handleMissingParams(MissingServletRequestParameterException exception) {
        ResponseBase validationError = buildErrorResponse(exception, Result.BAD_REQUEST);
        validationError.setDescription("The " + "parameter fo the request is required");
        logger.error(validationError.getResult()+" : "+validationError.getDescription());

        return new ResponseEntity<>(validationError,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseBase> handleValidationException(IllegalArgumentException exception) {
        ResponseBase validationError = buildErrorResponse(exception, Result.BAD_REQUEST);
        logger.error(exception.getClass()+" Cause "+exception.getCause()+" Message "+exception.getMessage()+" Stack Trace "+exception.getStackTrace());

        return new ResponseEntity<>(validationError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationServiceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseBase> handleAuthorizationException(AuthorizationServiceException exception) {
        ResponseBase validationError = buildErrorResponse(exception, Result.UNAUTHORIZED);
        logger.error(exception.getClass()+" Cause "+exception.getCause()+" Message "+exception.getMessage()+" Stack Trace "+exception.getStackTrace());

        return new ResponseEntity<>(validationError,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseBase> handleAuthorizationException(NotFoundException exception) {
        ResponseBase validationError = buildErrorResponse(exception, Result.NOT_FOUND);
        logger.error(exception.getClass()+" Cause "+exception.getCause()+" Message "+exception.getMessage()+" Stack Trace "+exception.getStackTrace());

        return new ResponseEntity<>(validationError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseBase> handleInternalServerError(Exception exception) {
        ResponseBase validationError = buildErrorResponse(exception, Result.GENERIC_ERROR);
        logger.error(exception.getClass()+" Cause "+exception.getCause()+" Message "+exception.getMessage()+" Stack Trace "+exception.getStackTrace());

        return new ResponseEntity<>(validationError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ResponseBase buildErrorResponse(Exception exception, Result result){

        ResponseBase responseBase=new ResponseBase();
        responseBase.setResult(result);
        responseBase.setDescription(exception.getMessage());

        return responseBase;
    }

}
