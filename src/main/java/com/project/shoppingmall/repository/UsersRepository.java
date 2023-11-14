package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {

}
