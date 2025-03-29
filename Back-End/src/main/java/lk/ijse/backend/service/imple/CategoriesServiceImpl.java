package lk.ijse.backend.service.imple;


import jakarta.transaction.Transactional;
import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.entity.Categories;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repository.CategoriesRepo;
import lk.ijse.backend.repository.UserRepo;
import lk.ijse.backend.service.CategoriesService;
import lk.ijse.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


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
    public CategoriesDTO saveCategory(String email,CategoriesDTO categoriesDTO) {
            User seller = userRepo.findByEmail(email);
        if (categoriesRepo.existsByNameAndSeller(categoriesDTO.getName(), seller)) {
            throw new RuntimeException("Category name already exists for this seller");
        }

        Categories category = new Categories();
        category.setCategoryId(categoriesDTO.getCategoryId());
        category.setName(categoriesDTO.getName());
        category.setSeller(seller);

        Categories savedCategory = categoriesRepo.save(category);
        return modelMapper.map(savedCategory, CategoriesDTO.class);
    }

//    @Override
//    public CategoriesDTO getCategoryById(String id) {
//        Categories category = categoriesRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//        return modelMapper.map(category, CategoriesDTO.class);
//    }
//
////    @Override
////    public List<CategoriesDTO> getAllCategories() {
////        return categoriesRepo.getAllCategories();
////    }
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




