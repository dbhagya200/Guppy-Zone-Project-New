package lk.ijse.backend.controller;

import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.dto.ResponseDTO;
import lk.ijse.backend.service.CategoriesService;
import lk.ijse.backend.util.ResponseUtil;
import lk.ijse.backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
public class CategoriesController {
    @Autowired
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping(path = "save")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseEntity<ResponseDTO> saveCategories(@RequestBody CategoriesDTO categoriesDTO) { //saveCategories
        categoriesService.saveCategories(categoriesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(VarList.Created, "Success", null));

    }
    @GetMapping(path = "get")
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<ResponseDTO> getAllCategories() {
        List<CategoriesDTO> categories = categoriesService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK, "Success", categories));
    }

    @PutMapping(path = "update")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseUtil updateCategories(@RequestBody CategoriesDTO categoriesDTO){
        categoriesService.updateCategories(categoriesDTO);
        return new ResponseUtil(200, "Success", null);
    }

    @DeleteMapping(path = "delete",params = "id")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public ResponseUtil deleteCategories(@RequestParam (value = "id") String id){
        categoriesService.deleteCategories(id);
        return new ResponseUtil(200, "Success", null);
    }

}
