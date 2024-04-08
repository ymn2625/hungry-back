package org.example.hungryback.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDTO {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String tel;
    private String profileImg;
}
