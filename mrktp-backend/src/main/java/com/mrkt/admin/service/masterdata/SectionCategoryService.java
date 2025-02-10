package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpSectionCategoriesDto;
import com.mrkt.admin.map.masterdata.MrktpSectionCategoryMapper;
import com.mrkt.admin.model.masterdata.SectionCategory;
import com.mrkt.admin.model.masterdata.QSectionCategory;
import com.mrkt.admin.repository.masterdata.MrktpSectionCategoryRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionCategoryService extends MasterDataService<SectionCategory, MrktpSectionCategoriesDto> {

    public SectionCategoryService(MrktpSectionCategoryRepository repository, MrktpSectionCategoryMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QSectionCategory mrktpSectionCategories = QSectionCategory.sectionCategory;
            predicate = mrktpSectionCategories.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QSectionCategory mrktpSectionCategories = QSectionCategory.sectionCategory;
        Predicate predicate = mrktpSectionCategories.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}

