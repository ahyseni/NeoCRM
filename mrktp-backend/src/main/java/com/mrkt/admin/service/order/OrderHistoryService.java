package com.mrkt.admin.service.order;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.order.OrderHistoryDto;
import com.mrkt.admin.map.order.OrderHistoryMapper;
import com.mrkt.admin.model.order.OrderHistory;
import com.mrkt.admin.model.order.QOrderHistory;
import com.mrkt.admin.repository.order.OrderHistoryRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHistoryService extends MasterDataService<OrderHistory, OrderHistoryDto> {

    public OrderHistoryService(OrderHistoryRepository repository, OrderHistoryMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QOrderHistory qOrderHistory = QOrderHistory.orderHistory;
            predicate = qOrderHistory.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QOrderHistory qOrderHistory = QOrderHistory.orderHistory;
        Predicate predicate = qOrderHistory.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
