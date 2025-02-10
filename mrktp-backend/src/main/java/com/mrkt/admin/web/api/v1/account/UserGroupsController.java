package com.mrkt.admin.web.api.v1.account;


import com.mrkt.admin.dto.account.UserGroupsDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.account.UserG;
import com.mrkt.admin.model.account.UserGroups;
import com.mrkt.admin.repository.account.UserGRepository;
import com.mrkt.admin.repository.masterdata.UserRepository;
import com.mrkt.admin.service.account.MrktpUserService;
import com.mrkt.admin.service.account.UserGroupsService;
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
@Api(value = "User Groups API", tags = "User Groups", description = "User Groups Management")
@RequestMapping(value = {"v1/account/users/{userId}/usergroups", "/account/users/{userId}/usergroups"})
@Slf4j
public class UserGroupsController {

    private final UserGroupsService userGroupsService;

    private final MrktpUserService userService;

    private final UserGRepository userRepository;


    public UserGroupsController(UserGroupsService userGroupsService,
                                MrktpUserService userService,
                                UserGRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userGroupsService = userGroupsService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the User Groups in ISBS Neo platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The User Groups is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody UserGroupsDto userGroupsDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        UserG userG = userService.findResource(userGroupsDto.getUserDto().getId());
        CreateResponseData responseData = userGroupsService.create(userGroupsDto);
        UserGroups userGroups = userGroupsService.findResource(responseData.getId());
        userGroups.setUserG(userG);
        userG.getUserGroups().add(userGroups);
        userRepository.save(userG);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/users/" + userG.getId() + "/usergroups/{id}").buildAndExpand(userGroups.getId());
        CreateResponse responseBase = CreateResponse.Builder().build(Result.SUCCESS).data(responseData);
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the User Groups in ISBS Neo platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The User Groups is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody UserGroupsDto userGroupsDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        userGroupsService.update(userGroupsDto);
        UserG userG = userService.findResource(userGroupsDto.getUserDto().getId());

        UserGroups userGroups = userGroupsService.findResource(id);
        userGroups.setUserG(userG);
        userG.getUserGroups().add(userGroups);
        userRepository.save(userG);

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
    @ApiOperation(value = "Search an  User Groups in ISBS Neo platform", response = MrktpGroupDto.class)
    public ResponseEntity<UserGroupsDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(userGroupsService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the User Groups in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return userGroupsService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the User Groups in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllUserAddresses() {
        return userGroupsService.findAllActive();
    }
}
