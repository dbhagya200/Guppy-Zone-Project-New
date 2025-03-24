
    $(document).ready(function () {
    // Edit Personal Information Form Submission
    $('.personal-info form').submit(function (e) {
        e.preventDefault(); // Prevent default form submission

        // Get form data
        let formData = {
            firstName: $('#first-name').val(),
            lastName: $('#last-name').val(),
            communityName: $('#comunity-name').val(),
            zipCode: $('#zip-code').val(),
            hideProfile: $('#hide-profile').is(':checked')
        };

        // Send AJAX request
        $.ajax({
            url: 'http://localhost:8080/api/v1/user/update-profile', // Replace with your API endpoint
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                Swal.fire({
                    icon: 'success',
                    title: 'Success!',
                    text: 'Profile updated successfully.',
                });
            },
            error: function (xhr) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: xhr.responseJSON ? xhr.responseJSON.message : 'An error occurred.',
                });
            }
        });
    });


        $('.change-password form').submit(function (e) {
            e.preventDefault(); // Prevent default form submission

            // Get form data
            let formData = {
                currentPassword: $('#current-password').val(),
                newPassword: $('#new-password').val(),
                confirmPassword: $('#confirm-password').val()
            };

            // Validate passwords
            if (formData.newPassword !== formData.confirmPassword) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'New password and confirm password do not match.',
                });
                return;
            }

            // Send AJAX request
            $.ajax({
                url: 'http://localhost:8080/api/v1/user/change-password', // Replace with your API endpoint
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function (response) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Success!',
                        text: 'Password changed successfully.',
                    });
                },
                error: function (xhr) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: xhr.responseJSON ? xhr.responseJSON.message : 'An error occurred.',
                    });
                }
            });
        });

        $('.change-email form').submit(function (e) {
            e.preventDefault(); // Prevent default form submission

            // Get form data
            let formData = {
                currentEmail: $('#current-email').val(),
                newEmail: $('#new-email').val()
            };

            // Send AJAX request
            $.ajax({
                url: 'http://localhost:8080/api/v1/user/change-email', // Replace with your API endpoint
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function (response) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Success!',
                        text: 'Email changed successfully.',
                    });
                },
                error: function (xhr) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: xhr.responseJSON ? xhr.responseJSON.message : 'An error occurred.',
                    });
                }
            });
        });
});
