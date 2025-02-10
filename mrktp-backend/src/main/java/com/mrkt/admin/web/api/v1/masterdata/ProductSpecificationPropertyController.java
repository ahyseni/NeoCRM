package com.mrkt.admin.web.api.v1.masterdata;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpProductSpecificationPropertyDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.masterdata.ProductSpecification;
import com.mrkt.admin.model.masterdata.ProductSpecificationProperty;
import com.mrkt.admin.repository.masterdata.MrktpProductSpecificationsRepository;
import com.mrkt.admin.service.masterdata.ProductSpecificationPropertyService;
import com.mrkt.admin.service.masterdata.ProductSpecificationService;
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
@Api(value = "Product Specification Property API", tags = "Product Specification Property", description = "Product Specification Property Management")
@RequestMapping(value = {"v1/master-data/groups/{groupId}/sections/{sectionId}/section-categories/{sectionCategoryId}/section-properties/{sectionPropertyId}/products/{productId}/product-specifications/{productSpecificationId}/product-spec-properties",
        "/master-data/groups/{groupId}/sections/{sectionId}/section-categories/{sectionCategoryId}/section-properties/{sectionPropertyId}/products/{productId}/product-specifications/{productSpecificationId}/product-spec-properties"})
@Slf4j
@CrossOrigin(origins = {"*"})
public class ProductSpecificationPropertyController {

    private final ProductSpecificationPropertyService propertyService;

    private final ProductSpecificationService specificationService;

    private final MrktpProductSpecificationsRepository specificationsRepository;


    public ProductSpecificationPropertyController(ProductSpecificationPropertyService propertyService,
                                                  ProductSpecificationService specificationService,
                                                  MrktpProductSpecificationsRepository specificationsRepository) {
        this.specificationService = specificationService;
        this.specificationsRepository = specificationsRepository;
        this.propertyService = propertyService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Product Specification Property in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Product Specification Property is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MrktpProductSpecificationPropertyDto specificationPropertyDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;

        ProductSpecification productSpecification = specificationService.findResource(specificationPropertyDto.getProductSpecificationId());
        CreateResponseData responseData = propertyService.create(specificationPropertyDto);
        ProductSpecificationProperty productSpecificationProperty = propertyService.findResource(responseData.getId());
        productSpecificationProperty.setProductSpecification(productSpecification);
        productSpecification.getSpecificationProperties().add(productSpecificationProperty);
        specificationsRepository.save(productSpecification);

        UriComponents uriComponents = uriComponentsBuilder.
                path("/master-data/groups/" +
                        productSpecification.getProduct().getSectionproperties().getCategories().getSection().getGroup().
                                getId() + "/sections/" + productSpecification.getProduct().getSectionproperties().
                        getCategories().getSection().getId() + "/section-categories/" + productSpecification.getProduct().
                        getSectionproperties().getCategories().getId() + "/section-properties/" + productSpecification.getProduct().
                        getSectionproperties().getId() + "/products/" + productSpecification.getProduct().getId() + "/product-specifications/" + productSpecification.getId() + "/product-spec-properties/{id}")
                .buildAndExpand(productSpecificationProperty.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);
        return response;

    }


    @PutMapping(value = "/{id}", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Replace the Product Specification Property in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Product Specification Property is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MrktpProductSpecificationPropertyDto specificationPropertyDto) throws MrktpNotFoundException {
        ResponseEntity<ResponseBase> response;
        propertyService.update(specificationPropertyDto);
        ProductSpecification productSpecification = specificationService.findResource(specificationPropertyDto.getProductSpecificationId());
        ProductSpecificationProperty productSpecificationProperty = propertyService.findResource(id);
        productSpecificationProperty.setProductSpecification(productSpecification);
        productSpecification.getSpecificationProperties().add(productSpecificationProperty);
        specificationsRepository.save(productSpecification);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;

    }
//    @Transactional
//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Product Specification Property in Market Place platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Product Specification Property is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id);
//    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    @ApiOperation(value = "Search an the Product Specification Property in Market Place platform", response = MrktpProductSpecificationPropertyDto.class)
    public ResponseEntity<MrktpProductSpecificationPropertyDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(propertyService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Product Specification Properties  in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return propertyService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"})
    @ApiOperation(value = "List of all the Product Specification Properties  in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllProductSpecificationProperties() {
        return propertyService.findAllActive();
    }
}
