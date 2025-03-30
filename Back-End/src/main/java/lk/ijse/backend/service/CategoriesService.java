package lk.ijse.backend.service;

import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.dto.CategoryUpdateDTO;
import lk.ijse.backend.entity.Categories;

import java.util.List;

public interface CategoriesService {
    CategoriesDTO saveCategory(CategoriesDTO categoriesDTO);
//    CategoriesDTO getCategoryById(String id);
    List<CategoriesDTO> getAllCategories();
    CategoriesDTO updateCategory(String id, CategoryUpdateDTO updateDTO, String username);
//    void deleteCategory(String id);
}
