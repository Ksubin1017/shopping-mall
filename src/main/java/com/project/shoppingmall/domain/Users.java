package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name="users")
public class Users {

    @Id
    @Column(nullable = false, name="user_id")
    private String Id;

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

    protected Users() {}

    public Users(String id, String pwd, String name, String address, String phone, String email, String birth) {
        this.Id = id;
        this.pwd = pwd;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
    }

    public static Users of(String id, String pwd, String name, String address, String phone, String email, String birth) {
        return new Users(id, pwd, name, address, phone, email, birth);
    }

}
