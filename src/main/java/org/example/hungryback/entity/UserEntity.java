package org.example.hungryback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hungryback.dto.request.user.SignUpAppRequestDto;
import org.example.hungryback.dto.request.user.SignUpSocialRequestDto;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="user")
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true)
    private String userEmail;
    private String userPassword;
    private String userType;
    private String userName;
    @Column(unique = true)
    private String userTel;
    private String userProfileImg;
    private String userNickname;
    private String userRole;

    @Builder
    public UserEntity(SignUpAppRequestDto dto) {
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userType = "App";
        this.userName = dto.getUserName();
        this.userTel = dto.getUserTel();
        this.userProfileImg = dto.getUserProfileImg();
        this.userNickname = dto.getUserNickname();
        this.userRole = "USER";
    }

    public void signUpUser(SignUpSocialRequestDto dto) {
        this.userName = dto.getUserName();
        this.userTel = dto.getUserTel();
        this.userProfileImg = dto.getUserProfileImg();
        this.userNickname = dto.getUserNickname();
        this.userRole = "USER";
    }

    public void patchPassword(String encodedPassword) {
        this.userPassword = encodedPassword;
    }

    public void patchProfileImg(String userProfileImg) {
        this.userProfileImg = userProfileImg;
    }

    public void patchNickname(String userNickname) {
        this.userNickname = userNickname;
    }

}
