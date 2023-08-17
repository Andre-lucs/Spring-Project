package com.andrelucs.demoProject.repositories;

import com.andrelucs.demoProject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
