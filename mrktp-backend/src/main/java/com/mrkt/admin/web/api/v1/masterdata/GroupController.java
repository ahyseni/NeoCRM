package com.mrkt.admin.web.api.v1.masterdata;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.service.masterdata.GroupService;
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
@Api(value = "Group API", tags = "Group", description = "Group Management")
@RequestMapping(value = {"v1/master-data/groups", "/master-data/groups"})
@Slf4j
@CrossOrigin(origins = {"*"})
public class GroupController{

    private final GroupService groupService;


    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Group in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Group is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MrktpGroupDto groupDto) {
        ResponseEntity<CreateResponseData> response;


        CreateResponseData data = new CreateResponseData(groupService.create(groupDto).getId());

        UriComponents uriComponents = uriComponentsBuilder.path("/master-data/groups/{id}").buildAndExpand(data.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(data);


        return response;

    }


    @PutMapping(value = "/{id}", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Replace the Group in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Group is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MrktpGroupDto groupBaseDto) throws MrktpNotFoundException {
        ResponseEntity<ResponseBase> response;
        groupService.update(groupBaseDto);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;


    }
//    @Transactional
//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Group in Market Place platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Group is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id);
//    }


    @GetMapping(value="/{id}",produces = {"application/json"})
    @ApiOperation(value = "Search an the Group in Market Place platform",response = MrktpGroupDto.class)
    public ResponseEntity<MrktpGroupDto> findById(@PathVariable String id)throws MrktpNotFoundException
    {
        return new ResponseEntity<>(groupService.findById(id),HttpStatus.OK);

    }

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Groups in Market Place platform",response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page,@RequestParam(required = false) int size,@RequestParam(required = false)
                                                         String direction,@RequestParam(required = false) String property,@RequestParam(required = false)String filter)
    {
        return groupService.findAllActive();
    }
    @GetMapping(value="/active",produces = {"application/json"})
    @ApiOperation(value = "List of all the Groups in Market Place platform",response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllGroups()
    {
        return groupService.findAllActive();
    }
}
