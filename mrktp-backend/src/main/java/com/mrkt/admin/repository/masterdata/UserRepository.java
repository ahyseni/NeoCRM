package com.mrkt.admin.repository.masterdata;

import com.mrkt.admin.model.masterdata.User;
import com.mrkt.admin.repository.MrktpRepositoryBase;

import java.util.Optional;

public interface UserRepository extends MrktpRepositoryBase<User> {
    // additional query methods
    Optional<User> findByEmailAndTenantId(String email, Long tenantId);
}
