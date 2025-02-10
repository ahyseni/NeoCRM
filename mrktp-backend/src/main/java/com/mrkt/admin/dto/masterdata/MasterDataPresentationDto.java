package com.mrkt.admin.dto.masterdata;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MasterDataPresentationDto extends MultiLanguageBaseDto {

    public MasterDataPresentationDto(String id, String nameEN, Boolean active) {
        setNameEN(nameEN);
        setActive(active);
        setId(id);
    }

    public MasterDataPresentationDto(String id, String nameEN,
                                     String nameNL, String nameGR, String nameDE, String nameAL, String nameFR, Boolean active) {
        super(id, nameEN, nameNL, nameGR, nameDE, nameAL, nameFR, active);
    }
}
