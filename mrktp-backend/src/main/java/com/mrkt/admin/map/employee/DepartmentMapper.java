package com.mrkt.admin.map.employee;

import com.mrkt.admin.dto.employees.DepartmentDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.employees.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface DepartmentMapper extends MrktpBaseMapper<Department, DepartmentDto> {


}
