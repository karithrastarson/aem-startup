$(document).on("click", "#minus", function() {

    $.ajax({
        url: "/services/CounterServlet",
        type: "POST",
        data: {
            firstname: firstname,
            lastname: lastname
        },
        success: function (data) {
        	
        	alert("Success!");
        },
        error: function () {
        	alert("Error!");


        }
    });
});

$(document).on("click", "#plus", function() {

    $.ajax({
        url: "/services/CounterServlet",
        type: "POST",
        data: {
            firstname: firstname,
            lastname: lastname
        },
        success: function (data) {
        	
        	alert("Success!");
        },
        error: function () {
        	alert("Error!");


        }
    });
});