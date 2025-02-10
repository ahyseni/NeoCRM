package com.mrkt.admin.service.employee;

import com.mrkt.admin.dto.employees.DepartmentDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.employee.DepartmentMapper;
import com.mrkt.admin.model.employees.Department;
import com.mrkt.admin.model.employees.QDepartment;
import com.mrkt.admin.repository.employee.DepartmentRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService extends MasterDataService<Department, DepartmentDto> {

    public DepartmentService(DepartmentRepository repository, DepartmentMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QDepartment qDepartment = QDepartment.department;
            predicate = qDepartment.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QDepartment qDepartment = QDepartment.department;
        Predicate predicate = qDepartment.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
