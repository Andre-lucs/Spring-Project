package com.andrelucs.demoProject.repositories;

import com.andrelucs.demoProject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
