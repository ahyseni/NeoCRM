package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.NotificationDto;
import com.mrkt.admin.map.masterdata.NotificationMapper;
import com.mrkt.admin.model.masterdata.Notification;
import com.mrkt.admin.model.masterdata.QNotification;
import com.mrkt.admin.repository.masterdata.NotificationRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends MasterDataService<Notification, NotificationDto> {

    public NotificationService(NotificationRepository repository, NotificationMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }

    public Page<NotificationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null && !filter.isEmpty()) {
            QNotification qNotification = QNotification.notification;
            predicate = qNotification.type.startsWithIgnoreCase(filter);
        }
        return findPaginatedDto(page, size, direction, property, predicate);
    }
}
