package com.mrkt.admin.map.employee;

import com.mrkt.admin.dto.employees.EmployeeDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.employees.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, JobPositionMapper.class})
public interface EmployeeMapper extends MrktpBaseMapper<Employee, EmployeeDto> {


}
