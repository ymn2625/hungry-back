package org.example.hungryback.dto.request.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchTelRequestDto {

    @Email
    @NotBlank
    private String userEmail;

    @NotBlank
    @Pattern(regexp="^(01[016789]{1})[0-9]{7,8}$")
    private String userTel;

}
