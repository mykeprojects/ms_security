package com.uc.ms_security.mapper;

import com.uc.ms_security.dto.userrole.UserRoleResponseDTO;
import com.uc.ms_security.entity.UserRole;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserRoleMapper {
    public UserRoleResponseDTO toResponseDTO(UserRole userRole) {
        return new UserRoleResponseDTO(userRole.getId(), userRole.getUser().getId(), userRole.getRole().getId());
    }
    public List<UserRoleResponseDTO> toResponseDTOList(List<UserRole> userRoles) {
        return userRoles.stream().map(this::toResponseDTO).toList();
    }
}