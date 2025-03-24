package lk.ijse.backend.controller;

import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.dto.ResponseDTO;
import lk.ijse.backend.service.CategoriesService;
import lk.ijse.backend.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @PostMapping(path = "save")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<CategoriesDTO> saveCategories(@RequestBody CategoriesDTO categoriesDTO){
        categoriesService.saveCategories(categoriesDTO);
        return ResponseEntity.ok(categoriesDTO);
    }
    @GetMapping(path = "get")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<CategoriesDTO>> getAllCategories() {
        List<CategoriesDTO> categories = categoriesService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping(path = "update")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseUtil updateCategories(@RequestBody CategoriesDTO categoriesDTO){
        categoriesService.updateCategories(categoriesDTO);
        return new ResponseUtil(200, "Success", null);
    }

    @DeleteMapping(path = "delete",params = "id")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseUtil deleteCategories(@RequestParam (value = "id") String id){
        categoriesService.deleteCategories(id);
        return new ResponseUtil(200, "Success", null);
    }
    @GetMapping("/test3")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String checkss(){
        return "passed~!2";
    }
}
