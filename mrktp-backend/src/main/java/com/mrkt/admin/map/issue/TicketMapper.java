package com.mrkt.admin.map.issue;

import com.mrkt.admin.dto.issue.TicketDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.ContactMapper;
import com.mrkt.admin.model.issue.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ContactMapper.class})
public interface TicketMapper extends MrktpBaseMapper<Ticket, TicketDto> {


}
