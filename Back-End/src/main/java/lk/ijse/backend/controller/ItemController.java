package lk.ijse.backend.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lk.ijse.backend.dto.ItemDTO;
import lk.ijse.backend.service.ItemService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addsItem")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ItemController {
    @Autowired
    private ItemService itemService;


    @PostMapping(path = "save")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseUtil postItems(@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
        return new ResponseUtil(200, "Success", null);
    }
    @GetMapping(path = "get")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseUtil getItems() {

        return new ResponseUtil(200, "Success", itemService.getAllItems());
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
