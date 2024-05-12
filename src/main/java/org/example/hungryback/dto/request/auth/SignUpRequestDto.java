package org.example.hungryback.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @Email
    @NotBlank
    private String userEmail;

    @NotBlank
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,13}$")
    private String userPassword;

    @NotBlank
    private String userName;

    @NotBlank
    @Pattern(regexp="^(01[016789]{1})[0-9]{7,8}$")
    private String userTel;

    private String userProfileImg;

    @NotBlank
    private String userNickname;

    @NotBlank
    private String userType;

}
