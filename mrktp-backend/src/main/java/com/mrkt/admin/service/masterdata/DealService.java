package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.DealDto;
import com.mrkt.admin.map.masterdata.DealMapper;
import com.mrkt.admin.model.masterdata.Deal;
import com.mrkt.admin.model.masterdata.QDeal;
import com.mrkt.admin.repository.masterdata.DealRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DealService extends MasterDataService<Deal, DealDto> {

    public DealService(DealRepository repository, DealMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }

    public Page<DealDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null && !filter.isEmpty()) {
            QDeal qDeal = QDeal.deal;
            predicate = qDeal.dealName.startsWithIgnoreCase(filter);
        }
        return findPaginatedDto(page, size, direction, property, predicate);
    }
}
