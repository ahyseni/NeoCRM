package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.MerchantDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.MerchantMapper;
import com.mrkt.admin.model.account.Merchant;
import com.mrkt.admin.model.account.QMerchant;
import com.mrkt.admin.repository.account.MerchantRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrktpMerchantService extends AccountDataService<Merchant, MerchantDto> {


    public MrktpMerchantService(MerchantRepository repository, MerchantMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QMerchant merchant = QMerchant.merchant;
            predicate = merchant.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QMerchant merchant = QMerchant.merchant;
        Predicate predicate = merchant.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}