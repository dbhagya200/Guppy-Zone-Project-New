package lk.ijse.backend.repository;


import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.entity.Categories;
import lk.ijse.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories,String> {


    boolean existsByName(String name);
}
