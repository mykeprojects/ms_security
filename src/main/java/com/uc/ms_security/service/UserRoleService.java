package com.uc.ms_security.service;

import com.uc.ms_security.dto.userrole.*;
import com.uc.ms_security.entity.UserRole;
import com.uc.ms_security.exception.*;
import com.uc.ms_security.mapper.UserRoleMapper;
import com.uc.ms_security.repository.UserRoleRepository;
import com.uc.ms_security.repository.RoleRepository;
import com.uc.ms_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository repository;
        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
    private final UserRoleMapper mapper;
        public UserRoleResponseDTO create(CreateUserRoleDTO dto) {
        UserRole userRole = new UserRole();
        userRole.setUser(userRepository.findById(dto.getUserId()).orElseThrow(() -> new ApplicationException(
            ErrorCase.NOT_FOUND, "Usuario no encontrado con id: " + dto.getUserId())));
        userRole.setRole(roleRepository.findById(dto.getRoleId()).orElseThrow(() -> new ApplicationException(
            ErrorCase.NOT_FOUND, "Rol no encontrado con id: " + dto.getRoleId())));
        return mapper.toResponseDTO(repository.save(userRole));
        }
    public List<UserRoleResponseDTO> findAll() { return mapper.toResponseDTOList(repository.findAll()); }
    public UserRoleResponseDTO findById(Long id) { return mapper.toResponseDTO(findEntity(id)); }
    public void delete(Long id) { repository.delete(findEntity(id)); }
    private UserRole findEntity(Long id) { return repository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCase.NOT_FOUND, "Relación usuario-rol no encontrada con id: " + id)); }
}