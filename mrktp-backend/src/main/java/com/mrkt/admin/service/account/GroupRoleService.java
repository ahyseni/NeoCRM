package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.GroupRoleDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.GroupRoleMapper;
import com.mrkt.admin.model.account.GroupRole;
import com.mrkt.admin.model.account.QGroupRole;
import com.mrkt.admin.repository.account.GroupRoleRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupRoleService extends MasterDataService<GroupRole, GroupRoleDto> {

    public GroupRoleService(GroupRoleRepository repository, GroupRoleMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QGroupRole groupRole = QGroupRole.groupRole;
            predicate = groupRole.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QGroupRole groupRole = QGroupRole.groupRole;
        Predicate predicate = groupRole.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
