package com.mrkt.admin.web.api.v1.account;


import com.mrkt.admin.dto.account.GroupRoleDto;
import com.mrkt.admin.dto.masterdata.MasterDataPresentationDto;
import com.mrkt.admin.dto.response.CreateResponse;
import com.mrkt.admin.dto.response.CreateResponseData;
import com.mrkt.admin.dto.response.ResponseBase;
import com.mrkt.admin.enums.Result;
import com.mrkt.admin.exception.MrktpNotFoundException;
import com.mrkt.admin.model.account.*;
import com.mrkt.admin.repository.account.RoleRepository;
import com.mrkt.admin.repository.account.UserGroupRepository;
import com.mrkt.admin.service.account.GroupRoleService;
import com.mrkt.admin.service.account.RoleService;
import com.mrkt.admin.service.account.UserGroupService;
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
@Api(value = "Group Role API", tags = "Group Role", description = "Group Role Management")
@RequestMapping(value = {"v1/account/usergroup/{usergroupId}/grouproles", "/account/usergroup/{usergroupId}/grouproles"})
@Slf4j
public class GrouRoleController {

    private final GroupRoleService groupRoleService;

    private final UserGroupService userGroupService;

    private final UserGroupRepository userGroupRepository;

    private final RoleService roleService;

    private final RoleRepository roleRepository;


    public GrouRoleController(GroupRoleService groupRoleService,
                              UserGroupService userService,
                              UserGroupRepository userGroupRepository, RoleService roleService, RoleRepository roleRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userGroupService = userService;
        this.groupRoleService = groupRoleService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }


    @PostMapping(value = "", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create the Group Role in ISBS Neo platform", response = CreateResponse.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Group Role is created!", response = CreateResponse.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<CreateResponseData> create(UriComponentsBuilder uriComponentsBuilder, @Valid @RequestBody GroupRoleDto groupRoleDto) throws MrktpNotFoundException {

        ResponseEntity<CreateResponseData> response;
//      groupValidator.validate(sectionDto);
        UserGroup userGroup = userGroupService.findResource(groupRoleDto.getUserGroupDto().getId());
        Role role = roleService.findResource(groupRoleDto.getRoleDto().getId());

        CreateResponseData responseData = groupRoleService.create(groupRoleDto);
        GroupRole groupRole = groupRoleService.findResource(responseData.getId());
        groupRole.setRole(role);
        groupRole.setUserGroup(userGroup);
        userGroup.getGroupRoles().add(groupRole);

        userGroupRepository.save(userGroup);

        UriComponents uriComponents = uriComponentsBuilder.path("/account/usergroup/" + userGroup.getId() + "/grouproles/{id}").buildAndExpand(groupRole.getId());
        CreateResponse responseBase = CreateResponse.Builder().build(Result.SUCCESS).data(responseData);
        response = ResponseEntity.created(uriComponents.toUri()).body(responseData);


        return response;
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Replace the Group Role in ISBS Neo platform", response = ResponseBase.class)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Group Role is replaced!", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "The request is invalid!"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<ResponseBase> replace(@PathVariable String id,
                                                @Valid @RequestBody GroupRoleDto groupRoleDto) throws MrktpNotFoundException {

        ResponseEntity<ResponseBase> response;
//      groupValidator.validate(sectionDto);
        groupRoleService.update(groupRoleDto);
        UserGroup resource = userGroupService.findResource(groupRoleDto.getUserGroupDto().getId());

        GroupRole groupRole = groupRoleService.findResource(id);


        groupRole.setUserGroup(resource);
        resource.getGroupRoles().add(groupRole);
        userGroupRepository.save(resource);

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
    @ApiOperation(value = "Search an  Group Role in ISBS Neo platform", response = GroupRoleDto.class)
    public ResponseEntity<GroupRoleDto> findById(@PathVariable String id) throws MrktpNotFoundException {
        return new ResponseEntity<>(groupRoleService.findById(id), HttpStatus.OK);

    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all the Group Roles in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> findPaginated(@RequestParam(required = false) int page, @RequestParam(required = false) int size, @RequestParam(required = false)
            String direction, @RequestParam(required = false) String property, @RequestParam(required = false) String filter) {
        return groupRoleService.findAllActive();
    }

    @GetMapping(value = "/active", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "List of all the Group Roles in ISBS Neo platform", response = MasterDataPresentationDto.class)
    public List<MasterDataPresentationDto> listAllUserAddresses() {
        return groupRoleService.findAllActive();
    }
}
