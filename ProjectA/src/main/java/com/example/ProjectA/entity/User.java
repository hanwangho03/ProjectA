package com.example.ProjectA.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message =  "Name không được để trống")
    private String name;
    @NotNull(message =  "Email không được để trống")
    @Email(message =  "Email không hợp lệ" )
    private String email;
    @NotNull(message =  "Password không được để trống")
    private  String password;
    @Max(value = 100,message =  "Address chỉ tối đa 100 kí tự")
    @Min(value = 5, message = "Address phải có ít nhất 5 kí tự")
    private  String Address;
    @Pattern(regexp = "^(0|84)(\\d{9})$", message = "Số điện thoại không hợp lệ")
    private  String phone;
    @Min(value = 0,message = "Age phải lớn hơn 0")
    @Max(value = 100,message =  "Age phải bé hơn 100")
    private  int age;

    private  String image;

     @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
     private Set<WarehouseRelease> warehouseRelease ;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
     private  Set<WarehouseReceipt> warehouseReceipt ;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}
