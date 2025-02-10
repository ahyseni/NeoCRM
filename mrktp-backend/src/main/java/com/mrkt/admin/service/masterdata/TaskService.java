package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.TaskDto;
import com.mrkt.admin.map.masterdata.TaskMapper;
import com.mrkt.admin.model.masterdata.QTask;
import com.mrkt.admin.model.masterdata.Task;
import com.mrkt.admin.repository.masterdata.TaskRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends MasterDataService<Task, TaskDto> {

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }

    public Page<TaskDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null && !filter.isEmpty()) {
            QTask qTask = QTask.task;
            predicate = qTask.title.startsWithIgnoreCase(filter);
        }
        return findPaginatedDto(page, size, direction, property, predicate);
    }
}
