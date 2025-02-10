package com.mrkt.admin.service.employee;

import com.mrkt.admin.dto.employees.EmployeeDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.employee.EmployeeMapper;
import com.mrkt.admin.model.employees.Employee;
import com.mrkt.admin.model.employees.QEmployee;
import com.mrkt.admin.repository.employee.EmployeeRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends MasterDataService<Employee, EmployeeDto> {

    public EmployeeService(EmployeeRepository repository, EmployeeMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QEmployee qEmployee = QEmployee.employee;
            predicate = qEmployee.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QEmployee qEmployee = QEmployee.employee;
        Predicate predicate = qEmployee.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
