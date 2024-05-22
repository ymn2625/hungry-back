package org.example.hungryback.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hungryback.dto.request.auth.SignUpRequestDto;

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
    public UserEntity(SignUpRequestDto dto) {
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userType = dto.getUserType();
        this.userName = dto.getUserName();
        this.userTel = dto.getUserTel();
        this.userProfileImg = dto.getUserProfileImg();
        this.userNickname = dto.getUserNickname();
        this.userRole = "ROLE_USER";
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
    public void patchTel(String userTel) {
        this.userTel = userTel;
    }

}
