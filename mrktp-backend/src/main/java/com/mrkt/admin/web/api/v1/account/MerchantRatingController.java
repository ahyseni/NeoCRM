package com.mrkt.admin.web.api.v1.account;


import com.mrkt.admin.dto.account.MerchantRatingDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.account.Merchant;
import com.mrkt.admin.model.account.MerchantRating;
import com.mrkt.admin.repository.account.MerchantRepository;
import com.mrkt.admin.service.account.MrktpMerchantRatingService;
import com.mrkt.admin.service.account.MrktpMerchantService;
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
@Api(value = "Merchant Rating API", tags = "Merchant Rating", description = "Merchant Rating Management")
@RequestMapping(value = {"v1/account/users/{userId}/merchants/{merchantId}/merchant-ratings", "/account/users/{userId}/merchants/{merchantId}/merchant-ratings"})
@Slf4j
public class MerchantRatingController {

    private final MrktpMerchantRatingService merchantRatingService;

    private final MrktpMerchantService merchantService;

    private final MerchantRepository merchantRepository;

    public MerchantRatingController(MrktpMerchantRatingService merchantRatingService,
                                    MrktpMerchantService merchantService,
                                    MerchantRepository merchantRepository) {
        this.merchantService = merchantService;
        this.merchantRepository = merchantRepository;
        this.merchantRatingService = merchantRatingService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Merchant Rating in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Merchant Rating is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MerchantRatingDto merchantRatingDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        Merchant merchant = merchantService.findResource(merchantRatingDto.getMerchantId());
        CreateResponseData responseData = merchantRatingService.create(merchantRatingDto);
        MerchantRating merchantRating = merchantRatingService.findResource(responseData.getId());
        merchantRating.setMerchant(merchant);
        merchant.getMerchantRatings().add(merchantRating);
        merchantRepository.save(merchant);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/users/" + merchant.getUserG().getId() + "/merchants/" + merchant.getId() + "/merchant-ratings/{id}").buildAndExpand(merchantRating.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;

    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Merchant Rating in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Merchant Rating is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MerchantRatingDto merchantRatingDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        merchantRatingService.update(merchantRatingDto);
        Merchant merchant = merchantService.findResource(merchantRatingDto.getMerchantId());

        MerchantRating merchantRating = merchantRatingService.findResource(id);
        merchantRating.setMerchant(merchant);
        merchant.getMerchantRatings().add(merchantRating);
        merchantRepository.save(merchant);

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
    @ApiOperation(value = "Search an  Merchant Rating in Market Place platform", response = MrktpGroupDto.class)
    public ResponseEntity<MerchantRatingDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(merchantRatingService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Merchant Ratings in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return merchantRatingService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the Merchant Ratings in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllMerchantRatings() {
        return merchantRatingService.findAllActive();
    }
}
