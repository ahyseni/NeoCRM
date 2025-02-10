package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.MrktpBaseDto;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.MrktpMasterdataBaseEntity;
import com.mrkt.admin.repository.MrktpRepositoryBase;
import com.mrkt.admin.repository.MrktpRepositoryUtils;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
@Getter
@Setter
public abstract class MasterDataService<E extends MrktpMasterdataBaseEntity, D extends MrktpBaseDto>
        implements MrktpMasterdataBaseService<E,D> {

 private MrktpRepositoryBase<E> repositoryBase;

 private MrktpBaseMapper<E,D> mapper;

 @Override
 public CreateResponseData create(D dto)
 {
     E entity=mapper.mapToEntity(dto);
     return  new CreateResponseData(repositoryBase.save(entity).getId());
 }

 @Override
 public void update(D dto)throws MrktpNotFoundException {
     findResource(dto.getId());
     repositoryBase.save(mapper.mapToEntity(dto));
 }
    @Override
 public Page<E> findPaginated(int page, int size, String direction, String property, Predicate predicate){
     Sort sort=new Sort(Sort.Direction.fromString(direction),property);
     Pageable pageable= PageRequest.of(page,size,sort);
     return repositoryBase.findAll(predicate,pageable);
 }
    @Override
 public D findById(String id)throws MrktpNotFoundException{
        return mapper.mapToDto(findResource(id));
    }

    @Override
    public E findResource(String id) throws MrktpNotFoundException {
        return (E) MrktpRepositoryUtils.findById(repositoryBase, id);
    }

    @Override
    public List<E> findAll(Predicate predicate) {
        return repositoryBase.findAll(predicate);
    }

    /**
     * New helper method: returns a paginated result of DTOs rather than entities.
     */
    public Page<D> findPaginatedDto(int page, int size, String direction, String property, Predicate predicate) {
        return findPaginated(page, size, direction, property, predicate)
                .map(entity -> mapper.mapToDto(entity));
    }
}
