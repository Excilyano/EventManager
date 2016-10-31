/**
 * 
 */
function show(id) {
	document.getElementById("calque").style.display = "block";
	document.getElementById(id).style.display = "block";
}

function hide(id) {
	document.getElementById("calque").style.display = "none";
	document.getElementById(id).style.display = "none";
}

function redirect(url){
	window.location.href = url;
}