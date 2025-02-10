package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.OpportunityDto;
import com.mrkt.admin.map.masterdata.OpportunityMapper;
import com.mrkt.admin.model.masterdata.Opportunity;
import com.mrkt.admin.model.masterdata.QOpportunity;
import com.mrkt.admin.repository.masterdata.OpportunityRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpportunityService extends MasterDataService<Opportunity, OpportunityDto> {

    public OpportunityService(OpportunityRepository repository, OpportunityMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QOpportunity qOpportunity = QOpportunity.opportunity;
            predicate = qOpportunity.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QOpportunity qOpportunity = QOpportunity.opportunity;
        Predicate predicate = qOpportunity.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
