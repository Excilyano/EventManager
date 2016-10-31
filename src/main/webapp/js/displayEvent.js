/**
 * 
 */
function show(id) {
	document.getElementById("calque").className = "";
	document.getElementById(id).className = "modale";
}

function hide(id) {
	document.getElementById("calque").className = "invisible";
	document.getElementById(id).className = "modale invisible";
}

function redirect(url){
	window.location.href = url;
}