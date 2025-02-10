package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.AuditLogDto;
import com.mrkt.admin.map.masterdata.AuditLogMapper;
import com.mrkt.admin.model.masterdata.AuditLog;
import com.mrkt.admin.model.masterdata.QAuditLog;
import com.mrkt.admin.repository.masterdata.AuditLogRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService extends MasterDataService<AuditLog, AuditLogDto> {

    public AuditLogService(AuditLogRepository repository, AuditLogMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }

    public Page<AuditLogDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null && !filter.isEmpty()) {
            QAuditLog qAuditLog = QAuditLog.auditLog;
            predicate = qAuditLog.action.startsWithIgnoreCase(filter);
        }
        // Use the helper method that maps entities to DTOs
        return findPaginatedDto(page, size, direction, property, predicate);
    }
}
