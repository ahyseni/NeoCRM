package com.mrkt.admin.web.api.v1.masterdata;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpSectionCategoriesDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.masterdata.Section;
import com.mrkt.admin.model.masterdata.SectionCategory;
import com.mrkt.admin.repository.masterdata.MrktpSectionRepository;
import com.mrkt.admin.service.masterdata.SectionCategoryService;
import com.mrkt.admin.service.masterdata.SectionService;
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
@Api(value = "Section Category API", tags = "Section Category", description = "Section Category Management")
@RequestMapping(value = {"v1/master-data/groups/{groupId}/sections/{sectionId}/section-categories",
        "v1/master-data/groups/{groupId}/sections/{sectionId}/section-categories"})
@Slf4j
public class SectionCategoryController {

    private final SectionCategoryService sectionCategoryService;

    private final SectionService sectionService;

    private final MrktpSectionRepository sectionRepository;

    public SectionCategoryController(SectionCategoryService sectionCategoryService,
                                     SectionService sectionService,
                                     MrktpSectionRepository sectionRepository) {
        this.sectionService = sectionService;
        this.sectionRepository = sectionRepository;
        this.sectionCategoryService = sectionCategoryService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Section Category in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Section Category is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MrktpSectionCategoriesDto sectionCategoriesDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        Section section = sectionService.findResource(sectionCategoriesDto.getSectionId());
        CreateResponseData responseData = sectionCategoryService.create(sectionCategoriesDto);
        SectionCategory sectionCategory = sectionCategoryService.findResource(responseData.getId());
        sectionCategory.setSection(section);
        section.getSectionCategories().add(sectionCategory);
        sectionRepository.save(section);

        UriComponents uriComponents = uriComponentsBuilder.path("/master-data/groups/" + section.getGroup().getId() + "/sections/" + section.getId() + "/section-categories/{id}").buildAndExpand(sectionCategory.getId());

        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Section Category in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Section Category is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MrktpSectionCategoriesDto sectionCategoriesDto) throws MrktpNotFoundException {
        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        sectionCategoryService.update(sectionCategoriesDto);
        Section section = sectionService.findResource(sectionCategoriesDto.getSectionId());

        SectionCategory sectionCategory = sectionCategoryService.findResource(id);
        sectionCategory.setSection(section);
        section.getSectionCategories().add(sectionCategory);
        sectionRepository.save(section);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;

    }
//    @Transactional
//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Section Category in Market Place platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Section Category is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id);
//    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search an the Section Category in Market Place platform", response = MrktpSectionCategoriesDto.class)
    public ResponseEntity<MrktpSectionCategoriesDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(sectionCategoryService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Section Categorys in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return sectionCategoryService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"})
    @ApiOperation(value = "List of all the Section Categories in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllSectionProperties() {
        return sectionCategoryService.findAllActive();
    }
}
