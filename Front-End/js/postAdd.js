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
                $('#name').text(response.data.firstName);
                $('#p-image').attr('src', response.data.image);

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

    loadProfile();


});