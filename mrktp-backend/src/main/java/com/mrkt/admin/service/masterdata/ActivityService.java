package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.ActivityDto;
import com.mrkt.admin.map.masterdata.ActivityMapper;
import com.mrkt.admin.model.masterdata.Activity;
import com.mrkt.admin.model.masterdata.QActivity;
import com.mrkt.admin.repository.masterdata.ActivityRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ActivityService extends MasterDataService<Activity, ActivityDto> {

    public ActivityService(ActivityRepository repository, ActivityMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }

    public Page<ActivityDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null && !filter.isEmpty()) {
            QActivity qActivity = QActivity.activity;
            predicate = qActivity.activityType.startsWithIgnoreCase(filter);
        }
        // method to return DTOs
        return findPaginatedDto(page, size, direction, property, predicate);
    }
}
