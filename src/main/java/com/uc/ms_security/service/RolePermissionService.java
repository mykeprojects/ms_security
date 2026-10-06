package com.uc.ms_security.service;

import com.uc.ms_security.dto.rolepermission.*;
import com.uc.ms_security.entity.RolePermission;
import com.uc.ms_security.exception.*;
import com.uc.ms_security.mapper.RolePermissionMapper;
import com.uc.ms_security.repository.RolePermissionRepository;
import com.uc.ms_security.repository.PermissionRepository;
import com.uc.ms_security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionService {
    private final RolePermissionRepository repository;
        private final RoleRepository roleRepository;
        private final PermissionRepository permissionRepository;
    private final RolePermissionMapper mapper;
        public RolePermissionResponseDTO create(CreateRolePermissionDTO dto) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRole(roleRepository.findById(dto.getRoleId()).orElseThrow(() -> new ApplicationException(
            ErrorCase.NOT_FOUND, "Rol no encontrado con id: " + dto.getRoleId())));
        rolePermission.setPermission(permissionRepository.findById(dto.getPermissionId()).orElseThrow(() -> new ApplicationException(
            ErrorCase.NOT_FOUND, "Permiso no encontrado con id: " + dto.getPermissionId())));
        return mapper.toResponseDTO(repository.save(rolePermission));
        }
    public List<RolePermissionResponseDTO> findAll() { return mapper.toResponseDTOList(repository.findAll()); }
    public RolePermissionResponseDTO findById(Long id) { return mapper.toResponseDTO(findEntity(id)); }
    public void delete(Long id) { repository.delete(findEntity(id)); }
    private RolePermission findEntity(Long id) { return repository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCase.NOT_FOUND, "Relación rol-permiso no encontrada con id: " + id)); }
}