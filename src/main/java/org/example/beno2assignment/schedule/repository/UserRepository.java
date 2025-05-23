package org.example.beno2assignment.schedule.repository;

import org.example.beno2assignment.schedule.entity.User;

public interface UserRepository {
    User signUp(User user);

    User findUserByUidorElseThrow(Long uid);

}
