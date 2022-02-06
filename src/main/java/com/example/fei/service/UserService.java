package com.example.fei.service;

import com.example.fei.domain.User;
import com.example.fei.domain.User2;


public interface UserService {
    User selectUser(long userId);

    User selectUser2(long userId);

    // User selectUser3(long userId);

    User selectUser4(User user);

    User selectUser5(User user);

    User2 selectUser6(long userId);

    User selectUser7(long userId);
}
