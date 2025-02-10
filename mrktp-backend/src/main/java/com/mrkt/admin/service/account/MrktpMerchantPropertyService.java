package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.MerchantPropertyDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.MerchantPropertyMapper;
import com.mrkt.admin.model.account.MerchantProperty;
import com.mrkt.admin.model.account.QMerchantProperty;
import com.mrkt.admin.repository.account.MerchantPropertyRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrktpMerchantPropertyService extends AccountDataService<MerchantProperty, MerchantPropertyDto> {


    public MrktpMerchantPropertyService(MerchantPropertyRepository
                                                repository, MerchantPropertyMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QMerchantProperty merchantProperty = QMerchantProperty.merchantProperty;
            predicate = merchantProperty.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QMerchantProperty merchantProperty = QMerchantProperty.merchantProperty;
        Predicate predicate = merchantProperty.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}