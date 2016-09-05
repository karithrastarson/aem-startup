$(document).on("click", "#nudge", function() {
	$.ajax({
		url: "/bin/startworkflow",
		type: "GET",
	    contentType: false,
		success: function () {
			document.getElementById("done").innerHTML = "Nudge delivered";
		},
		error: function () {
			document.getElementById("done").innerHTML = "Something went wrong. Try again later";
		}
	});
});
