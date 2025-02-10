package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.UserTypeDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.UserTypeMapper;
import com.mrkt.admin.model.account.QUserType;
import com.mrkt.admin.model.account.UserType;
import com.mrkt.admin.repository.account.UserTypeRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrktpUserTypeService extends AccountDataService<UserType, UserTypeDto> {


    public MrktpUserTypeService(UserTypeRepository repository, UserTypeMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QUserType userType = QUserType.userType;
            predicate = userType.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QUserType userType = QUserType.userType;
        Predicate predicate = userType.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}