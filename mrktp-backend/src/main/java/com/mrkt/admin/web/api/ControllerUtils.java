package com.mrkt.admin.web.api;

import com.mrkt.admin.dto.MrktpBaseDto;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.service.MrktpBaseService;
import com.mrkt.admin.service.masterdata.MasterDataService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Utilities used by Mrktp controllers
 */

public class ControllerUtils {
    public ControllerUtils() {
    }

    public static <T extends Serializable>ResponseEntity<T> listResource(List<T> resources){

        ResponseEntity<T> response;
        if(resources!=null && !resources.isEmpty())
        {
            response=ResponseEntity.status(HttpStatus.OK).body(resources.get(0));
        }else{
            response=ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return response;
    }

    public static <T extends Serializable>ResponseEntity<T> listResource(T resource){

        ResponseEntity<T> response;
        if(resource!=null)
        {
            response=ResponseEntity.status(HttpStatus.OK).body(resource);
        }else{
            response=ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return response;
    }

    public static <T extends Serializable>ResponseEntity<T> listResource(T resource, HttpHeaders headers){

        ResponseEntity<T> response;
        if(resource!=null)
        {
            response=ResponseEntity.status(HttpStatus.OK).headers(headers).body(resource);
        }else{
            response=ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(null);
        }
        return response;
    }
    public static <T extends Serializable> ResponseEntity<Iterable> listResources(List<T> resources){

        ResponseEntity<Iterable> response;
        if(resources!=null && !resources.isEmpty())
        {
            response=ResponseEntity.status(HttpStatus.OK).body(resources);
        }else{
            response=ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return response;
    }
    public static <K extends Serializable, V extends Serializable> ResponseEntity<Map> listResources(Map<K,V> resources){

        ResponseEntity<Map> response;
        if(resources!=null && !resources.isEmpty())
        {
            response=ResponseEntity.status(HttpStatus.OK).body(resources);
        }else{
            response=ResponseEntity.status(HttpStatus.NOT_FOUND).body(resources);
        }
        return response;
    }

    public static ResponseEntity<ResponseBase> delete(Serializable id, MrktpBaseService mrktpBaseService)throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
        mrktpBaseService.delete(id);
        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response=ResponseEntity.status(HttpStatus.OK).body(responseBase);

        return response;
    }
    public static ResponseEntity<ResponseBase> update(MasterDataService masterDataService, MrktpBaseDto mrktpBaseDto,String id)throws MrktpNotFoundException {

        mrktpBaseDto.setId(id);
        masterDataService.update(mrktpBaseDto);
        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        return  ResponseEntity.status(HttpStatus.OK).body(responseBase);

    }
}
