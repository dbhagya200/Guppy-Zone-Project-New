package lk.ijse.backend.service.imple;


import jakarta.servlet.annotation.MultipartConfig;
import lk.ijse.backend.dto.ItemDTO;
import lk.ijse.backend.entity.Item;
import lk.ijse.backend.repository.CategoriesRepo;
import lk.ijse.backend.repository.ItemRepo;
import lk.ijse.backend.repository.UserRepo;
import lk.ijse.backend.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ItemServiceImple implements ItemService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ItemRepo itemRepository;
    @Autowired
    private CategoriesRepo categoriesRepo;

    @Autowired
    private ModelMapper modelMapper;


//    @Override
//    public void saveItem(ItemDTO itemDTO , UserDTO UserDTO) {
//        User user = userRepository.findByEmail(UserDTO.getEmail());
//                modelMapper.map(UserDTO, User.class);
//        if (itemRepository.existsById(itemDTO.getItemCode())) {
//            throw new RuntimeException("Item already exists");
//        }
//        itemRepository.save(modelMapper.map(itemDTO, Item.class));
//
//
//    }

    @Override
    public void saveItem(ItemDTO itemDTO) {

       if (itemRepository.existsById(itemDTO.getItemCode())) {
           throw new RuntimeException("Item already exists");
       }
       itemRepository.save(modelMapper.map(itemDTO, Item.class));

    }

    @Override
    public List<ItemDTO> getAllItems() {
       return modelMapper.map(itemRepository.findAll(),
                new TypeToken<List<ItemDTO>>(){}.getType());
    }

    @Override
    public List<ItemDTO> getAllItemsByUserId(String userEmail) {
        return modelMapper.map(userRepository.findByEmail(userEmail),
                new TypeToken<List<ItemDTO>>(){}.getType());
    }

    @Override
    public List<ItemDTO> getAllItemsByCategoryId(String categoryId) {
        return modelMapper.map(categoriesRepo.findAllByCategoryId(categoryId),
                new TypeToken<List<ItemDTO>>(){}.getType());
    }

    @Override
    public List<ItemDTO> getItemById(String itemCode) {
        return modelMapper.map(itemRepository.findAllByItemCode(itemCode),
                new TypeToken<List<ItemDTO>>(){}.getType());
    }

    @Override
    public void updateItem( ItemDTO itemDTO) {
        if (!itemRepository.existsById(itemDTO.getItemCode())) {
            throw new RuntimeException("Item not found");
        }
        itemRepository.save(modelMapper.map(itemDTO, Item.class));

    }

    @Override
    public void deleteItem(String itemCode) {
        if (itemRepository.existsById(itemCode)) {
            itemRepository.deleteById(itemCode);
        }
        throw new RuntimeException("Item does not exist");

    }
}
