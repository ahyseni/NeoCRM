package com.mrkt.admin.map;

import com.mrkt.admin.model.masterdata.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class UserIdMapper {

    /**
     * Converts a User object to its id as a String.
     */
    public String map(User user) {
        return (user == null) ? null : user.getId();
    }

}
