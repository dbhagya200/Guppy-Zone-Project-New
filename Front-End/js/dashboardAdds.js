$(document).ready(function () {
    const token = localStorage.getItem('token');

    if (!token) {
        window.location.href = "register.html";
        return;
    }
    loadItems();
    loadUserAdvertisements();
    showItemDetails(item);

    function loadItems() {
        $.ajax({
            url: "http://localhost:8080/api/v1/addsItem/getAll",
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function(response) {
                if (response && response.data) {
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
           <div class="property-item"	style="padding: 15px;margin-bottom: 30px;
           box-shadow: 1px 1px 4px rgba(0, 0, 0, 0.6);align-items: center">
                    <a href="product-detail.html?id=${item.id}" class="img">
                        <img src="${item.sourceImage || 'images/img_1.jpg'}"
                             alt="${item.name || 'Product image'}"
                             class="img-fluid"
                             onerror="this.src='images/img_1.jpg'"
                             style="padding: 10px; width: 18rem;height: 16rem;
                             object-fit: cover;"
                             >
                    </a>

                    <div class="property-content">
                        <div class="price mb-2"><span>Rs.${item.price ? item.price.toFixed(2) : '0.00'}</span></div>
                        <div>
                            <span class="d-block mb-2 text-black-50">${item.location || 'N/A'}</span>
                            <span class="city d-block mb-3">${item.name || 'No name'}</span>

                            <div class="specs d-flex mb-3">
                                <span class="d-block d-flex align-items-center me-3">
                                    <i class="fas fa-tag me-2"></i>
                                    <span class="caption">${getCategoryName(item.category)}</span>
                                </span>
                                <span class="d-block d-flex align-items-center">
                                    <i class="fas fa-cubes me-2"></i>
                                    <span class="caption">${item.quantity || 0}</span>
                                </span>
                            </div>

                           <button 
                                 <a href="javascript:void(0);" class="btn text-light btn-success btn-outline-dark py-2 px-3" 
                               onclick="showItemDetails(${item.code})" style="border-radius: 20px;background-color: #245a48;margin-top: 10px;">
                               See Details
                            </a>

                            </button>

                        </div>
                    </div>
                </div>
        </div>
        
      `;
            container.append(card);

        });

    }

    function showItemDetails(itemId) {
        // Make an API request to fetch item details using itemId
        $.ajax({
            url: `http://localhost:8080/api/v1/addsItem/${itemId}`, // Make sure this endpoint is correct
            type: 'GET',
            success: function(response) {
                if (response && response.data) {
                    const item = response.data;

                    // Inject data into the modal content
                    const content = `
                    <h3>${item.itemName}</h3>
                    <p><strong>Category:</strong> ${getCategoryName(item.category)}</p>
                    <p><strong>Price:</strong> Rs.${item.price}</p>
                    <p><strong>Description:</strong> ${item.description}</p>
                    <p><strong>Stock:</strong> ${item.quantity}</p>
                    <img src="${item.sourceImage || 'images/default.jpg'}" class="img-fluid" alt="Product Image">
                `;

                    $('#item-details-content').html(content);

                    // Show the modal
                    const modal = new bootstrap.Modal(document.getElementById('itemDetailsModal'));
                    modal.show();
                } else {
                    console.log("Item details not found");
                }
            },
            error: function(xhr, status, error) {
                console.log('Error fetching item details:', error);
            }
        });
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

    function loadUserAdvertisements() {
        const token = localStorage.getItem('token');

        $.ajax({
            url: "http://localhost:8080/api/v1/addsItem/get", // Adjust endpoint if needed
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function(response) {
                if (response && response.data) {
                    populateAdvertisementTable(response.data);
                } else {
                    $('#addvertisementModal .product-table').html('<tr><td colspan="4" class="text-center">No data found</td></tr>');
                }
            },
            error: function(xhr, status, error) {
                $('#addvertisementModal .product-table').html(`<tr><td colspan="4" class="text-danger text-center">Error loading data: ${error}</td></tr>`);
            }
        });
    }

    function populateAdvertisementTable(items) {
        const tableBody = $('#addvertisementModal .product-table');
        tableBody.empty();

        if (!items || items.length === 0) {
            tableBody.append('<tr><td colspan="4" class="text-center">No advertisements found</td></tr>');
            return;
        }

        items.forEach(item => {
            const row = `
             <tr>
                <td>
                    <img src="${item.sourceImage || 'images/default.jpg'}" 
                         width="100px" height="80px" class="img-thumbnail" 
                         onerror="this.src='images/default.jpg'" />
                </td>
                <td>${item.itemName || 'No name'}</td>
                <td class="text-center">${item.description || 'No description'}</td>
                <td class="text-center">${getCategoryName(item.category)}</td>
                <td class="text-center">Rs.${item.price ? item.price.toFixed(2) : '0.00'}</td>
                <td class="text-center">
                      <div class="btn-group">
                        <button class="btn btn-sm btn-outline-primary px-2 py-1" 
                                onclick="editItemModal(${item.itemCode})" 
                                data-bs-toggle="tooltip" title="Edit">
                          <i class="fas fa-pen"></i>
                        </button>
                        <button class="btn btn-sm btn-outline-danger px-2 py-1" 
                                onclick="deleteItemModal(${item.itemCode})" 
                                data-bs-toggle="tooltip" title="Delete">
                          <i class="fas fa-trash"></i>
                        </button>
                      </div>
                    </td>

            </tr>
        `;
            tableBody.append(row);

        });
    }



// Example edit function for modal
    function editItemModal(itemId) {
        // Your edit implementation
        console.log("Editing item:", itemId);
        // You might want to open another modal or redirect
    }

// Example delete function for modal
    function deleteItemModal(itemId) {
        if (confirm("Are you sure you want to delete this item?")) {
            // Your delete implementation
            console.log("Deleting item:", itemId);
        }
    }

    $('#addvertisement').on('show.bs.modal', function() {
        loadUsersItems();
    });
    // Helper functions
    function getCategoryName(categoryId) {
        const categories = {
            1: "Fish",
            2: "Filters",
            3: "Food",
            4: "Accessories"
        };
        return categories[categoryId] || "Other";
    }

    function escapeString(str) {
        return str ? str.replace(/'/g, "\\'") : '';
    }

    function showError(message) {
        $('.product-dashboard-table tbody').html(`
            <tr>
                <td colspan="4" class="text-center text-danger">
                    ${message}
                </td>
            </tr>
        `);
    }
});

// Global functions for edit/delete
function editItem(id, name, category, description, price, imageUrl, location, quantity) {
    console.log("Editing item:", {id, name, category, description, price, imageUrl, location, quantity});
    // Populate your edit modal here
    $('#editItemId').val(id);
    $('#editName').val(name);
    $('#editCategory').val(category);
    $('#editDescription').val(description);
    $('#editPrice').val(price);
    $('#editLocation').val(location);
    $('#editQuantity').val(quantity);
    // Show the modal
    $('#editModal').modal('show');
}

function deleteItem(id) {
    if (confirm('Are you sure you want to delete this item?')) {
        $.ajax({
            url: `http://localhost:8080/api/v1/addsItem/delete/${id}`,
            type: "DELETE",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            success: function(response) {
                alert('Item deleted successfully');
                location.reload();
            },
            error: function(xhr, status, error) {
                alert('Error deleting item: ' + error);
            }
        });
    }
}