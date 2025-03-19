package lk.ijse.backend.service;

import lk.ijse.backend.dto.CategoriesDTO;

import java.util.List;

public interface CategoriesService {
    void saveCategories(CategoriesDTO categoriesDTO);
    List <CategoriesDTO> getAllCategories();
    void updateCategories(CategoriesDTO categoriesDTO);
    void deleteCategories(String id);

}
