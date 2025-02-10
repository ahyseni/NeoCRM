package com.mrkt.admin.service.masterdata;

import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpProductQuoteDto;
import com.mrkt.admin.map.masterdata.MrktpProductQuoteMapper;
import com.mrkt.admin.model.masterdata.ProductQuote;
import com.mrkt.admin.model.masterdata.QProductQuote;
import com.mrkt.admin.repository.masterdata.MrktpProductQuoteRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductQuoteService extends MasterDataService<ProductQuote, MrktpProductQuoteDto> {


    public ProductQuoteService(MrktpProductQuoteRepository repository, MrktpProductQuoteMapper mapper) {
        setRepositoryBase(repository);
        setMapper(mapper);
    }


    public Page<MasterDataPresentationDto> findPaginated(int page, int size, String direction, String property, String filter) {
        Predicate predicate = null;
        if (filter != null) {
            QProductQuote productQuote = QProductQuote.productQuote;
            predicate = productQuote.nameEN.startsWith(filter);
        }

        return super.findPaginated(page, size, direction, property, predicate)
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()));
    }


    public List<MasterDataPresentationDto> findAllActive() {
        QProductQuote productQuote = QProductQuote.productQuote;
        Predicate predicate = productQuote.active.eq(true);

        return super.findAll(predicate)
                .stream()
                .map(entity -> new MasterDataPresentationDto(entity.getId(), entity.getNameEN(), entity.getActive()))
                .collect(Collectors.toList());

    }
}