package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpSectionPropertyDto;
import com.mrkt.admin.map.masterdata.MrktpSectionPropertyMapper;
import com.mrkt.admin.model.masterdata.SectionProperty;
import com.mrkt.admin.model.masterdata.QSectionProperty;
import com.mrkt.admin.repository.masterdata.MrktpSectionPropertyRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionPropertyService extends MasterDataService<SectionProperty, MrktpSectionPropertyDto> {

    public SectionPropertyService(MrktpSectionPropertyRepository repository, MrktpSectionPropertyMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QSectionProperty mrktpSectionProperty = QSectionProperty.sectionProperty;
            predicate = mrktpSectionProperty.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QSectionProperty mrktpSectionProperty = QSectionProperty.sectionProperty;
        Predicate predicate = mrktpSectionProperty.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}

