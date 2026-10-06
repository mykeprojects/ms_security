package com.uc.ms_security.controller;

import com.uc.ms_security.dto.rolepermission.*;
import jakarta.validation.Valid;
import com.uc.ms_security.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/role-permissions")
@RequiredArgsConstructor
public class RolePermissionController {
    private final RolePermissionService service;
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public RolePermissionResponseDTO create(@Valid @RequestBody CreateRolePermissionDTO dto) { return service.create(dto); }
    @GetMapping public List<RolePermissionResponseDTO> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public RolePermissionResponseDTO findById(@PathVariable Long id) { return service.findById(id); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}