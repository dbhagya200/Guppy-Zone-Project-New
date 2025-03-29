package lk.ijse.backend.repository;


import lk.ijse.backend.dto.ItemDTO;
import lk.ijse.backend.entity.Categories;
import lk.ijse.backend.entity.Item;
import lk.ijse.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, String> {
//    List<Item> findAllByCategoryId(String itemCode);
//    List<Item> findAllByUserId(String userId);
    List<Item>findAllByItemCode(String itemCode);

    List<ItemDTO> findByCategory(Categories category);

    List<ItemDTO> findByUser(User user);
}
