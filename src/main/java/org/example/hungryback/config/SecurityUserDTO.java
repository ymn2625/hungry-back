package org.example.hungryback.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hungryback.user.common.Role;
import org.example.hungryback.user.entity.UserEntity;

@Getter
@NoArgsConstructor
public class SecurityUserDTO {
    private Long id;

    private String email;
    private String name;
    private String nickname;
    private String tel;
    private String profileImg;

    private Boolean state;

    private String role;

    public SecurityUserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();

        this.email = userEntity.getEmail();
        this.name = userEntity.getName();
        this.nickname = userEntity.getNickname();
        this.tel = userEntity.getTel();
        this.profileImg = userEntity.getProfileImg();

        this.state = true;

        this.role = Role.ROLE_USER;
    }
}
