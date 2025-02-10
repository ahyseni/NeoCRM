package com.mrkt.admin.service.order;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.order.OrderItemsDto;
import com.mrkt.admin.map.order.OrderItemsMapper;
import com.mrkt.admin.model.order.OrderItems;
import com.mrkt.admin.model.order.QOrderItems;
import com.mrkt.admin.repository.order.OrderItemsRepository;
import com.mrkt.admin.service.masterdata.MasterDataService;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemsService extends MasterDataService<OrderItems, OrderItemsDto> {

    public OrderItemsService(OrderItemsRepository repository, OrderItemsMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QOrderItems qOrderItems = QOrderItems.orderItems;
            predicate = qOrderItems.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QOrderItems qOrderItems = QOrderItems.orderItems;
        Predicate predicate = qOrderItems.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }

}
