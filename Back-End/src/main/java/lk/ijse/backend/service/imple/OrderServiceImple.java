package lk.ijse.backend.service.imple;

import lk.ijse.backend.dto.OrderDTO;
import lk.ijse.backend.entity.Orders;
import lk.ijse.backend.repository.OrderRepo;
import lk.ijse.backend.service.OrderService;
import org.hibernate.mapping.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class OrderServiceImple implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        Orders orders = modelMapper.map(orderDTO, Orders.class);
        orders.setOrderDate(LocalDateTime.now());
        orderRepo.save(orders);
        return modelMapper.map(orders, OrderDTO.class);
    }

//    @Override
//    public List<OrderDTO> getAllOrders() {
//        return null;
//    }
}
