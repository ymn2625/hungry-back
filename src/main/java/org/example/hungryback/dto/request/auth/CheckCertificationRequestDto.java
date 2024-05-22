package org.example.hungryback.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckCertificationRequestDto {

    @NotBlank
    private String userName;

    @NotBlank
    @Pattern(regexp="^(01[016789]{1})[0-9]{7,8}$")
    private String userTel;

    @NotBlank
    @Pattern(regexp="^[0-9]{6}$")
    private String certificationNumber;

}
