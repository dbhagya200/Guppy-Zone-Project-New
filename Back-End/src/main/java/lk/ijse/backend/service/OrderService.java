package lk.ijse.backend.service;

import lk.ijse.backend.dto.OrderDTO;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;


public interface OrderService {
    OrderDTO placeOrder(OrderDTO orderDTO);
//    List<OrderDTO> getAllOrders();
}
