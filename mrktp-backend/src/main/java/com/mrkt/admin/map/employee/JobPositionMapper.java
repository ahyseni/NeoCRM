package com.mrkt.admin.map.employee;

import com.mrkt.admin.dto.employees.JobPositionDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.employees.JobPosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface JobPositionMapper extends MrktpBaseMapper<JobPosition, JobPositionDto> {


}
