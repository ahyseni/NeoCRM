package com.mrkt.admin.web.api.v1.validator.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.exception.MrktpSubError;
import com.mrkt.admin.model.masterdata.Group;
import com.mrkt.admin.model.masterdata.QGroup;
import com.mrkt.admin.repository.masterdata.MrktpGroupRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupValidator extends MasterDataValidator<Group> {

    public GroupValidator(MrktpGroupRepository repository) {
        setRepositoryBase(repository);

    }

    private List<MrktpSubError> validateNames(MrktpGroupDto mrktpGroupDto) {

        QGroup mrktpGroup = QGroup.group;

        Predicate[] predicates = new Predicate[]{
                mrktpGroup.nameEN.eq(mrktpGroupDto.getNameEN()),
                mrktpGroup.nameNL.eq(mrktpGroupDto.getNameNL()),
                mrktpGroup.nameGR.eq(mrktpGroupDto.getNameGR()),
                mrktpGroup.nameDE.eq(mrktpGroupDto.getNameDE()),
                mrktpGroup.nameAL.eq(mrktpGroupDto.getNameAL()),
                mrktpGroup.nameFR.eq(mrktpGroupDto.getNameFR())

        };
        String[] fields = new String[]{"NameEN", "NameNL", "NameGR", "NameDE", "NameAL", "NameFR"};
        String[] values = new String[]{mrktpGroupDto.getNameEN(), mrktpGroupDto.getName()};

        return super.validateUniqueFields(predicates, fields, values);
    }
//
//private void buildError(List<MrktpSubError> errors)throws MrktpValidationException
//{throw new MrktpValidationException(MrktpError.builder()
//        .status(HttpStatus.BAD_REQUEST)
//        .subErrorList(errors)
//        .time(Instant.now())
//        .build()
//);


}
