package com.example.springboot.services;

import com.example.springboot.dto.RegistrationDto;
import com.example.springboot.models.UserEntity;

public interface UserServices {
    void saveUsers(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUserName(String username);
}
