$(document).ready(function () {
    // Check authentication
    const token = localStorage.getItem('token');
    if (!token) {
        redirectToLogin();
        return;
    }
    loadCategories();
    try {
        // Decode token to get user info
        console.log("Raw token:", token); // Debug

        const decoded = jwt_decode(token);
        console.log("Decoded token:", decoded); // Debug

        // Try different common claim names
        const userEmail = decoded.sub || decoded.email || decoded.username || decoded.userEmail;

        if (!userEmail) {
            throw new Error("No user identifier found in token");
        }

        console.log("User email/username:", userEmail); // Debug
        $('#email').val(userEmail);

        // Load categories when modal opens
        $('#addPostModal').on('show.bs.modal', function () {
            console.log("Modal opened - loading categories");
            loadCategories();
        });


    } catch (error) {
        console.error("Authentication error:", error);
        showError("Session invalid. Please login again.");
        redirectToLogin();
    }

    // Function to load categories
    function loadCategories() {
        $.ajax({
            url: "http://localhost:8080/api/v1/categories/get",
            type: "GET",
            headers: {
                "Authorization": "Bearer " + token
            },
            dataType: "json",
            success: function(response) {
                console.log("Categories response:", response.data); // Debug log

                // Check if response has the expected structure
                if (response.data && Array.isArray(response.data)) {
                    const $select = $('#itemCategory');
                    $select.empty().append('<option value="" disabled selected>Choose a category</option>');

                    response.data.forEach(category => {
                        $select.append(`<option value="${category.categoryId}">${category.name}</option>`);
                        console.log("Appending category:", category.categoryId);
                    });
                } else {
                    console.error("Unexpected response format:", response.data);
                    $('#itemCategory').html('<option value="" disabled selected>Error loading categories</option>');
                }
            },
            error: function(xhr, status, error) {
                console.error("Failed to load categories:", status, error);
                $('#itemCategory').html('<option value="" disabled selected>Error loading categories</option>');
                showError('Failed to load categories. Please try again later.');
            }
        });
    }

    // Image preview functionality
    $('#itemImages').on('change', function () {
        $('#imagePreview').empty();
        const files = this.files;

        if (files.length > 5) {
            showWarning('You can upload maximum 5 images');
            $(this).val('');
            return;
        }

        Array.from(files).forEach(file => {
            if (!file.type.match('image.*')) {
                showError('Only image files are allowed');
                $(this).val('');
                return;
            }

            const reader = new FileReader();
            reader.onload = function (e) {
                $('#imagePreview').append(`
                    <div class="image-preview-item m-2 position-relative" data-filename="${file.name}">
                        <img src="${e.target.result}" class="img-thumbnail rounded shadow-sm"
                             style="width: 100px; height: 100px; object-fit: cover;">
                        <span class="remove-image position-absolute top-0 end-0 bg-white rounded-circle p-1"
                              style="transform: translate(30%, -30%); cursor: pointer;">
                            <i class="fas fa-times text-danger"></i>
                        </span>
                    </div>
                `);
            };
            reader.readAsDataURL(file);
        });
    });

    // Remove image preview
    $(document).on('click', '.remove-image', function () {
        const filename = $(this).closest('.image-preview-item').data('filename');
        const input = $('#itemImages')[0];
        const files = Array.from(input.files);

        // Remove the file from the input
        const updatedFiles = files.filter(file => file.name !== filename);

        // Create new DataTransfer and set updated files
        const dataTransfer = new DataTransfer();
        updatedFiles.forEach(file => dataTransfer.items.add(file));
        input.files = dataTransfer.files;

        // Remove the preview
        $(this).closest('.image-preview-item').remove();
    });

    // Form submission handler
    $('#submitPost').click(function (e) {
        e.preventDefault();

        if (!validateForm()) return;

        const formData = prepareFormData();

        submitAdvertisement(formData);
    });

    // Validate the form
    function validateForm() {
        const form = $('#postAddForm')[0];

        if (!form.checkValidity()) {
            form.classList.add('was-validated');
            scrollToFirstInvalidField();
            return false;
        }

        if (!$('#agreeTerms').is(':checked')) {
            showError('You must agree to the terms and conditions');
            return false;
        }

        const files = $('#itemImages')[0].files;
        if (!files || files.length === 0) {
            showError('Please upload at least one image');
            return false;
        }

        return true;
    }

    // Prepare FormData for submission
    function prepareFormData() {
        const formData = new FormData();
        formData.append('title', $('#itemTitle').val());
        formData.append('categoryId', $('#itemCategory').val());
        formData.append('price', $('#itemPrice').val());
        formData.append('quantity', $('#itemQuantity').val());
        formData.append('location', $('#itemLocation').val());
        formData.append('description', $('#itemDescription').val());
        formData.append('email', $('#email').val());

        // Append all selected images
        Array.from($('#itemImages')[0].files).forEach((file, index) => {
            formData.append('images', file);
        });

        return formData;
    }

    // Submit advertisement to server
    function submitAdvertisement(formData) {
        const submitBtn = $('#submitPost');
        submitBtn.prop('disabled', true).html('<i class="fas fa-spinner fa-spin mr-2"></i> Posting...');

        $.ajax({
            url: 'http://localhost:8080/api/v1/addsItem/saveItem',
            type: 'POST',
            headers: {
                'Authorization': 'Bearer ' + token
            },
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                if (response.code === "200") {
                    showSuccess('Your item has been posted successfully');
                    resetForm();
                    $('#addPostModal').modal('hide');
                    // You might want to refresh the ads list here
                } else {
                    showError(response.message || 'Failed to post item');
                }
            },
            error: function (xhr) {
                handleSubmissionError(xhr);
            },
            complete: function () {
                submitBtn.prop('disabled', false).html('<i class="fas fa-paper-plane mr-2"></i> Post Item');
            }
        });
    }

    // Helper functions
    function scrollToFirstInvalidField() {
        const firstInvalid = $('.is-invalid').first();
        if (firstInvalid.length) {
            $('html, body').animate({
                scrollTop: firstInvalid.offset().top - 100
            }, 500);

            showError('Please fill all required fields correctly. The first invalid field has been highlighted.');
        }
    }

    function resetForm() {
        $('#postAddForm')[0].reset();
        $('#postAddForm')[0].classList.remove('was-validated');
        $('#imagePreview').empty();
    }

    function redirectToLogin() {
        Swal.fire({
            icon: 'error',
            title: 'Authentication Required',
            text: 'Please login first',
            confirmButtonColor: '#28a745'
        }).then(() => {
            window.location.href = "register.html";
        });
    }

    function showError(message) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: message,
            confirmButtonColor: '#28a745'
        });
    }

    function showWarning(message) {
        Swal.fire({
            icon: 'warning',
            title: 'Warning',
            text: message,
            confirmButtonColor: '#28a745'
        });
    }

    function showSuccess(message) {
        Swal.fire({
            icon: 'success',
            title: 'Success!',
            text: message,
            confirmButtonColor: '#28a745'
        });
    }

    function handleSubmissionError(xhr) {
        let errorMessage = 'Failed to post item. Please try again.';
        if (xhr.responseJSON && xhr.responseJSON.message) {
            errorMessage = xhr.responseJSON.message;
        } else if (xhr.status === 413) {
            errorMessage = 'File size too large. Please upload smaller images.';
        } else if (xhr.status === 401) {
            errorMessage = 'Session expired. Please login again.';
            redirectToLogin();
            return;
        }

        showError(errorMessage);
    }

    // Clear validation styling on input
    $('input, textarea, select').on('input change', function () {
        $(this).removeClass('is-invalid');
    });
});