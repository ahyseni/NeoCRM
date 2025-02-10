package com.mrkt.admin.service.account;

import com.mrkt.admin.dto.account.AddressDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.account.AddressMapper;
import com.mrkt.admin.model.account.Address;
import com.mrkt.admin.model.account.QAddress;
import com.mrkt.admin.repository.account.AddressRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MrktpAddressService extends AccountDataService<Address, AddressDto> {


    public MrktpAddressService(AddressRepository repository, AddressMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QAddress address = QAddress.address;
            predicate = address.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QAddress address = QAddress.address;
        Predicate predicate = address.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}