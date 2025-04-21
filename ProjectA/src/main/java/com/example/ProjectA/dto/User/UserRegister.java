package com.example.ProjectA.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRegister {

    @NotNull(message =  "Name không được để trống")
    @NotBlank(message =  "Name không được để trống")
    private String name;

    @Email(message = "Email không hợp lệ")
    @NotNull(message =  "Email không được để trống")
    @NotBlank(message =  "Email không được để trống")
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Mật khẩu phải có ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt"
    )
    private String password;

}
