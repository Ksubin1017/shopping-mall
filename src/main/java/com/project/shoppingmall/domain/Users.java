package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@DynamicInsert
@Table(name="users")
public class Users {

    @Id
    @Column(nullable = false, name="user_id")
    private String userId;

    @Column(nullable = false, name="user_password")
    private String pwd;

    @Column(nullable = false, name="user_name")
    private String name;

    @Column(nullable = false, name="user_address")
    private String address;

    @Column(nullable = false, name="user_phone")
    private String phone;

    @Column(nullable = false, name="user_email", unique = true)
    private String email;

    @Column(name="user_birth")
    private String birth;

    @Column(name="user_class", columnDefinition = "varchar(255) default 'Role_m'")
    private String userClass;

    protected Users() {

    }

    public Users(String userId, String pwd, String name, String address, String phone, String email, String birth) {
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
    }
}