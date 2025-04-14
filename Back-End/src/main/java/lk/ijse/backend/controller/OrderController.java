package lk.ijse.backend.controller;

import jakarta.validation.Valid;
import lk.ijse.backend.dto.OrderDTO;
import lk.ijse.backend.dto.ResponseDTO;
import lk.ijse.backend.service.OrderService;
import lk.ijse.backend.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid OrderDTO orderDTO){
        orderService.placeOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(VarList.OK,"Order Added SuccessFully!",orderDTO));
    }
//    @GetMapping("/get")
//    public ResponseEntity<List<OrderDTO>> getOrderHistory(@RequestParam String userEmail) {
//        List<OrderDTO> orderHistory = orderService.getAllOrders(userEmail);
//        return ResponseEntity.ok(orderHistory);
//    }
}
