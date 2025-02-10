package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpProductSpecificationDto;
import com.mrkt.admin.map.masterdata.MrktpProductSpecificationMapper;
import com.mrkt.admin.model.masterdata.ProductSpecification;
import com.mrkt.admin.model.masterdata.QProductSpecification;
import com.mrkt.admin.repository.masterdata.MrktpProductSpecificationsRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSpecificationService extends MasterDataService<ProductSpecification, MrktpProductSpecificationDto> {


    public ProductSpecificationService(MrktpProductSpecificationsRepository repository, MrktpProductSpecificationMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QProductSpecification productSpecification = QProductSpecification.productSpecification;
            predicate = productSpecification.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QProductSpecification productSpecification = QProductSpecification.productSpecification;
        Predicate predicate = productSpecification.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}
