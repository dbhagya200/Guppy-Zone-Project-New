$(document).ready(function () {
    const token = localStorage.getItem('token');

    if (!token) {
        console.error("Token not found!");
        window.location.href = "register.html";
        return;
    }


    function loadProfile(){
        let token = localStorage.getItem("token")

        $.ajax({
            url: 'http://localhost:8080/api/v1/profile/me',
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token
            },
            success: function (response){
                $('#email').val(response.data.email);
                $('#name').text(response.data.firstName);
                $('#firstname').val(response.data.firstName);
                $('#lastname').val(response.data.lastName);
                $('#contact').val(response.data.contact);
                $('#address').val(response.data.address);
             //   $('#input-file').attr("src", response.data.input-file);
                $('#p-image').attr('src', response.data.image);

                console.log(response.data)
            },
            error:function (error) {
                console.log(error)
            }
        })
    }

    $("#logout").click(function () {
        localStorage.removeItem("token")
        window.location.href = "register.html";
    })


    async function updateProfile() {
        const token = localStorage.getItem("token");
        const submitBtn = $("#update");

        try {
            submitBtn.prop("disabled", true).text("Updating...");

            const formData = new FormData();
            formData.append("firstName", $("#firstname").val());
            formData.append("lastName", $("#lastname").val());
            formData.append("contact", $("#contact").val());
            formData.append("address", $("#address").val());

            // Corrected file field name (should match backend expectation)
            const fileInput = $("#input-file")[0];
            if (fileInput.files.length > 0) {
                formData.append("image", fileInput.files[0]); // Changed from "input-file"
            }

            const response = await $.ajax({
                url: 'http://localhost:8080/api/v1/profile/update',
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer ' + token
                },
                data: formData,
                contentType: false,
                processData: false,
                timeout: 10000
            });

            // Update the profile image display if a new image was uploaded
            if (fileInput.files.length > 0 && response.data.profileImageUrl) {
                $("#input-file").attr("src", response.data.profileImageUrl);
            }

            Swal.fire({
                icon: 'success',
                title: 'Success!',
                text: 'Profile updated successfully',
                timer: 2000
            });
            loadProfile();
        } catch (error) {
            console.error("Update error:", error);
            let errorMsg = 'Failed to update profile';

            if (error.responseJSON?.message) {
                errorMsg = error.responseJSON.message;
            } else if (error.status === 413) {
                errorMsg = "File too large (max 5MB)";
            } else if (error.status === 415) {
                errorMsg = "Invalid file type";
            } else if (error.status === 401) {
                errorMsg = "Session expired. Please login again.";
            }

            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: errorMsg
            });
        } finally {
            submitBtn.prop("disabled", false).text("Save My Changes");
        }
    }

    loadProfile();

    $("#update").click(function () {
        updateProfile();
    });

});