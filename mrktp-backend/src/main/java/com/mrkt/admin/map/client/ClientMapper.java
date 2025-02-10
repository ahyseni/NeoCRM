package com.mrkt.admin.map.client;

import com.mrkt.admin.dto.client.ClientDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.client.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends MrktpBaseMapper<Client, ClientDto> {


}
