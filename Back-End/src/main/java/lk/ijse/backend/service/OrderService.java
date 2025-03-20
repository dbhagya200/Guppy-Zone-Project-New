package lk.ijse.backend.service;

import lk.ijse.backend.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(OrderDTO orderDTO);
}
