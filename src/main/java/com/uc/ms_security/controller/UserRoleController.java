package com.uc.ms_security.controller;

import com.uc.ms_security.dto.userrole.*;
import jakarta.validation.Valid;
import com.uc.ms_security.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@RequiredArgsConstructor
public class UserRoleController {
    private final UserRoleService service;
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public UserRoleResponseDTO create(@Valid @RequestBody CreateUserRoleDTO dto) { return service.create(dto); }
    @GetMapping public List<UserRoleResponseDTO> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public UserRoleResponseDTO findById(@PathVariable Long id) { return service.findById(id); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}