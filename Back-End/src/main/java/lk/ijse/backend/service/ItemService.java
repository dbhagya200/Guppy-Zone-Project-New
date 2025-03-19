package lk.ijse.backend.service;


import lk.ijse.backend.dto.ItemDTO;

import java.util.List;

public interface ItemService {
//    void saveItem(ItemDTO itemDTO, UserDTO userDTO);
    void saveItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();
    List<ItemDTO> getAllItemsByUserId(String userId);
    List<ItemDTO> getAllItemsByCategoryId(String categoryId);
    List<ItemDTO> getItemById(String itemCode);
    void updateItem( ItemDTO itemDTO);
    void deleteItem(String itemCode);
}
