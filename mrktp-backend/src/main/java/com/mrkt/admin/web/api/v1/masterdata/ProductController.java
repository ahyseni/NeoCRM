package com.mrkt.admin.web.api.v1.masterdata;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpProductDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.masterdata.Product;
import com.mrkt.admin.model.masterdata.SectionProperty;
import com.mrkt.admin.repository.masterdata.MrktpSectionPropertyRepository;
import com.mrkt.admin.service.masterdata.ProductService;
import com.mrkt.admin.service.masterdata.SectionPropertyService;
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
@Api(value = "Product API", tags = "Product", description = "Product Management")
@RequestMapping(value = {"v1/master-data/groups/{groupId}/sections/{sectionId}/section-categories/{sectionCategoryId}/section-properties/{sectionPropertyId}/products",
        "/master-data/groups/{groupId}/sections/{sectionId}/section-categories/{sectionCategoryId}/section-properties/{sectionPropertyId}/products"})
@Slf4j
@CrossOrigin(origins = {"*"})
public class ProductController {

    private final ProductService productService;

    private final SectionPropertyService propertyService;

    private final MrktpSectionPropertyRepository propertyRepository;

    public ProductController(ProductService productService,
                             SectionPropertyService propertyService,
                             MrktpSectionPropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.propertyRepository = propertyRepository;
        this.productService = productService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Product in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Product is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MrktpProductDto productDto) throws MrktpNotFoundException {
        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        SectionProperty property = propertyService.findResource(productDto.getSectionPropertyId());
        CreateResponseData responseData = productService.create(productDto);
        Product product = productService.findResource(responseData.getId());
        product.setSectionproperties(property);
        property.getProductList().add(product);
        propertyRepository.save(property);

        UriComponents uriComponents = uriComponentsBuilder.
                path("/master-data/groups/" + property.getCategories().getSection().
                        getGroup().getId() + "/sections/" + property.getCategories().
                        getSection().getId() + "/section-categories/" + property.
                        getCategories().getId() + "/section-properties/" + property.getId() + "/products")
                .buildAndExpand(product.getId());

        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);
        return response;
    }


    @PutMapping(value = "/{id}", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Replace the Product in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Product is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MrktpProductDto productDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        productService.update(productDto);
        SectionProperty property = propertyService.findResource(productDto.getSectionPropertyId());

        Product product = productService.findResource(id);
        product.setSectionproperties(property);
        property.getProductList().add(product);
        propertyRepository.save(property);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;

    }
//    @Transactional
//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Product in Market Place platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Product is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id);
//    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    @ApiOperation(value = "Search an the Product in Market Place platform", response = MrktpProductDto.class)
    public ResponseEntity<MrktpProductDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Products in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return productService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"})
    @ApiOperation(value = "List of all the Products in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllProducts() {
        return productService.findAllActive();
    }
}
