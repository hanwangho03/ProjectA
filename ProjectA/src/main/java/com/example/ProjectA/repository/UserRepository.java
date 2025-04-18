package com.example.ProjectA.repository;

import com.example.ProjectA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // bạn có thể thêm các custom query ở đây nếu cần
}
