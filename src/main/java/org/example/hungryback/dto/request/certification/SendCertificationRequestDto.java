package org.example.hungryback.dto.request.certification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendCertificationRequestDto {

    @NotBlank
    @Pattern(regexp="^(01[016789]{1})[0-9]{7,8}$")
    private String userTel;

}
