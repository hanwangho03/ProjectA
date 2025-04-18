package com.example.ProjectA.repository;

import com.example.ProjectA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // làm để tạo các phương thưc fillAll ,get ,find , c,r,u,d
    // hàm subquery
}
