package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpSectionDto;
import com.mrkt.admin.map.masterdata.MrktpSectionMapper;
import com.mrkt.admin.model.masterdata.Section;
import com.mrkt.admin.model.masterdata.QSection;
import com.mrkt.admin.repository.masterdata.MrktpSectionRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService extends MasterDataService<Section, MrktpSectionDto> {

    private MrktpSectionMapper mapper;

    public SectionService(MrktpSectionRepository repository, MrktpSectionMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QSection mrktpSection = QSection.section;
            predicate = mrktpSection.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QSection mrktpSection = QSection.section;
        Predicate predicate = mrktpSection.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}