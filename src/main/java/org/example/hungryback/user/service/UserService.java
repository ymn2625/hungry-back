package org.example.hungryback.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hungryback.user.dto.UserSignUpDTO;
import org.example.hungryback.user.entity.UserEntity;
import org.example.hungryback.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUp(UserSignUpDTO userSignUpDTO) throws Exception {
        if (userRepository.findByEmail(userSignUpDTO.getEmail()) != null) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (userRepository.findByNickname(userSignUpDTO.getNickname()) != null) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(userSignUpDTO.getPassword());
        UserEntity userEntity = new UserEntity(userSignUpDTO, encodedPassword);

        userRepository.save(userEntity);
    }
}
