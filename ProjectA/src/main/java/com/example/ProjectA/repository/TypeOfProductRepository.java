package com.example.ProjectA.repository;

import com.example.ProjectA.entity.TypeOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TypeOfProductRepository extends JpaRepository<TypeOfProduct, Integer> {
}
