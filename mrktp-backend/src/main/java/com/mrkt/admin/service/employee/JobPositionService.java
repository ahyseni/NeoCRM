package com.mrkt.admin.service.employee;

import com.mrkt.admin.dto.employees.JobPositionDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.employee.JobPositionMapper;
import com.mrkt.admin.model.employees.JobPosition;
import com.mrkt.admin.model.employees.QJobPosition;
import com.mrkt.admin.repository.employee.JobPositionRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPositionService extends MasterDataService<JobPosition, JobPositionDto> {

    public JobPositionService(JobPositionRepository repository, JobPositionMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QJobPosition qJobPosition = QJobPosition.jobPosition;
            predicate = qJobPosition.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QJobPosition qJobPosition = QJobPosition.jobPosition;
        Predicate predicate = qJobPosition.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
