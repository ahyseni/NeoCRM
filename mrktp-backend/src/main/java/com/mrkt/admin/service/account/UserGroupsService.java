package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.UserGroupsDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.UserGroupsMapper;
import com.mrkt.admin.model.account.QUserGroups;
import com.mrkt.admin.model.account.UserGroups;
import com.mrkt.admin.repository.account.UserGroupsRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserGroupsService extends MasterDataService<UserGroups, UserGroupsDto> {

    public UserGroupsService(UserGroupsRepository repository, UserGroupsMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QUserGroups qUserGroups = QUserGroups.userGroups;
            predicate = qUserGroups.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QUserGroups qUserGroups = QUserGroups.userGroups;
        Predicate predicate = qUserGroups.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
