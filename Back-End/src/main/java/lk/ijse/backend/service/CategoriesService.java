package lk.ijse.backend.service;

import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.entity.User;

import java.util.List;

public interface CategoriesService {
    CategoriesDTO saveCategory(String email, CategoriesDTO categoriesDTO);
//    CategoriesDTO getCategoryById(String id);
////    List<CategoriesDTO> getAllCategories();
//    CategoriesDTO updateCategory(String id, CategoriesDTO categoriesDTO);
//    void deleteCategory(String id);
}
