package lk.ijse.backend.service.imple;


import jakarta.servlet.annotation.MultipartConfig;
import lk.ijse.backend.dto.ItemDTO;
import lk.ijse.backend.entity.Categories;
import lk.ijse.backend.entity.Item;
import lk.ijse.backend.entity.User;
import lk.ijse.backend.repository.CategoriesRepo;
import lk.ijse.backend.repository.ItemRepo;
import lk.ijse.backend.repository.UserRepo;
import lk.ijse.backend.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    private CategoriesRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);

        // Set category if categoryId is provided
        if (itemDTO.getCategoryId() != null) {
            Categories category = categoryRepo.findById(itemDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            item.setCategory(category);
        }

        // Set user if userEmail is provided
        if (itemDTO.getUserEmail() != null) {
            User user = userRepository.findById(itemDTO.getUserEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            item.setUser(user);
        }

        itemRepository.save(item);
        return modelMapper.map(item, ItemDTO.class);
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) {
        if (!itemRepository.existsById(itemDTO.getItemCode())) {
            throw new RuntimeException("Item not found");
        }

        Item existingItem = itemRepository.findById(itemDTO.getItemCode()).get();
        modelMapper.map(itemDTO, existingItem);

        // Update category if changed
        if (itemDTO.getCategoryId() != null &&
                (existingItem.getCategory() == null ||
                        !existingItem.getCategory().getCategoryId().equals(itemDTO.getCategoryId()))) {
            Categories category = categoryRepo.findById(itemDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingItem.setCategory(category);
        }

        // Update user if changed
        if (itemDTO.getUserEmail() != null &&
                (existingItem.getUser() == null ||
                        !existingItem.getUser().getEmail().equals(itemDTO.getUserEmail()))) {
            User user = userRepository.findById(itemDTO.getUserEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existingItem.setUser(user);
        }

        itemRepository.save(existingItem);
        return modelMapper.map(existingItem, ItemDTO.class);
    }

    @Override
    public void deleteItem(String itemCode) {
        if (!itemRepository.existsById(itemCode)) {
            throw new RuntimeException("Item not found");
        }
        itemRepository.deleteById(itemCode);
    }

    @Override
    public ItemDTO findItem(String itemCode) {
        Item item = itemRepository.findById(itemCode)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return modelMapper.map(item, ItemDTO.class);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getItemsByCategory(String categoryId) {
        Categories category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return itemRepository.findByCategory(category).stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getItemsByUser(String userEmail) {
        User user = userRepository.findById(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return itemRepository.findByUser(user).stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }
}
