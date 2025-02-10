package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpProductSpecificationPropertyDto;
import com.mrkt.admin.map.masterdata.MrktpProductSpecificationPropertyMapper;
import com.mrkt.admin.model.masterdata.ProductSpecificationProperty;
import com.mrkt.admin.model.masterdata.QProductSpecificationProperty;
import com.mrkt.admin.repository.masterdata.MrktpProductSpecificationPropertyRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSpecificationPropertyService extends MasterDataService<ProductSpecificationProperty, MrktpProductSpecificationPropertyDto> {


    public ProductSpecificationPropertyService(MrktpProductSpecificationPropertyRepository repository, MrktpProductSpecificationPropertyMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QProductSpecificationProperty qMrktpProductSpecificationProperty = QProductSpecificationProperty.productSpecificationProperty;
            predicate = qMrktpProductSpecificationProperty.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QProductSpecificationProperty qMrktpProductSpecificationProperty = QProductSpecificationProperty.productSpecificationProperty;
        Predicate predicate = qMrktpProductSpecificationProperty.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}
