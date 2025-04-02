$(document).ready(function () {
    const token = localStorage.getItem('token');

    if (!token) {
        window.location.href = "register.html";
        return;
    }

    // Load items immediately
    loadItems();

    function loadItems() {
        $.ajax({
            url: "http://localhost:8080/api/v1/addsItem/get",
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            success: function(response) {
                if (response && response.data) {
                    populateTable(response.data);
                } else {
                    showError("No data received from server");
                }
            },
            error: function(xhr, status, error) {
                showError("Error loading items: " + error);
            }
        });
    }

    function populateTable(items) {
        const tableBody = $('.product-dashboard-table tbody');
        tableBody.empty();
        const baseImagePath = "static/images/items";
        if (!items || items.length === 0) {
            tableBody.append('<tr><td colspan="4">No items found</td></tr>');
            return;
        }

        items.forEach(item => {
            console.log("Original imageUrl:", item.sourceImage);
            const row = `
                <tr>
                    <td class="product-thumb">
                        <img width="80px" height="auto" 
                             src="${item.sourceImage || 'images/products/products-1.jpg'}" 
                             alt="${item.name || 'Product image'}"
                             onerror="this.src='images/default.jpg'">
                    </td>
                    <td class="product-details">
                        <h3 class="title">${item.name || 'No name'}</h3>
                        <span class="add-id"><strong>ID:</strong> ${item.itemCode || 'N/A'}</span>
                        <span><strong>Description:</strong> ${item.description || 'N/A'}</span>
                        <span><strong>Price:</strong> $${item.price ? item.price.toFixed(2) : '0.00'}</span>
                        <span class="status active"><strong>Stock:</strong> ${item.quantity || 0}</span>
                        <span class="location"><strong>Location:</strong> ${item.location || 'N/A'}</span>
                    </td>
                    <td class="product-category">
                        <span class="categories">${getCategoryName(item.category)}</span>
                    </td>
                    <td class="action" data-title="Action">
                        <div class="">
                            <ul class="list-inline justify-content-center">
                                <li class="list-inline-item">
                                    <a data-toggle="tooltip" title="View" class="view" 
                                       href="product-detail.html?id=${item.id}">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a class="edit" href="#" 
                                       onclick="editItem(${item.id}, '${escapeString(item.name)}', ${item.category}, 
                                               '${escapeString(item.description)}', ${item.price}, 
                                               '${item.imageUrl}', '${escapeString(item.location)}', 
                                               ${item.quantity})">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a class="delete" href="#" onclick="deleteItem(${item.id})">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
            `;
            tableBody.append(row);
        });

        // Initialize tooltips
        $('[data-toggle="tooltip"]').tooltip();
    }

    // Helper functions
    function getCategoryName(categoryId) {
        const categories = {
            1: "Fish",
            2: "Equipment",
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