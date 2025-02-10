package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.OpportunityDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Opportunity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ContactMapper.class})
public interface OpportunityMapper extends MrktpBaseMapper<Opportunity, OpportunityDto> {


}
