package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.ContactDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface ContactMapper extends MrktpBaseMapper<Contact, ContactDto> {


}
