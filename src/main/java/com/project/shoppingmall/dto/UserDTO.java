package com.project.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserDTO {

    @NotBlank
    @Length(min = 6, max = 16, message = "아이디는 6글자 이상 16글자 이하여야합니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 영소문자와 숫자만 가능합니다")
    public String userId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    public String pwd;

    @NotBlank
    public String name;

    @NotBlank
    public String postcode;

    @NotBlank
    public String address1;

    @NotBlank
    public String address2;

    @NotBlank
    @Pattern(regexp="[0-9]+$", message="숫자만 입력 가능합니다.")
    public String phone;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "이메일 형식이 올바르지 않습니다.")
    public String email;

    @Pattern(regexp = "^[0-9]{6}$", message = "6자리 숫자만 입력하세요.")
    public String birth;

}
