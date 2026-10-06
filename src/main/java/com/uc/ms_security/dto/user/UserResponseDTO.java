package com.uc.ms_security.dto.user;

import lombok.Value;

@Value
public class UserResponseDTO {
    Long id;
    String name;
    String email;
}
//Me permite manipular datos para esconder algunas cosas en la respuesta