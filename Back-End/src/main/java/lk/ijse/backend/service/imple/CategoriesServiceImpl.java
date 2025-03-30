package lk.ijse.backend.service.imple;


import jakarta.transaction.Transactional;
import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.entity.Categories;
import lk.ijse.backend.repository.CategoriesRepo;
import lk.ijse.backend.repository.UserRepo;
import lk.ijse.backend.service.CategoriesService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    private final CategoriesRepo categoriesRepo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final ModelMapper modelMapper;

    public CategoriesServiceImpl(CategoriesRepo categoriesRepo, UserRepo userRepo, ModelMapper modelMapper) {
        this.categoriesRepo = categoriesRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public CategoriesDTO saveCategory(CategoriesDTO categoriesDTO) {
        if (categoriesRepo.existsByName(categoriesDTO.getName())) {
            throw new RuntimeException("Category name already exists");
        }

        CategoriesDTO category = new CategoriesDTO();
        category.setCategoryId(categoriesDTO.getCategoryId());
        category.setName(categoriesDTO.getName());

        Categories savedCategory = categoriesRepo.save(modelMapper.map(category, Categories.class));
        return modelMapper.map(savedCategory, CategoriesDTO.class);
    }

    @Override
    public List<CategoriesDTO> getAllCategories() {
        List<Categories> categories = categoriesRepo.findAll();
        return modelMapper.map(categories,
                new TypeToken<List<CategoriesDTO>>(){}.getType());
    }
//
//    @Override
//    public CategoriesDTO updateCategory(String id, CategoriesDTO categoriesDTO) {
//        Categories category = categoriesRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//        category.setName(categoriesDTO.getName());
//        return modelMapper.map(categoriesRepo.save(category), CategoriesDTO.class);
//    }
//
//    @Override
//    public void deleteCategory(String id) {
//        categoriesRepo.deleteById(id);
//    }
}




