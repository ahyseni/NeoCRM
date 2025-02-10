package com.mrkt.admin.web.api.v1.order;


import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.dto.order.OrderItemsDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.order.Order;
import com.mrkt.admin.model.order.OrderItems;
import com.mrkt.admin.repository.order.OrdersRepository;
import com.mrkt.admin.service.order.OrderItemsService;
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
@Api(value = "Order Items API", tags = "Order Items ", description = "Order Items  Management")
@RequestMapping(value = {"v1/account/orders/{orderId}/orderitems", "/account/orders/{orderId}/orderitems"})
@Slf4j
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    private final OrderService orderService;

    private final OrdersRepository ordersRepository;


    public OrderItemsController(OrderItemsService orderItemsService,
                                OrderService orderService,
                                OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
        this.orderService = orderService;
        this.orderItemsService = orderItemsService;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Order Items in ISBS Neo platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order Items is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody OrderItemsDto orderItemsDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        Order order = orderService.findResource(orderItemsDto.getOrder().getId());
        CreateResponseData responseData = orderItemsService.create(orderItemsDto);
        OrderItems orderItems = orderItemsService.findResource(responseData.getId());
        orderItems.setOrder(order);
        order.getOrderItemsList().add(orderItems);
        ordersRepository.save(order);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/orders/" + order.getId() + "/orderitems/{id}").buildAndExpand(orderItems.getId());
        CreateResponse responseBase = CreateResponse.Builder().build(Result.SUCCESS).data(responseData);
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Order Items in ISBS Neo platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order Items is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody OrderItemsDto orderItemsDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        orderItemsService.update(orderItemsDto);
        Order order = orderService.findResource(orderItemsDto.getOrder().getId());
        OrderItems orderItems = orderItemsService.findResource(orderItemsDto.getId());
        orderItems.setOrder(order);
        order.getOrderItemsList().add(orderItems);
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
    @ApiOperation(value = "Search an  Order Items in ISBS Neo platform", response = MrktpGroupDto.class)
    public ResponseEntity<OrderItemsDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(orderItemsService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Order Items in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return orderItemsService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the Order Items in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllUserAddresses() {
        return orderItemsService.findAllActive();
    }
}
