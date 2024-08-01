package com.example.jparestapipractice.controller;

import com.example.jparestapipractice.common.Result;
import com.example.jparestapipractice.domain.User;
import com.example.jparestapipractice.dto.user.request.UserEditRequest;
import com.example.jparestapipractice.dto.user.request.UserSaveRequest;
import com.example.jparestapipractice.dto.user.response.UserResponse;
import com.example.jparestapipractice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() { // 모든 사용자 목록 api

        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable Long userId) { // id로 회원 찾기 api
        User finduser = userService.findUserById(userId);
        return UserResponse.toResponse(finduser);
    }

    @PostMapping("/")  // 회원 저장 api
    public Result createUser(@Valid @RequestBody UserSaveRequest userSaveRequest,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // 유효성 검사 실패 시 에러 메시지를 클라이언트에 반환
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return new Result<>(errorMessages);
        }

        User user = userSaveRequest.toEntity();
        Long saveUser = userService.saveUser(user);


        return new Result(saveUser);
    }

    @PatchMapping("/{userId}") // 회원 수정 api
    public UserResponse editUser(@PathVariable Long userId,
                                 @Valid @RequestBody UserEditRequest userEditRequest) {
        // 수정할 정보 받은 객체
        User modified = userEditRequest.toEntity();
        // 수정할 사람의 id값과 , 수정할 내용을담은 객체 같이보냄
        modified = userService.modify(userId, modified);

        return UserResponse.toResponse(modified);
    }


    @DeleteMapping("/{userId}") // 회원 삭제 api
    public Result deleteUser(@PathVariable Long userId) {
        userService.remove(userId);
        return new Result(userId);
    }
}
