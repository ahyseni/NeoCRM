package com.mrkt.admin.web.api.v1.masterdata;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpSectionPropertyDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.masterdata.SectionCategory;
import com.mrkt.admin.model.masterdata.SectionProperty;
import com.mrkt.admin.repository.masterdata.MrktpSectionCategoryRepository;
import com.mrkt.admin.service.masterdata.SectionCategoryService;
import com.mrkt.admin.service.masterdata.SectionPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Section Property API", tags = "Section Property", description = "Section Property Management")
@RequestMapping(value = {"v1/master-data/groups/{groupId}/sections/{sectionId}/section-categories/{sectionCategoryId}/section-properties",
        "/master-data/groups/{groupId}/sections/{sectionId}/section-categories/{sectionCategoryId}/section-properties"})
@Slf4j
public class SectionPropertyController {

    private final SectionPropertyService sectionPropertyService;

    private final SectionCategoryService sectionCategoryService;

    private final MrktpSectionCategoryRepository sectionCategoryRepository;


    public SectionPropertyController(SectionPropertyService sectionPropertyService,
                                     SectionCategoryService sectionCategoryService,
                                     MrktpSectionCategoryRepository sectionCategoryRepository) {
        this.sectionCategoryRepository = sectionCategoryRepository;
        this.sectionCategoryService = sectionCategoryService;
        this.sectionPropertyService = sectionPropertyService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Section Property in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Section Property is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MrktpSectionPropertyDto sectionPropertyDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
        SectionCategory sectionCategories = sectionCategoryService.findResource(sectionPropertyDto.getMrktpSectionCategoriesID());
        CreateResponseData responseData = sectionPropertyService.create(sectionPropertyDto);
        SectionProperty sectionProperty = sectionPropertyService.findResource(responseData.getId());
        sectionProperty.setCategories(sectionCategories);
        sectionCategories.getMrktpSectionProperties().add(sectionProperty);
        sectionCategoryRepository.save(sectionCategories);

        UriComponents uriComponents = uriComponentsBuilder.
                path("/master-data/groups/" + sectionCategories.getSection().getGroup().getId() + "/sections/" + sectionCategories.
                        getSection().getId() + "/section-categories/" + sectionCategories.getId() + "/section-properties/{id}")
                .buildAndExpand(sectionProperty.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;

    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Section Property in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Section Property is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MrktpSectionPropertyDto sectionPropertyBaseDto) throws MrktpNotFoundException {
        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        sectionPropertyService.update(sectionPropertyBaseDto);
        SectionCategory sectionCategories = sectionCategoryService.findResource(sectionPropertyBaseDto.getMrktpSectionCategoriesID());

        SectionProperty sectionProperty = sectionPropertyService.findResource(id);
        sectionProperty.setCategories(sectionCategories);
        sectionCategories.getMrktpSectionProperties().add(sectionProperty);
        sectionCategoryRepository.save(sectionCategories);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;

    }
//    @Transactional
//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Section Property in Market Place platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Section Property is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id);
//    }


    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search an the Section Property in Market Place platform",response = MrktpSectionPropertyDto.class)
    public ResponseEntity<MrktpSectionPropertyDto> findById(@PathVariable String id)throws MrktpNotFoundException
    {
        return new ResponseEntity<>(sectionPropertyService.findById(id),HttpStatus.OK);

    }

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Section Propertys in Market Place platform",response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page,@RequestParam(required = false) int size,@RequestParam(required = false)
                                                         String direction,@RequestParam(required = false) String property,@RequestParam(required = false)String filter)
    {
        return sectionPropertyService.findAllActive();
    }
    @GetMapping(value="/active",produces = {"application/json"})
    @ApiOperation(value = "List of all the Section Propertys in Market Place platform",response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllSectionProperties()
    {
        return sectionPropertyService.findAllActive();
    }
}
