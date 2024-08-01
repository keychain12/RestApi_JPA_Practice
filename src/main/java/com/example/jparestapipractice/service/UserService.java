package com.example.jparestapipractice.service;

import com.example.jparestapipractice.domain.User;
import com.example.jparestapipractice.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("해당 회원을 찾을 수 없습니다."));
    }

    public Long saveUser(User user) {
       return userRepository.save(user).getId();
    }
    public User modify(Long userId, User modified) {
        // 받은 유저id로 원래 유저정보 가져오기
        User original = findUserById(userId);
        // 변경감지로 업데이트
        update(original, modified);

        return original;
    }

    private void update(User original, User modified) {
        original.update(modified);
    }


    public void remove(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }
}