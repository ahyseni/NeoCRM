package com.mrkt.admin.web.api.v1.validator.masterdata;

import com.mrkt.admin.exception.MrktpSubError;
import com.mrkt.admin.model.masterdata.MrktpMasterdataBaseEntity;
import com.mrkt.admin.repository.MrktpRepositoryBase;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MasterDataValidator<E extends MrktpMasterdataBaseEntity> {

    private MrktpRepositoryBase repositoryBase;

    private List<MrktpSubError> subErrorList;

    /**
     *
     * @param predicates
     * @param fields
     * @param values
     * @return
     */
    List<MrktpSubError> validateUniqueFields(Predicate[] predicates,String[] fields,String[] values)
    {
        subErrorList=new ArrayList<>();

        for(int i=0;i< predicates.length;i++)
            findResource(predicates[i],fields[i],values[i]);

        return subErrorList;
    }


    /**
     *
     * @param predicate
     * @param field
     * @param value
     * @return
     */
    List<MrktpSubError> validateUniqueField(Predicate predicate, String field, String value)
    {
        subErrorList=new ArrayList<>();

            findResource(predicate,field,value);

        return subErrorList;
    }

    /**
     * 
     * @param predicate
     * @param field
     * @param value
     */

    private void findResource(Predicate predicate, String field, String value) {

        repositoryBase.findOne(predicate).
                ifPresent(e->buildError(field,value));
    }

    private void buildError(String field, String value) {
        subErrorList.add(new MrktpSubError(null,field,value,"Already exist in DB"));
    }


}
