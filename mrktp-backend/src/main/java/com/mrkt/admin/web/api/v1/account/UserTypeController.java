package com.mrkt.admin.web.api.v1.account;


import com.mrkt.admin.dto.account.UserTypeDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.account.UserG;
import com.mrkt.admin.model.account.UserType;
import com.mrkt.admin.repository.account.UserGRepository;
import com.mrkt.admin.repository.masterdata.UserRepository;
import com.mrkt.admin.service.account.MrktpUserService;
import com.mrkt.admin.service.account.MrktpUserTypeService;
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
@Api(value = "User Type API", tags = "User Type", description = "User Type Management")
@RequestMapping(value = {"v1/account/users/{userId}/user-types", "/account/users/{userId}/user-types"})
@Slf4j
public class UserTypeController {

    private final MrktpUserTypeService userTypeService;

    private final MrktpUserService userService;

    private final UserGRepository userRepository;


    public UserTypeController(MrktpUserTypeService userTypeService,
                              MrktpUserService userService,
                              UserGRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userTypeService = userTypeService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the User Type in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The User Type is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody UserTypeDto userDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        UserG userG = userService.findResource(userDto.getUserId());
        CreateResponseData responseData = userTypeService.create(userDto);
        UserType userType = userTypeService.findResource(responseData.getId());
        userType.setUserG(userG);
        userG.getUserTypes().add(userType);
        userRepository.save(userG);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/users/" + userG.getId() + "/user-types/{id}").buildAndExpand(userType.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the User Type in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The User Type is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody UserTypeDto userDto) throws MrktpNotFoundException {
        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        userTypeService.update(userDto);
        UserG userG = userService.findResource(userDto.getUserId());

        UserType userType = userTypeService.findResource(id);
        userType.setUserG(userG);
        userG.getUserTypes().add(userType);
        userRepository.save(userG);

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


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search an  User Type in Market Place platform", response = MrktpGroupDto.class)
    public ResponseEntity<UserTypeDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(userTypeService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the User Types  in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return userTypeService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the User Types  in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllUserTypes() {
        return userTypeService.findAllActive();
    }
}
