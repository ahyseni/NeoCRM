package com.mrkt.admin.web.api.v1.masterdata;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpProductPropertyDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.masterdata.Product;
import com.mrkt.admin.model.masterdata.ProductProperty;
import com.mrkt.admin.repository.masterdata.MrktpProductRepository;
import com.mrkt.admin.service.masterdata.ProductPropertyService;
import com.mrkt.admin.service.masterdata.ProductService;
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
@Api(value = "Product Property API", tags = "Product Property", description = "Product Property Management")
@RequestMapping(value = {"v1/master-data/groups/{groupId}/sections/{sectionId}/section-categories/{sectionCategoryId}/section-properties/{sectionPropertyId}/products/{productId}/product-property",
        "/master-data/groups/{groupId}/sections/{sectionId}/section-categories/{sectionCategoryId}/section-properties/{sectionPropertyId}/products/{productId}/product-property"})
@Slf4j
@CrossOrigin(origins = {"*"})
public class ProductPropertyController {

    private final ProductPropertyService productPropertyService;

    private final ProductService productService;

    private final MrktpProductRepository productRepository;


    public ProductPropertyController(ProductPropertyService productPropertyService,
                                     ProductService productService,
                                     MrktpProductRepository productRepository) {
        this.productPropertyService = productPropertyService;
        this.productRepository = productRepository;
        this.productService = productService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Product Property in Market Place platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Product Property is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody MrktpProductPropertyDto productDto) throws MrktpNotFoundException {
        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        Product product = productService.findResource(productDto.getProductId());
        CreateResponseData responseData = productPropertyService.create(productDto);
        ProductProperty productProperty = productPropertyService.findResource(responseData.getId());
        productProperty.setProduct(product);
        product.getProperties().add(productProperty);
        productRepository.save(product);

        UriComponents uriComponents = uriComponentsBuilder.
                path("/master-data/groups/" + product.getSectionproperties().
                        getCategories().getSection().getGroup().
                        getId() + "/sections/" + product.getSectionproperties().
                        getCategories().getSection().getId() + "/section-categories/" + product.
                        getSectionproperties().getCategories().getId() + "/section-properties/" +
                        product.getSectionproperties().getId() + "/products/" + product.getId() + "/product-property/{id}")
                .buildAndExpand(productProperty.getId());
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);
        return response;

    }


    @PutMapping(value = "/{id}", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Replace the Product Property in Market Place platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Product Property is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody MrktpProductPropertyDto productDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
        productPropertyService.update(productDto);
        Product product = productService.findResource(productDto.getProductId());
        ProductProperty productProperty = productPropertyService.findResource(id);
        productProperty.setProduct(product);
        product.getProperties().add(productProperty);
        productRepository.save(product);

        ResponseBase responseBase = ResponseBase.Builder().build(Result.SUCCESS);
        response = ResponseEntity.ok().body(responseBase);
        return response;
    }
//    @Transactional
//    @PostMapping(value="/id",produces = {"application/json"},
//            consumes = {"application/json"})
//    @ApiOperation(value = "Delete the Product Property in Market Place platform")
//    @ResponseStatus(HttpStatus.CREATED)
//    @ApiResponses(value = {
//            @ApiResponse(code = 201,message = "The Product Property is deleted!",response = CreateResponse.class),
//            @ApiResponse(code = 400,message = "The request is invalid!"),
//            @ApiResponse(code = 500,message = "Server error")})
//    public ResponseEntity<ResponseBase> delete(@PathVariable String id)throws MrktpNotFoundException
//    {
//        return ControllerUtils.delete(id);
//    }


    @GetMapping(value = "/{id}", produces = {"application/json"})
    @ApiOperation(value = "Search an the Product Property in Market Place platform", response = MrktpProductPropertyDto.class)
    public ResponseEntity<MrktpProductPropertyDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(productPropertyService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Product Properties in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return productPropertyService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"})
    @ApiOperation(value = "List of all the Product Properties in Market Place platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllProductProperties() {
        return productPropertyService.findAllActive();
    }
}
