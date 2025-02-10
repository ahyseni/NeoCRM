package com.mrkt.admin.map;

import com.mrkt.admin.map.masterdata.*;
import org.mapstruct.factory.Mappers;

public class MrktpMapper {

    private MrktpMapper() {

    }


    public static final MrktpProductMapper ADVERTISE_MAPPER = Mappers.getMapper(MrktpProductMapper.class);
    public static final MrktpProductPropertyMapper ADVERTISE_PROPERTY_MAPPER = Mappers.getMapper(MrktpProductPropertyMapper.class);
    public static final MrktpProductQuoteMapper ADVERTISE_QUOTE_MAPPER = Mappers.getMapper(MrktpProductQuoteMapper.class);
    public static final MrktpGroupMapper GROUP_MAPPER = Mappers.getMapper(MrktpGroupMapper.class);
    public static final MrktpSectionMapper SECTION_MAPPER = Mappers.getMapper(MrktpSectionMapper.class);
    public static final MrktpSectionPropertyMapper SECTION_PROPERTY_MAPPER = Mappers.getMapper(MrktpSectionPropertyMapper.class);
    // Additional mappers for masterdata:
    public static final TenantMapper TENANT_MAPPER = Mappers.getMapper(TenantMapper.class);
    public static final UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    public static final ContactMapper CONTACT_MAPPER = Mappers.getMapper(ContactMapper.class);
    public static final DealMapper DEAL_MAPPER = Mappers.getMapper(DealMapper.class);
    public static final TaskMapper TASK_MAPPER = Mappers.getMapper(TaskMapper.class);
    public static final ActivityMapper ACTIVITY_MAPPER = Mappers.getMapper(ActivityMapper.class);
    public static final NotificationMapper NOTIFICATION_MAPPER = Mappers.getMapper(NotificationMapper.class);
    public static final AuditLogMapper AUDIT_LOG_MAPPER = Mappers.getMapper(AuditLogMapper.class);

    // Mapper for user ID conversions (if needed)
    public static final UserIdMapper USER_ID_MAPPER = Mappers.getMapper(UserIdMapper.class);

}
