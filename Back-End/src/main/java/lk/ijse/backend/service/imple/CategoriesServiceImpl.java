package lk.ijse.backend.service.imple;


import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.entity.Categories;
import lk.ijse.backend.repository.CategoriesRepo;
import lk.ijse.backend.service.CategoriesService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Character.getType;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private CategoriesRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void saveCategories(CategoriesDTO categoriesDTO) {
        if (categoryRepo.existsById(categoriesDTO.getCategoryId())) {
            throw new RuntimeException("Category already exists");
        }
        categoryRepo.save(modelMapper.map(categoriesDTO, Categories.class));
    }

    @Override
    public List<CategoriesDTO> getAllCategories() {
        return modelMapper.map(categoryRepo.findAll(),
                new TypeToken<List<CategoriesDTO>>(){}.getType());
    }

    @Override
    public void updateCategories(CategoriesDTO categoriesDTO) {
        if (!categoryRepo.existsById(categoriesDTO.getCategoryId())) {
            throw new RuntimeException("Category not found");
        }
        categoryRepo.save(modelMapper.map(categoriesDTO, Categories.class));

    }

    @Override
    public void deleteCategories(String id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
        }
        throw new RuntimeException("Category does not exist");
    }
}
