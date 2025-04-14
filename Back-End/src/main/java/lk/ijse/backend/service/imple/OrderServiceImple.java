package lk.ijse.backend.service.imple;

import jakarta.transaction.Transactional;
import lk.ijse.backend.dto.OrderDTO;
import lk.ijse.backend.entity.Orders;
import lk.ijse.backend.repository.CartRepo;
import lk.ijse.backend.repository.ItemRepo;
import lk.ijse.backend.repository.OrderDetailRepo;
import lk.ijse.backend.repository.OrderRepo;
import lk.ijse.backend.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class OrderServiceImple implements OrderService {
    private final CartRepo cartRepo;
    private final OrderRepo orderRepo;
    private final OrderDetailRepo orderDetailsRepo;
    private final ItemRepo itemRepo;
    private final ModelMapper modelMapper;

    public OrderServiceImple(CartRepo cartRepository, OrderRepo orderRepository, OrderDetailRepo orderDetailsRepository, ItemRepo itemRepository, ModelMapper modelMapper, CartRepo cartRepo, OrderRepo orderRepo, OrderDetailRepo orderDetailsRepo, ItemRepo itemRepo, ModelMapper modelMapper1) {

        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
        this.orderDetailsRepo = orderDetailsRepo;
        this.itemRepo = itemRepo;
        this.modelMapper = modelMapper1;
    }


    @Override
    public void placeOrder(OrderDTO orderDTO) {
        orderRepo.save(modelMapper.map(orderDTO, Orders.class));
    }

    @Override
    public void delete(int orderId) {
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<OrderDTO> getallOrders() {
        return modelMapper.map(orderRepo.findAll(), new TypeToken<List<OrderDTO>>() {}.getType());
    }

    @Override
    public OrderDTO getLastOrder() {
        Orders lastOrder = orderRepo.findFirstByOrderByOrderIdDesc();

        if (lastOrder == null) {
            return null; // No orders available
        }
        return modelMapper.map(lastOrder, OrderDTO.class);
    }
}
