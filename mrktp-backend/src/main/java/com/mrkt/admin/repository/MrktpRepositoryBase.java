package com.mrkt.admin.repository;

import com.mrkt.admin.model.MrktpBaseEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.lang.NonNull;
import java.util.List;

public interface MrktpRepositoryBase <T extends MrktpBaseEntity> extends JpaRepository<T,String>, QuerydslPredicateExecutor<T> {

    List<T> findAll(@NonNull Predicate predicate);

}
