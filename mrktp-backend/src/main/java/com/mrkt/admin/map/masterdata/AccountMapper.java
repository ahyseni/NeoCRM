package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.AccountDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ContactMapper.class})
public interface AccountMapper extends MrktpBaseMapper<Account, AccountDto> {


}
