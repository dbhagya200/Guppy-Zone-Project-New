package lk.ijse.backend.service;


import lk.ijse.backend.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO saveItem(ItemDTO itemDTO);
    ItemDTO updateItem(ItemDTO itemDTO);
    void deleteItem(String itemCode);
    ItemDTO findItem(String itemCode);
    List<ItemDTO> getAllItems();
    List<ItemDTO> getItemsByCategory(String categoryId);
    List<ItemDTO> getItemsByUser(String userEmail);
}
