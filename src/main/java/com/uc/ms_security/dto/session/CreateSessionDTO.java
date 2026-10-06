package com.uc.ms_security.dto.session;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateSessionDTO {
    @NotNull(message = "El usuario es obligatorio")
    private Long userId;

    @NotBlank(message = "El token es obligatorio")
    @Size(max = 500, message = "El token no puede superar 500 caracteres")
    private String token;
    @NotNull(message = "La fecha de expiración es obligatoria")
    private LocalDate expiration;
    @NotBlank(message = "El código 2FA es obligatorio")
    @Size(max = 20, message = "El código 2FA no puede superar 20 caracteres")
    private String code2FA;
}