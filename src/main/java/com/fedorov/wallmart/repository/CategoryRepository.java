package com.fedorov.wallmart.repository;

import com.fedorov.wallmart.entity.Category;
import com.fedorov.wallmart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
