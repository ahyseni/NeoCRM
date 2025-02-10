package com.mrkt.admin.web.api.v1.order;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.order.OrderHistoryDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.order.Order;
import com.mrkt.admin.model.order.OrderHistory;
import com.mrkt.admin.repository.order.OrdersRepository;
import com.mrkt.admin.service.order.OrderHistoryService;
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
@Api(value = "Order History API", tags = "Order History", description = "Order History Management")
@RequestMapping(value = {"v1/account/orders/{orderId}/orderhistories", "/account/orders/{orderId}/orderhistories"})
@Slf4j
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    private final OrderService orderService;

    private final OrdersRepository ordersRepository;


    public OrderHistoryController(OrderHistoryService orderHistoryService,
                                  OrderService orderService,
                                  OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
        this.orderService = orderService;
        this.orderHistoryService = orderHistoryService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Order History in ISBS Neo platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order History is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody OrderHistoryDto orderDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        Order order = orderService.findResource(orderDto.getOrderDto().getId());
        CreateResponseData responseData = orderHistoryService.create(orderDto);
        OrderHistory orderHistory = orderHistoryService.findResource(responseData.getId());
        orderHistory.setOrder(order);
        order.getOrderHistoryList().add(orderHistory);
        ordersRepository.save(order);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/orders/" + order.getId() + "/orderhistories/{id}").buildAndExpand(orderHistory.getId());
        CreateResponse responseBase = CreateResponse.Builder().build(Result.SUCCESS).data(responseData);
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Order History in ISBS Neo platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order History is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody OrderHistoryDto orderDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        orderHistoryService.update(orderDto);
        Order order = orderService.findResource(orderDto.getOrderDto().getId());
        OrderHistory orderHistory = orderHistoryService.findResource(orderDto.getId());
        orderHistory.setOrder(order);
        order.getOrderHistoryList().add(orderHistory);
        ordersRepository.save(order);

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
    @ApiOperation(value = "Search an  Order History in ISBS Neo platform", response = MrktpGroupDto.class)
    public ResponseEntity<OrderHistoryDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(orderHistoryService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Order History in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return orderHistoryService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the Order History in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllUserAddresses() {
        return orderHistoryService.findAllActive();
    }
}
