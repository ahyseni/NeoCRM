package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.UserDto;
import com.mrkt.admin.map.account.UserGMapper;
import com.mrkt.admin.model.account.UserG;
import com.mrkt.admin.repository.account.UserGRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrktpUserService extends AccountDataService<UserG, UserDto> {


    public MrktpUserService(UserGRepository repository, UserGMapper mapper) {
//        setRepositoryBase(repository);
//        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
//        if (filter != null) {
//            QUser user = QUser.user;
//            predicate = user.nameEN.startsWith(filter);
//        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
//        QUser user = QUser.user;
//        Predicate predicate = user.active.eq(true);

        return super.findAll(null)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}