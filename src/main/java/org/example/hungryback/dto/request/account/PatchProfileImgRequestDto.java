package org.example.hungryback.dto.request.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchProfileImgRequestDto {

    @Email
    @NotBlank
    private String userEmail;

    private String userProfileImg;

}
