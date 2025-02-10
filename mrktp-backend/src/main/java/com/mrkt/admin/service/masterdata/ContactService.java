package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.ContactDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.masterdata.ContactMapper;
import com.mrkt.admin.model.masterdata.Contact;
import com.mrkt.admin.model.masterdata.QContact;
import com.mrkt.admin.repository.masterdata.ContactRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService extends MasterDataService<Contact, ContactDto> {

    public ContactService(ContactRepository repository, ContactMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QContact qContact = QContact.contact;
            predicate = qContact.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QContact qContact = QContact.contact;
        Predicate predicate = qContact.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
