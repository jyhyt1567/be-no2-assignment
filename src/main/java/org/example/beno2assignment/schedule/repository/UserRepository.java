package org.example.beno2assignment.schedule.repository;

import org.example.beno2assignment.schedule.entity.User;

//레벨 3 구현
public interface UserRepository {
    User signUp(User user);

    User findUserByUidorElseThrow(Long uid);

}
