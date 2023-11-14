package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@DataJpaTest
class JpaRepositoryTest {

    private final UsersRepository usersRepository;

    JpaRepositoryTest(@Autowired UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenNothing_whenSelectingUser_thenWorksFine() {
        // Given

        // When
        List<Users> users = usersRepository.findAll();

        // Then
        assertThat(users)
                .isNotNull()
                .hasSize(0);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInsertingUser_thenWorksFine() {
        // Given
        long previousCount = usersRepository.count();

        // When
        Users savedUsers = usersRepository.save(Users.of("ksubin10117", "123456", "홍길동", "경안동", "01012345678", "test11@test.com", "001122"));

        // Then
        assertThat(usersRepository.count())
                .isEqualTo(previousCount + 1);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdatingUser_thenWorksFine() {
        // Given
        Users users = usersRepository.findById("ksubin1017").orElseThrow();
        String updatedPwd = "1234567";
        users.setPwd(updatedPwd);

        // When
        Users updatedUsers = usersRepository.saveAndFlush(users);

        // Then
        assertThat(updatedUsers).hasFieldOrPropertyWithValue("pwd", updatedPwd);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeletingUser_thenWorksFine() {
        // Given
        Users users = usersRepository.findById("ksubin1017").orElseThrow();
        long previousCount = usersRepository.count();

        // When
        usersRepository.delete(users);

        // Then
        long currentCount = usersRepository.count();
        assertThat(currentCount).isEqualTo(previousCount - 1);
    }
}
