$.document.ready(function () {
    const token = localStorage.getItem('token');

    if (!token) {
        console.error("Token not found!");
        window.location.href = "register.html";
        return;
    }

    function getAllItemsForBrowse() {
        $.ajax({
            url: "http://localhost:8080/api/v1/addsItem/getAll",
            type: "GET",
            success: function (response) {
                const tableBody = $('.product-dashboard-table tbody');
                tableBody.empty(); // Clear existing rows

                for (let item of response.data) {
                    console.log(item);
                    tableBody.append(`
                <tr>
                    <td class="product-thumb">
                        <img width="80px" height="auto" src="${item.image || 'images/products/products-1.jpg'}" alt="${item.name}"></td>
                    <td class="product-details">
                        <h3 class="title">${item.name}</h3>
                        <span class="add-id"><strong>Ad ID:</strong> ${item.id || 'N/A'}</span>
                        <span><strong>Posted on: </strong><time>${new Date(item.createdAt).toLocaleDateString() || 'N/A'}</time></span>
                        <span class="status active"><strong>Status</strong>Active</span>
                        <span class="location"><strong>Location</strong>${item.location || 'N/A'}</span>
                    </td>
                    <td class="product-category"><span class="categories">${item.category || 'Uncategorized'}</span></td>
                    <td class="action" data-title="Action">
                        <div class="">
                            <ul class="list-inline justify-content-center">
                                <li class="list-inline-item">
                                    <a data-toggle="tooltip" data-placement="top" title="View" class="view" href="#">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a class="edit" href="#">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a class="delete" href="#">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
                `);
                }

                // Initialize tooltips
                $('[data-toggle="tooltip"]').tooltip();
            },
            error: function (error) {
                console.error("Error fetching items:", error);
                $('.product-dashboard-table tbody').html(`
                <tr>
                    <td colspan="4" class="text-center text-danger">
                        Failed to load items. Please try again later.
                    </td>
                </tr>
            `);
            }
        });
    }

});