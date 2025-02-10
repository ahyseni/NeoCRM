package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.MrktpBaseDto;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.masterdata.MrktpMasterdataBaseEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;

import java.util.List;
public interface MrktpMasterdataBaseService <E extends MrktpMasterdataBaseEntity,D extends MrktpBaseDto> {

    CreateResponseData create(D dto);

    /**
     *
     * @param dto the DTO containing all the information regarding the resource to be updated
     * @throws MrktpNotFoundException in case the resource is not present in the DB
     */

    void update(D dto)throws MrktpNotFoundException;

    /**
     *
     * @param page the page requested
     * @param size the size requested
     * @param direction the direction requested ASC or DESC
     * @param property the property with which to sort
     * @param predicate
     * @return a spring data page
     */

    Page<E> findPaginated(int page, int size, String direction, String property, Predicate predicate);

    /**
     *
     * @param id the id of the resource
     * @return a DTO of the resource type
     * @throws MrktpNotFoundException in case the resource is not present in the DB
     */
    D findById(String id)throws MrktpNotFoundException;


    E findResource(String id)throws MrktpNotFoundException;


    List<E> findAll(Predicate predicate);
}
