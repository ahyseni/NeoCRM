package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.UserGroupDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.UserGroupMapper;
import com.mrkt.admin.model.account.QUserGroup;
import com.mrkt.admin.model.account.UserGroup;
import com.mrkt.admin.repository.account.UserGroupRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGroupService extends MasterDataService<UserGroup, UserGroupDto> {

    public UserGroupService(UserGroupRepository repository, UserGroupMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QUserGroup qUserGroup = QUserGroup.userGroup;
            predicate = qUserGroup.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QUserGroup qUserGroup = QUserGroup.userGroup;
        Predicate predicate = qUserGroup.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
