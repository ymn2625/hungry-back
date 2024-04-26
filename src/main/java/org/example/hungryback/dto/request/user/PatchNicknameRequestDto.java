package org.example.hungryback.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchNicknameRequestDto {

    @Email
    @NotBlank
    private String userEmail;

    @NotBlank
    private String userNickname;

}
