package lk.ijse.backend.repository;


import lk.ijse.backend.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<Categories,String> {
    List<Categories> findAllByCategoryId(String itemCode);
}
