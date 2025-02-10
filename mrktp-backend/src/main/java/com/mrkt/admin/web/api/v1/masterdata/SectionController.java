package com.mrkt.admin.web.api.v1.masterdata;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpSectionDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.masterdata.Group;
import com.mrkt.admin.model.masterdata.Section;
import com.mrkt.admin.repository.masterdata.MrktpGroupRepository;
import com.mrkt.admin.service.masterdata.GroupService;
import com.mrkt.admin.service.masterdata.SectionService;
import com.mrkt.admin.web.api.v1.validator.masterdata.GroupValidator;
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
@Api(value = "Section API", tags = "Section", description = "Section Management")
@RequestMapping(value = {"v1/master-data/groups/{groupId}/sections", "/master-data/groups/{groupId}/sections"})
@Slf4j
public class SectionController {

    private final SectionService sectionService;

    private final MrktpGroupRepository mrktpGroupRepository;

    private final GroupValidator groupValidator;

    private final GroupService groupService;


    public SectionController(SectionService sectionService,
                             MrktpGroupRepository mrktpGroupRepository,
                             GroupService groupService,
                             GroupValidator groupValidator) {

        this.groupService = groupService;
        this.mrktpGroupRepository = mrktpGroupRepository;
        this.sectionService = sectionService;
        this.groupValidator = groupValidator;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Section in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Section is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MrktpSectionDto sectionDto) throws MrktpNotFoundException {
        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        Group group = groupService.findResource(sectionDto.getGroupId());
        CreateResponseData responseData = sectionService.create(sectionDto);
        Section section = sectionService.findResource(responseData.getId());
        section.setGroup(group);
        group.getSections().add(section);
        mrktpGroupRepository.save(group);

        UriComponents uriComponents = uriComponentsBuilder.path("/master-data/groups/" + group.getId() + "/sections/{id}").buildAndExpand(section.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Section in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Section is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MrktpSectionDto sectionBaseDto) throws MrktpNotFoundException {
        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        sectionService.update(sectionBaseDto);
        Group group = groupService.findResource(sectionBaseDto.getGroupId());

        Section section = sectionService.findResource(id);
        section.setGroup(group);
        group.getSections().add(section);
        mrktpGroupRepository.save(group);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;

    }

//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Section in Market Place platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Section is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id,sectionService);
//    }


    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search an the Section in Market Place platform",response = MrktpSectionDto.class)
    public ResponseEntity<MrktpSectionDto> findById(@PathVariable String id)throws MrktpNotFoundException
    {
        return new ResponseEntity<>(sectionService.findById(id),HttpStatus.OK);

    }

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Sections in Market Place platform",response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page,@RequestParam(required = false) int size,@RequestParam(required = false)
                                                         String direction,@RequestParam(required = false) String property,@RequestParam(required = false)String filter)
    {
        return sectionService.findAllActive();
    }
    @GetMapping(value="/active",produces = {"application/json"})
    @ApiOperation(value = "List of all the Sections in Market Place platform",response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllSections()
    {
        return sectionService.findAllActive();
    }
}
