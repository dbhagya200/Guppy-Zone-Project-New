// cart.js - This will handle all cart-related functionality
$(document).ready(function() {
    const token = localStorage.getItem('token');
    let cartItems = [];

    if (!token) {
        window.location.href = "register.html";
        return;
    }

    // Initialize the cart
    loadCartItems();

    function updateCartCount() {
        const count = cartItems.reduce((sum, item) => sum + item.quantity, 0);
        $('.cart-count-badge').text(count || '0');
    }

    // Load cart items from server
    function loadCartItems() {
        const userEmail = getUserEmailFromToken();
        console.log("User Email from Token:", userEmail); // Debug

        if (!userEmail) {
            Swal.fire('Error', 'Unable to identify user', 'error');
            return;
        }

        $.ajax({
            url: `http://localhost:8080/api/v1/cart/get`,
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function(response) {
                console.log("Full API Response:", response);
                if (response && response.code === "200" && response.data) {
                    cartItems = response.data;
                    console.log("Cart Items Loaded:", cartItems); // Debug
                    renderCartItems();
                    updateOrderSummary();
                    updateCartCount(); // Add this function
                } else {
                    console.log("No cart items or invalid response");
                    cartItems = [];
                    renderCartItems();
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX Error:", {
                    status: status,
                    error: error,
                    response: xhr.responseJSON
                });
                Swal.fire('Error', 'Failed to load cart: ' + (xhr.responseJSON?.message || error), 'error');
            }
        });
    }

    function getUserEmailFromToken() {
        const token = localStorage.getItem("token");
        if (!token) {
            console.error("No token found");
            Swal.fire('Error', 'Please login first', 'error');
            return null;
        }

        try {
            const decoded = jwt_decode(token);
            console.log("Decoded Token Contents:", decoded);
            // Check what claims are available in your token
            return decoded.email || decoded.username || decoded.sub || null;
        } catch (error) {
            console.error("Error decoding token:", error);
            return null;
        }
    }

    // Render cart items to the page
    function renderCartItems() {
        const cartContainer = $('#cart-items-container');
        cartContainer.find('.cart-item').remove(); // Clear existing items

        if (cartItems.length === 0) {
            cartContainer.append('<div class="col-12 text-center py-5"><h5>Your cart is empty</h5></div>');
            return;
        }

        cartItems.forEach(item => {
            const cartItem = `
                <div class="row cart-item align-items-center mb-3" data-item-id="${item.itemCode}">
                    <div class="col-md-2">
                        <img src="${item.image || 'images/default.jpg'}" alt="${item.name}" class="item-image img-thumbnail">
                    </div>
                    <div class="col-md-4">
                        <h6>${item.name}</h6>
                        <p class="text-muted mb-0">Item Code: ${item.itemCode}</p>
                    </div>
                    <div class="col-md-3">
                        <div class="d-flex align-items-center">
                            <button class="quantity-btn btn btn-sm btn-outline-secondary">-</button>
                            <input type="text" class="quantity-input form-control form-control-sm mx-2 text-center" 
                                   value="${item.quantity}" style="width: 50px;">
                            <button class="quantity-btn btn btn-sm btn-outline-secondary">+</button>
                        </div>
                    </div>
                    <div class="col-md-2 text-end">
                        <h6>Rs.${(item.price * item.quantity).toFixed(2)}</h6>
                        <small class="text-muted">Rs.${item.price.toFixed(2)} each</small>
                    </div>
                    <div class="col-md-1 text-end">
                        <button class="btn btn-sm btn-outline-danger remove-item"><i class="fas fa-trash"></i></button>
                    </div>
                </div>`;
            cartContainer.append(cartItem);
        });
    }

    // Update order summary
    function updateOrderSummary() {
        const subtotal = cartItems.reduce((sum, item) => sum + (item.price * item.quantity), 0);
        const tax = subtotal * 0.05; // 5% tax
        const total = subtotal + tax;

        $('.summary-card').html(`
            <div class="d-flex justify-content-between mb-2">
                <span>Subtotal (${cartItems.length} items)</span>
                <span>Rs.${subtotal.toFixed(2)}</span>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <span>Shipping</span>
                <span>Free</span>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <span>Tax (5%)</span>
                <span>Rs.${tax.toFixed(2)}</span>
            </div>
            <hr>
            <div class="d-flex justify-content-between fw-bold">
                <span>Total</span>
                <span>Rs.${total.toFixed(2)}</span>
            </div>
        `);
    }

    // Event handlers for cart operations
    $(document).on('click', '.quantity-btn', function() {
        const row = $(this).closest('.cart-item');
        const itemCode = row.data('item-id');
        const input = row.find('.quantity-input');
        let quantity = parseInt(input.val());

        if ($(this).text() === '+') {
            quantity++;
        } else {
            quantity = quantity > 1 ? quantity - 1 : 1;
        }

        input.val(quantity);
        updateCartItem(itemCode, quantity);
    });

    $(document).on('click', '.remove-item', function() {
        const itemCode = $(this).closest('.cart-item').data('item-id');
        removeCartItem(itemCode);
    });

    $('.btn-checkout').click(function() {
        if (cartItems.length === 0) {
            Swal.fire('Error', 'Your cart is empty', 'error');
            return;
        }
        window.location.href = "checkout.html";
    });

    $('.btn-outline-danger').click(function() {
        Swal.fire({
            title: 'Clear Cart?',
            text: "Are you sure you want to remove all items from your cart?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'Yes, clear it!'
        }).then((result) => {
            if (result.isConfirmed) {
                clearCart();
            }
        });
    });

    // Cart API functions
    function updateCartItem(itemCode, quantity) {
        const userEmail = getUserEmailFromToken();
        if (!userEmail) return;

        $.ajax({
            url: "http://localhost:8080/api/v1/cart/update",
            type: "PUT",
            contentType: "application/json",
            headers: {
                "Authorization": "Bearer " + token
            },
            data: JSON.stringify({
                userEmail: userEmail,
                itemCode: itemCode,
                quantity: quantity
            }),
            success: function(response) {
                loadCartItems(); // Refresh cart
            },
            error: function(xhr, status, error) {
                console.error("Error updating cart:", error);
            }
        });
    }

    function removeCartItem(itemCode) {
        const userEmail = getUserEmailFromToken(); // Changed from userId to email
        if (!userEmail) return;

        $.ajax({
            url: `http://localhost:8080/api/v1/cart/delete/${itemCode}`,
            type: "DELETE",
            headers: {
                "Authorization": "Bearer " + token
            },
            data: { userEmail: userEmail }, // Send as query parameter or body
            success: function(response) {
                loadCartItems();
                Swal.fire('Removed!', 'Item has been removed from cart.', 'success');
            },
            error: function(xhr, status, error) {
                console.error("Error removing item:", error);
                Swal.fire('Error', 'Failed to remove item: ' + error, 'error');
            }
        });
    }

    function clearCart() {
        const userId = getUserIdFromToken();
        if (!userId) return;

        $.ajax({
            url: `http://localhost:8080/api/v1/cart/clear/${userId}`,
            type: "DELETE",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function(response) {
                cartItems = [];
                renderCartItems();
                updateOrderSummary();
                Swal.fire('Cleared!', 'Your cart has been cleared.', 'success');
            },
            error: function(xhr, status, error) {
                console.error("Error clearing cart:", error);
            }
        });
    }

});