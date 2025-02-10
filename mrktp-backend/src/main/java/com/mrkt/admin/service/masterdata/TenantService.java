package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.TenantDto;
import com.mrkt.admin.map.masterdata.TenantMapper;
import com.mrkt.admin.model.masterdata.QTenant;
import com.mrkt.admin.model.masterdata.Tenant;
import com.mrkt.admin.repository.masterdata.TenantRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TenantService extends MasterDataService<Tenant, TenantDto> {

    public TenantService(TenantRepository repository, TenantMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }

    public Page<TenantDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null && !filter.isEmpty()) {
            QTenant qTenant = QTenant.tenant;
            predicate = qTenant.tenantName.startsWithIgnoreCase(filter);
        }
        // This call uses the helper method from the base service
        return findPaginatedDto(page, size, direction, property, predicate);
    }
}
