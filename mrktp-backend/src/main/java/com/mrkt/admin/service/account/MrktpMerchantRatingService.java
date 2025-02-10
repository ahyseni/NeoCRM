package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.MerchantRatingDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.MerchantRatingMapper;
import com.mrkt.admin.model.account.MerchantRating;
import com.mrkt.admin.model.account.QMerchantRating;
import com.mrkt.admin.repository.account.MerchantRatingRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrktpMerchantRatingService extends AccountDataService<MerchantRating, MerchantRatingDto> {


    public MrktpMerchantRatingService(MerchantRatingRepository repository, MerchantRatingMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QMerchantRating merchantRating = QMerchantRating.merchantRating;
            predicate = merchantRating.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QMerchantRating merchantRating = QMerchantRating.merchantRating;
        Predicate predicate = merchantRating.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}