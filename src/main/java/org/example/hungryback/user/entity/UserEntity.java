package org.example.hungryback.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.oauth2.dto.OAuth2Response;
import org.example.hungryback.user.common.Role;
import org.example.hungryback.user.dto.UserSignUpDTO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String nickname;
    private String tel;
    private String profileImg;

    private Boolean state;

    private String role;

    public UserEntity(UserSignUpDTO userSignUpDTO, String encodedPassword) {
        this.email = "APP " + userSignUpDTO.getEmail();
        this.password = encodedPassword;
        this.name = userSignUpDTO.getName();
        this.nickname = userSignUpDTO.getNickname();
        this.tel = userSignUpDTO.getTel();
        this.profileImg = userSignUpDTO.getProfileImg();

        this.state = true;

        this.role = Role.ROLE_USER;
    }

    public UserEntity(OAuth2Response oAuth2Response, String email) {
        this.email = email;
        this.name = oAuth2Response.getName();

        this.state = true;

        this.role = Role.ROLE_GUEST;
    }
}
