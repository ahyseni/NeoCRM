package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.UserLoginDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.UserLoginMapper;
import com.mrkt.admin.model.account.UserLogin;
import com.mrkt.admin.model.account.QUserLogin;
import com.mrkt.admin.repository.account.UserLoginRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrktpUserLoginService extends AccountDataService<UserLogin, UserLoginDto> {


    public MrktpUserLoginService(UserLoginRepository repository, UserLoginMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QUserLogin userLogin = QUserLogin.userLogin;
            predicate = userLogin.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QUserLogin userLogin = QUserLogin.userLogin;
        Predicate predicate = userLogin.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}