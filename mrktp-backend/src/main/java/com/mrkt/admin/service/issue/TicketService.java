package com.mrkt.admin.service.issue;

import com.mrkt.admin.dto.issue.TicketDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.map.issue.TicketMapper;
import com.mrkt.admin.model.issue.QTicket;
import com.mrkt.admin.model.issue.Ticket;
import com.mrkt.admin.repository.issue.TicketRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService extends MasterDataService<Ticket, TicketDto> {

    public TicketService(TicketRepository repository, TicketMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QTicket qTicket = QTicket.ticket;
            predicate = qTicket.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QTicket qTicket = QTicket.ticket;
        Predicate predicate = qTicket.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
