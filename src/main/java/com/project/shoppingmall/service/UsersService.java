package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Users;
import com.project.shoppingmall.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void saveUser(Users users) {
        usersRepository.save(users);
    }

    public Long loginUserId(String userId) {

        return usersRepository.countByUserId(userId);
    }

    public Long loginUserPwd(String pwd) {

        return usersRepository.countByPwd(pwd);
    }

    public Users findUser(String userId) {

        return usersRepository.findByUserId(userId);
    }
}
