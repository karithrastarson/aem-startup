$(document).on("click", "#minus", function() {
       var sign = "minus";
    $.ajax({
        url: "/services/counterServlet",
        type: "POST",
        data: {
            sign: sign
        },
        success: function (data) {
        	
        	var response = data[0].newVal;
          	 document.getElementById("bigcounter").innerHTML = response;
        },
        error: function () {
        	alert("Error!");


        }
    });
});
$(document).on("click", "#plus", function() {
    var sign = "plus";

    $.ajax({
        url: "/services/counterServlet",
        type: "POST",
        data: {
            sign: sign
        },
        
        success: function (data) {
        	
          	var response = data[0].newVal;
        	 document.getElementById("bigcounter").innerHTML = response;
        },
        error: function () {
        	alert("Error!");


        }
    });
});
$(document).on("click", "#reset", function() {
    var sign = "reset";

    $.ajax({
        url: "/services/counterServlet",
        type: "POST",
        data: {
            sign: sign
        },
        
        success: function (data) {
        	
          	var response = data[0].newVal;
        	 document.getElementById("bigcounter").innerHTML = response;
        },
        error: function () {
        	alert("Error!");


        }
    });
});