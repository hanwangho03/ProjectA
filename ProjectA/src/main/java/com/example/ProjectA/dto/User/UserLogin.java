package com.example.ProjectA.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLogin {
    @NotNull(message =  "Email không được để trống")
    @Email(message =  "Email không hợp lệ" )
    @NotBlank(message =  "Email không được để rỗng")
    private String email;

    @NotNull(message =  "Password không được để trống")
    private  String password;
}
