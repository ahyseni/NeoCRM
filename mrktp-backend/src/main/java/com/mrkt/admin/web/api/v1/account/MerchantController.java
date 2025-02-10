package com.mrkt.admin.web.api.v1.account;


import com.mrkt.admin.dto.account.MerchantDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.account.Merchant;
import com.mrkt.admin.model.account.UserG;
import com.mrkt.admin.repository.account.UserGRepository;
import com.mrkt.admin.repository.masterdata.UserRepository;
import com.mrkt.admin.service.account.MrktpMerchantService;
import com.mrkt.admin.service.account.MrktpUserService;
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
@Api(value = "Merchant API", tags = "Merchant", description = "Merchant Management")
@RequestMapping(value = {"v1/account/users/{userId}/merchants", "/account/users/{userId}/merchants"})
@Slf4j
public class MerchantController {

    private final MrktpMerchantService merchantService;

    private final MrktpUserService userService;

    private final UserGRepository userRepository;


    public MerchantController(MrktpMerchantService merchantService,
                              MrktpUserService userService,
                              UserGRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.merchantService = merchantService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Merchant in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Merchant is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MerchantDto merchantDto) throws MrktpNotFoundException {
        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        UserG userG = userService.findResource(merchantDto.getUserId());
        CreateResponseData responseData = merchantService.create(merchantDto);
        Merchant merchant = merchantService.findResource(responseData.getId());
        merchant.setUserG(userG);
        userG.getMerchants().add(merchant);
        userRepository.save(userG);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/users/" + userG.getId() + "/merchants/{id}").buildAndExpand(merchant.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;

    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Merchant in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Merchant is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MerchantDto merchantDto) throws MrktpNotFoundException {
        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        merchantService.update(merchantDto);
        UserG userG = userService.findResource(merchantDto.getUserId());

        Merchant merchant = merchantService.findResource(id);
        merchant.setUserG(userG);
        userG.getMerchants().add(merchant);
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
    @ApiOperation(value = "Search an  Merchant in Market Place platform", response = MrktpGroupDto.class)
    public ResponseEntity<MerchantDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(merchantService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Merchants in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return merchantService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the Merchants in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllMerchants() {
        return merchantService.findAllActive();
    }
}
