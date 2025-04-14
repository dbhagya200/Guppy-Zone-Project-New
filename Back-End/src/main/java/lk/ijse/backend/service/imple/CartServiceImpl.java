package lk.ijse.backend.service.imple;

import lk.ijse.backend.dto.CartDTO;
import lk.ijse.backend.entity.Cart;
import lk.ijse.backend.repository.CartRepo;
import lk.ijse.backend.service.CartService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private CartRepo cartRepo;

    @Override
    public void addtoCart(CartDTO cartDTO) {
        if (cartDTO == null) {
            throw new IllegalArgumentException("CartDTO cannot be null");
        }

        try {
            Cart cart = modelMapper.map(cartDTO, Cart.class);
            cartRepo.save(cart);
        } catch (Exception e) {
            e.printStackTrace(); // this will show you the actual error in console
            throw new RuntimeException("Failed to add item to cart", e);
        }

    }

    @Override
    public void removeFromCart(int id) {
        cartRepo.deleteById(id);
    }

    @Override
    public List<CartDTO> getCartItemsByUser(String email) {
        List<Cart> cartItems = cartRepo.findByUserEmail(email);

        if (cartItems == null || cartItems.isEmpty()) {
            return Collections.emptyList();
        }

        return cartItems.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Cart updateCartQuantity(int id, int quantity) {
        Cart cart = cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cart.setQuantity(quantity);
        return cartRepo.save(cart);
    }
}
