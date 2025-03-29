package lk.ijse.backend.repository;


import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoriesRepo extends JpaRepository<Categories,String> {
    List<Categories> findAllByCategoryId(String itemCode);
}
