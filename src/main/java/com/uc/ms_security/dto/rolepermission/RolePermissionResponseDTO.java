package com.uc.ms_security.dto.rolepermission;

import lombok.Value;

@Value
public class RolePermissionResponseDTO {
    Long id;
    Long roleId;
    Long permissionId;
}