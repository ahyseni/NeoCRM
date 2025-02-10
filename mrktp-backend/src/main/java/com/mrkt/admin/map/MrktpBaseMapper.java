package com.mrkt.admin.map;

import com.mrkt.admin.model.masterdata.User;

import java.util.List;

/*
 *@param <E> the entity
 *@param <D> the dto
 */
public interface MrktpBaseMapper<E,D> {

    /*
     *@param entity the source entity
     *@return the mapped dto
     */
    D mapToDto(E entity);

    /*
     *@param entity the source entity
     *@return the mapped dto
     */
    List<D> mapToDto(List<E> entity);
    /*
     *@param dto the source entity
     *@return the mapped entity
     */
    E mapToEntity(D dto);

    /*
     *@param dto list
     *@return the mapped entities list
     */
    List<E> mapToEntity(List<D> entity);


//    /**
//     * Default mapping method to convert a {@code User} object to a String.
//     * This method is used when a property of type {@code User} (e.g., assignedTo or createdBy)
//     * needs to be mapped to a String (usually representing the user's ID).
//     *
//     * @param user the User entity
//     * @return the user's ID as a String, or null if the user is null
//     */
//    default String map(User user) {
//        return (user == null) ? null : user.getId();
//    }
}
