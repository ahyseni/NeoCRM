package com.mrkt.admin.web.api.v1.order;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.order.OrderDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.account.UserG;
import com.mrkt.admin.model.order.Order;
import com.mrkt.admin.repository.account.UserGRepository;
import com.mrkt.admin.repository.masterdata.UserRepository;
import com.mrkt.admin.service.account.MrktpUserService;
import com.mrkt.admin.service.order.OrderService;
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
@Api(value = "Order API", tags = "Order", description = "Order Management")
@RequestMapping(value = {"v1/account/users/{userId}/orders", "/account/users/{userId}/orders"})
@Slf4j
public class OrderController {

    private final OrderService orderService;

    private final MrktpUserService userService;

    private final UserGRepository userRepository;


    public OrderController(OrderService orderService,
                           MrktpUserService userService,
                           UserGRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.orderService = orderService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Order in ISBS Neo platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody OrderDto orderDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        UserG userG = userService.findResource(orderDto.getUserDto().getId());
        CreateResponseData responseData = orderService.create(orderDto);
        Order order = orderService.findResource(responseData.getId());
        order.setUserG(userG);
        userG.getOrderList().add(order);
        userRepository.save(userG);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/users/" + userG.getId() + "/orders/{id}").buildAndExpand(order.getId());
        CreateResponse responseBase = CreateResponse.Builder().build(Result.SUCCESS).data(responseData);
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Order in ISBS Neo platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody OrderDto orderDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        orderService.update(orderDto);
        UserG userG = userService.findResource(orderDto.getUserDto().getId());

        Order order = orderService.findResource(orderDto.getId());
        order.setUserG(userG);
        userG.getOrderList().add(order);
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
    @ApiOperation(value = "Search an  Order in ISBS Neo platform", response = MrktpGroupDto.class)
    public ResponseEntity<OrderDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Order in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return orderService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the Order in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllUserAddresses() {
        return orderService.findAllActive();
    }
}
