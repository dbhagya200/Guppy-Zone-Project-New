$(document).ready(function () {
    const token = localStorage.getItem('token');
    let allItems = []; // Store items globally

    if (!token) {
        window.location.href = "register.html";
        return;
    }

    // Load all items when page loads
    loadItems();
    getUserEmailFromToken();

    // Event delegation for dynamically created buttons
    $(document).on('click', '.see-details-btn', showItemDetails);
    $(document).on('click', '.add-to-cart', addToCart);

    function loadItems() {
        $.ajax({
            url: "http://localhost:8080/api/v1/addsItem/getAll",
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function(response) {
                if (response && response.data) {
                    allItems = response.data;
                    populateCards(response.data);
                } else {
                    $('#itemsContainer').html('<p class="text-center text-danger">No items found.</p>');
                }
            },
            error: function(xhr, status, error) {
                $('#itemsContainer').html('<p class="text-center text-danger">Error loading items: ' + error + '</p>');
            }
        });
    }

    function populateCards(items) {
        const container = $('#itemsContainer');
        container.empty();

        items.forEach(item => {
            const card = `
                <div class="col-md-6 col-lg-3 mb-4">
    <div class="property-item" style="padding: 15px; margin-bottom: 30px; box-shadow: 1px 1px 4px rgba(0, 0, 0, 0.6); align-items: center">
        <a href="product-detail.html?id=${item.itemCode}" class="img">
            <img src="${item.sourceImage || 'images/img_1.jpg'}"
                 alt="${item.itemName || 'Product image'}"
                 class="img-fluid"
                 onerror="this.src='images/img_1.jpg'"
                 style="padding: 10px; width: 18rem; height: 16rem; object-fit: cover;">
        </a>

        <div class="property-content">
            <div class="price mb-2"><span>Rs.${item.price ? item.price.toFixed(2) : '0.00'}</span></div>
            <div>
                <span class="d-block mb-2 text-black-50">${item.location || 'N/A'}</span>
                <span class="city d-block mb-3">${item.name || 'No name'}</span>

                <div class="specs d-flex mb-3">
                    <span class="d-block d-flex align-items-center me-3">
                        <i class="fas fa-tag me-2"></i>
                        <span class="caption">${getCategoryName(item.categoryId)}</span>
                    </span>
                    <span class="d-block d-flex align-items-center">
                        <i class="fas fa-cubes me-2"></i>
                        <span class="caption">${item.quantity || 0}</span>
                    </span>
                </div>

                <!-- Quantity Input -->
                <div class="mb-3">
                    <label for="quantity-${item.itemCode}" class="form-label">Quantity:</label>
                    <input type="number"
                           id="quantity-${item.itemCode}"
                           class="form-control"
                           value="1"
                           min="1"
                            max="${item.quantity}" 
                           style="max-width: 100px;">
                           <small class="text-muted">In stock: ${item.quantity}</small>
                </div>

                <div style="display: flex; flex-direction: column;">
                    <button class="btn text-light see-details-btn btn-success btn-outline-dark py-2 px-3"
                            data-item-code="${item.itemCode}"
                            style="border-radius: 20px; background-color: #245a48; margin: 10px;">
                        See Details
                    </button>

                    <button class="btn text-light add-to-cart py-2 px-3 btn-outline-light" 
                            style="border-radius: 20px; background-color: #136b68; margin: 10px;"
                            data-item-code="${item.itemCode}"
                            data-image="${item.sourceImage}"
                            data-name="${item.name}"
                            data-quantity="quantity"
                            data-price="${item.price}">
                        Add to Cart
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
`;
            container.append(card);
        });
    }

    function showItemDetails() {
        const itemCode = $(this).data('itemCode');

        // First try to find in cached items
        const cachedItem = allItems.find(item => item.itemCode == itemCode);
        if (cachedItem) {
            populateItemModal(cachedItem);
            return;
        }

        // If not found, fetch from server
        $.ajax({
            url: `http://localhost:8080/api/v1/addsItem/${itemCode}`,
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function(response) {
                if (response && response.data) {
                    populateItemModal(response.data);
                } else {
                    alert('Item details not found');
                }
            },
            error: function(xhr, status, error) {
                alert('Error loading item details: ' + error);
            }
        });
    }

    function populateItemModal(item) {
        $('#detailImage').attr('src', item.sourceImage || 'images/img_1.jpg');
        $('#detailTitle').text(item.name || 'No name');
        $('#detailId').text(item.itemCode || 'N/A');
        $('#detailCategory').text(getCategoryName(item.categoryId) || 'N/A');
        $('#detailDescription').text(item.description || 'No description available');
        $('#detailLocation').text(item.location || 'N/A');
        $('#detailQuantity').text(item.quantity || 0);
        $('#detailPrice').text(item.price ? item.price.toFixed(2) : '0.00');

        // Show the modal
        $('#itemDetailsModal').modal('show');
    }

    function addToCart() {
        const token = localStorage.getItem("token");
        if (!token) {
            Swal.fire('Error', 'User not logged in!', 'error');
            return;
        }

        try {
            const userEmail = getUserEmailFromToken();
            if (!userEmail) {
                Swal.fire('Error', 'Unable to identify user', 'error');
                return;
            }

            const itemCode = $(this).data("item-code");
            const quantity = parseInt($(`#quantity-${itemCode}`).val()) || 1;
            const image = $(this).data('image') || 'images/default.jpg';
            const name = $(this).data('name') || 'Unknown Item';
            const itemPrice = parseFloat($(this).data('price')) || 0.00;
            const totalPrice = itemPrice * quantity;

            const cartData = {
                userEmail: userEmail,
                itemCode: itemCode,
                quantity: quantity,
                image: image,
                name: name,
                price: totalPrice
            };

            console.log("Sending cart data:", cartData);

            $.ajax({
                url: "http://localhost:8080/api/v1/cart/save",
                type: "POST",
                contentType: "application/json",
                headers: {
                    "Authorization": `Bearer ${token}`
                },
                data: JSON.stringify(cartData),
                success: function(response) {
                    console.log("Full response:", response);

                    if (response && response.code === 201) {
                        Swal.fire({
                            icon: 'success',
                            title:  'Item added to cart!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    } else {
                        Swal.fire('Info', response.message || 'Item added to cart', 'info');
                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error details:", xhr.responseJSON);
                    const errorMsg = xhr.responseJSON?.message ||
                        xhr.responseJSON?.error ||
                        'Failed to add to cart';
                    Swal.fire('Error', errorMsg, 'error');
                }
            });
        } catch (error) {
            console.error("Cart error:", error);
            Swal.fire('Error', 'An unexpected error occurred', 'error');
        }
    }

    function getUserEmailFromToken() {
        let token = localStorage.getItem("token");
        if (!token) {
            console.error("No token found!");
            return null;
        }

        try {
            let decodedToken = jwt_decode(token);
            console.log("Decoded Token:", decodedToken);
            return decodedToken.email || decodedToken.sub || null;
        } catch (error) {
            console.error("Error decoding token:", error);
            return null;
        }
    }




    function getCategoryName(categoryId) {
        const categories = {
            1: "Fish",
            2: "Filters",
            3: "Food",
            4: "Accessories"
        };
        return categories[categoryId] || "Other";
    }
});