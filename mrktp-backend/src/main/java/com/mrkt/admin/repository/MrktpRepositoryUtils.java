package com.mrkt.admin.repository;

import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.MrktpBaseEntity;

import java.util.Optional;

public class MrktpRepositoryUtils {

    public static MrktpBaseEntity findById(MrktpRepositoryBase repositoryBase,String id) throws MrktpNotFoundException {

        if(id==null)
        {
            throw new IllegalArgumentException("Null id");
        }
        Optional<MrktpBaseEntity> optional=repositoryBase.findById(id);
        if(!optional.isPresent())
        {
            throw new MrktpNotFoundException("No resource found with ID "+id);
        }
        return optional.get();
    }

}
