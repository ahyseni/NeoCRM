package com.mrkt.admin.web.api.v1.masterdata;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.OpportunityDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.service.masterdata.GroupService;
import com.mrkt.admin.service.masterdata.OpportunityService;
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
@Api(value = "Opportunity API", tags = "Opportunity", description = "Opportunity Management")
@RequestMapping(value = {"v1/master-data/opportunities", "/master-data/opportunities"})
@Slf4j
@CrossOrigin(origins = {"*"})
public class OpportunityController {

    private final OpportunityService opportunityService;


    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Opportunity in ISBS Neo platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Opportunity is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody OpportunityDto opportunityDto) {
        ResponseEntity<CreateResponseData> response;


        CreateResponseData data = new CreateResponseData(opportunityService.create(opportunityDto).getId());

        UriComponents uriComponents = uriComponentsBuilder.path("/master-data/opportunities/{id}").buildAndExpand(data.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(data);


        return response;

    }


    @PutMapping(value = "/{id}", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Replace the Opportunity in ISBS Neo platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Opportunity is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody OpportunityDto groupBaseDto) throws MrktpNotFoundException {
        ResponseEntity<ResponseBase> response;
        opportunityService.update(groupBaseDto);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;


    }
//    @Transactional
//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Opportunity in ISBS Neo platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Opportunity is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id);
//    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    @ApiOperation(value = "Search an the Opportunity in ISBS Neo platform", response = OpportunityDto.class)
    public ResponseEntity<OpportunityDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(opportunityService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Opportunities in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return opportunityService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"})
    @ApiOperation(value = "List of all the Opportunities in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllOpportunities() {
        return opportunityService.findAllActive();
    }
}
