package lk.ijse.backend.controller;

import lk.ijse.backend.dto.CategoriesDTO;
import lk.ijse.backend.dto.ResponseDTO;
import lk.ijse.backend.entity.Categories;
import lk.ijse.backend.service.CategoriesService;
import lk.ijse.backend.util.JwtUtil;
import lk.ijse.backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @Autowired
    private final JwtUtil jwtUtil;

    public CategoriesController(CategoriesService categoriesService, JwtUtil jwtUtil) {

        this.categoriesService = categoriesService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesDTO> createCategory(@RequestBody CategoriesDTO categoryDTO) {
        CategoriesDTO savedCategory = categoriesService.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }


    @GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<ResponseDTO> getAllCategories() {
        List<CategoriesDTO> categories = categoriesService.getAllCategories();
        return ResponseEntity.ok()
                .body(new ResponseDTO(VarList.OK, "Success", categories));
    }

}
