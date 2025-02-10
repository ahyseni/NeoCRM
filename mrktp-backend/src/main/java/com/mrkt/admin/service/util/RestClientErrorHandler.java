package com.mrkt.admin.service.util;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
public class RestClientErrorHandler implements ResponseErrorHandler {


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series()== CLIENT_ERROR ||
                response.getStatusCode().series()== SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }
}
