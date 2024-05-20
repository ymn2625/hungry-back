package org.example.hungryback.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindAccountRequestDto {
    @Email
    @NotBlank
    private String userEmail;
}
