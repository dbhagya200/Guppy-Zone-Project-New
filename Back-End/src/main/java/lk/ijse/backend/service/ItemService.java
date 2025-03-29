package lk.ijse.backend.service;


import lk.ijse.backend.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO saveItem(ItemDTO itemDTO, String sellerEmail);
    List<ItemDTO> getItemsBySeller(String sellerEmail);
    List<ItemDTO> getItemsByCategoryAndSeller(String categoryId, String sellerEmail);

}
