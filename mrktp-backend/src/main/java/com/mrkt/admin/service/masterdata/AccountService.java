package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.AccountDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.masterdata.AccountMapper;
import com.mrkt.admin.model.masterdata.Account;
import com.mrkt.admin.model.masterdata.QAccount;
import com.mrkt.admin.repository.masterdata.AccountRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService extends MasterDataService<Account, AccountDto> {

    public AccountService(AccountRepository repository, AccountMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QAccount qAccount = QAccount.account;
            predicate = qAccount.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QAccount qAccount = QAccount.account;
        Predicate predicate = qAccount.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
