$(document).on("click", "#get", function() {

var url = "http://www.dr.dk/nyheder/kultur/historie/10-historikere-vurderer-er-vi-paa-vej-mod-endnu-en-kold-krig";
	$.ajax({
		url: "/services/embedly",
		type: "GET",
		data: {
			url: url
		},
		contentType:"application/json",
		success: function (data) {

			var response = data[0].url;
			document.getElementById("json").innerHTML = response;
		},
		error: function () {
			alert("Error!");
		}
	});
});
