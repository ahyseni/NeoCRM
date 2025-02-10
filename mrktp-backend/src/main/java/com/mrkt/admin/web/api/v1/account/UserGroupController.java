package com.mrkt.admin.web.api.v1.account;


import com.mrkt.admin.dto.account.UserGroupDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.repository.masterdata.UserRepository;
import com.mrkt.admin.service.account.MrktpUserService;
import com.mrkt.admin.service.account.UserGroupService;
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
@Api(value = "User Group API", tags = "User Groups", description = "User Group Management")
@RequestMapping(value = {"v1/account/usergroups", "/account/usergroups"})
@Slf4j
public class UserGroupController {

    private final UserGroupService userGroupService;

    private final MrktpUserService userService;

    private final UserRepository userRepository;


    public UserGroupController(UserGroupService userGroupService,
                               MrktpUserService userService,
                               UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userGroupService = userGroupService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the User Group in ISBS Neo platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The User Group is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody UserGroupDto userGroupDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        CreateResponseData responseData = userGroupService.create(userGroupDto);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/usergroups/{id}").buildAndExpand(responseData.getId());
        CreateResponse responseBase = CreateResponse.Builder().build(Result.SUCCESS).data(responseData);
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the The User Group in ISBS Neo platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The The User Group is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody UserGroupDto userGroupDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        userGroupService.update(userGroupDto);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;

    }
//    @Transactional
//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Group in ISBS Neo platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Group is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id);
//    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search an  The User Group in ISBS Neo platform", response = MrktpGroupDto.class)
    public ResponseEntity<UserGroupDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(userGroupService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the The User Group in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return userGroupService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the The User Group in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllUserAddresses() {
        return userGroupService.findAllActive();
    }
}
