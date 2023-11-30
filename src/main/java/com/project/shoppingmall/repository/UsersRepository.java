package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    public Users findByUserId(String Id);
    public Long countByUserId(String userId);
    public Long countByPwd(String pwd);
}
