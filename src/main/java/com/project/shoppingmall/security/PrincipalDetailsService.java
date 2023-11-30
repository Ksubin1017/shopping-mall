package com.project.shoppingmall.security;

import com.project.shoppingmall.domain.Users;
import com.project.shoppingmall.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String Id) throws UsernameNotFoundException {
        // 세션에 사용자 정보를 저장
        Users usersEntity = usersRepository.findByUserId(Id);
        if (usersEntity == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + Id);
        }

        // UserDetails를 구성하여 반환
        PrincipalDetails userDetails = new PrincipalDetails(usersEntity);

        return userDetails;
    }
}