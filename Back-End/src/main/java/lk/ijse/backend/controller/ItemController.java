package lk.ijse.backend.controller;
import lk.ijse.backend.dto.ItemDTO;
import lk.ijse.backend.dto.ResponseDTO;
import lk.ijse.backend.service.ItemService;
import lk.ijse.backend.util.ResponseUtil;
import lk.ijse.backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addsItem")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;


    @PostMapping(path = "/save",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseEntity<ResponseDTO> saveItem(@RequestBody ItemDTO itemDTO,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        String sellerEmail = userDetails.getUsername();
        ItemDTO savedItem = itemService.saveItem(itemDTO, sellerEmail);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(VarList.Created, "Item saved successfully", savedItem));
    }

    @GetMapping(path = "/get")
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<ResponseDTO> getItemsBySeller(@AuthenticationPrincipal UserDetails userDetails) {
        String sellerEmail = userDetails.getUsername();
        List<ItemDTO> items = itemService.getItemsBySeller(sellerEmail);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Items fetched successfully", items));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseUtil> getItemsByCategoryAndSeller(
            @PathVariable String categoryId,
            @AuthenticationPrincipal UserDetails userDetails) {
        String sellerEmail = userDetails.getUsername();
        List<ItemDTO> items = itemService.getItemsByCategoryAndSeller(categoryId, sellerEmail);
        return ResponseEntity.ok(new ResponseUtil(200, "Items fetched successfully", items));
    }

}
