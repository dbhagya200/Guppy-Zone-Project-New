package lk.ijse.backend.controller;

import lk.ijse.backend.dto.OrderDTO;
import lk.ijse.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/placeOrder")
@CrossOrigin
public class PlaceOrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(path = "save")
    @PreAuthorize("hasAnyAuthority('SELLER','BUYER')")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.placeOrder(orderDTO);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping(path = "test")
    public String checkss(){
        return "passed~!2";
    }

}
