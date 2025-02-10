package com.mrkt.admin.web.api.v1.account;


import com.mrkt.admin.dto.account.UserLoginDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.service.account.MrktpUserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "User Login API", tags = "User Login", description = "User Login Management")
@RequestMapping(value = {"v1/account/userlogin"})
@Slf4j
public class UserLoginController {

private final MrktpUserLoginService userLoginService;



    public UserLoginController(MrktpUserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }


    @PostMapping(value="",produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the User Login in Market Place platform",response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "The User Login is created!",response = CreateResponse.class),
            @ApiResponse(code = 400,message = "The request is invalid!"),
            @ApiResponse(code = 500,message = "Server error")})
    public CreateResponseData create(@Valid @RequestBody UserLoginDto userLoginDto){


        return userLoginService.create(userLoginDto);
    }


    @PutMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the User Login in Market Place platform",response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201,message = "The User Login is replaced!",response = ResponseBase.class),
            @ApiResponse(code = 400,message = "The request is invalid!"),
            @ApiResponse(code = 500,message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                 @Valid @RequestBody UserLoginDto userLoginDto)throws MrktpNotFoundException
    {
        userLoginService.update(userLoginDto);
        return new ResponseEntity<>(HttpStatus.OK);

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


    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search an the User Login in Market Place platform",response = MrktpGroupDto.class)
    public ResponseEntity<UserLoginDto> findById(@PathVariable String id)throws MrktpNotFoundException
    {
        return new ResponseEntity<>(userLoginService.findById(id),HttpStatus.OK);

    }

    @GetMapping(value="",produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the User Login in Market Place platform",response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page,@RequestParam(required = false) int size,@RequestParam(required = false)
                                                         String direction,@RequestParam(required = false) String property,@RequestParam(required = false)String filter)
    {
        return userLoginService.findAllActive();
    }
    @GetMapping(value="/active",produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the User Login in Market Place platform",response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllUserLogins()
    {
        return userLoginService.findAllActive();
    }
}
