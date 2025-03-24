package lk.ijse.backend.controller;
import lk.ijse.backend.dto.ItemDTO;
import lk.ijse.backend.service.ItemService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addsItem")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;


    @PostMapping(path = "save")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseEntity<ItemDTO> postItems(@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
        return ResponseEntity.ok(itemDTO);
    }
    @GetMapping(path = "get")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseEntity<List<ItemDTO>> getItems() {
        List<ItemDTO> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PutMapping(path = "update")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseUtil putItems(@RequestBody ItemDTO itemDTO) {
        itemService.updateItem(itemDTO);
        return new ResponseUtil(200, "Success", null);
    }

    @DeleteMapping(path = "delete",params = "itemCode")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseUtil deleteItems(@RequestParam (value = "itemCode") String itemCode) {
        itemService.deleteItem(itemCode);
        return new ResponseUtil(200, "Success", null);
    }

}
