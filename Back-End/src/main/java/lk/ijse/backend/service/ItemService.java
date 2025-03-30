package lk.ijse.backend.service;


import lk.ijse.backend.dto.ItemDTO;
import lk.ijse.backend.dto.ItemDataDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    ItemDTO saveItem(ItemDataDTO itemDataDTO, String sellerEmail);
    List<ItemDTO> getItemsBySeller(String sellerEmail);
    List<ItemDTO> getItemsByCategoryAndSeller(String categoryId, String sellerEmail);

    List<ItemDTO> getAllItems();

    ItemDTO updateItem(ItemDataDTO itemDataDTO, String email);
}
