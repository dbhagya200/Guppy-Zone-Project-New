package lk.ijse.backend.repository;


import lk.ijse.backend.dto.ItemDTO;
import lk.ijse.backend.entity.Categories;
import lk.ijse.backend.entity.Item;
import lk.ijse.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, String> {
    List<Item> findByUserEmail(String sellerEmail);
    List<Item> findByCategoryCategoryId(String categoryId);

    @Query("SELECT i FROM Item i WHERE i.category.categoryId = :categoryId AND i.user.email = :sellerEmail")
    List<Item> findByCategoryAndSeller(@Param("categoryId") String categoryId,
                                       @Param("sellerEmail") String sellerEmail);

    Item findByItemCode(String itemCode);
}
