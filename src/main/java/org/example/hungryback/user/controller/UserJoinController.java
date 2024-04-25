package org.example.hungryback.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.hungryback.user.dto.UserSignUpDTO;
import org.example.hungryback.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class UserJoinController {

    private final UserService userService;

    public String joinProcess(UserSignUpDTO userSignUpDTO) throws Exception {
        userService.signUp(userSignUpDTO);
        return "ok";
    }
}
