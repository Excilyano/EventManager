/**
 * 
 */
function show(id) {
	document.getElementById("calque").style.visibility = "visible";
	document.getElementById(id).style.visibility = "visible";
}

function hide(id) {
	document.getElementById("calque").style.visibility = "hidden";
	document.getElementById(id).style.visibility = "hidden";
}

function redirect(url){
	window.location.href = url;
}