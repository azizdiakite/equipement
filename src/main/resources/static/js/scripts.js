/*!
	* Start Bootstrap - SB Admin v7.0.5 (https://startbootstrap.com/template/sb-admin)
	* Copyright 2013-2022 Start Bootstrap
	* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
	*/
// 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

	// Toggle the side navigation
	const sidebarToggle = document.body.querySelector('#sidebarToggle');
	if (sidebarToggle) {
		// Uncomment Below to persist sidebar toggle between refreshes
		// if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
		//     document.body.classList.toggle('sb-sidenav-toggled');
		// }
		sidebarToggle.addEventListener('click', event => {
			event.preventDefault();
			document.body.classList.toggle('sb-sidenav-toggled');
			localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
		});
	}

});

$(document).ready(function() {
	var table = $('#datatablesSimple').DataTable(
		{
			dom: 'Bfrtip',
			buttons: [
				'csv', 'excel', 'pdf'
			]
		}
	);
});

//update list after lab selection
updateList = function(e) {
	let labId = (e.value != -1) ? e.value : null;
	const parser = new URL(window.location);
	if (labId) {
		parser.searchParams.set('l_id', labId);
		window.location = parser.href;
	} else {
		parser.searchParams.delete('l_id');
		window.location = parser.href;
	}
}

filterByDate = function() {
	let date1 = $('#searchStartDate').val();
	let date2 = $('#searchEndDate').val();
	const parser = new URL(window.location);
	if (date1) {
		parser.searchParams.set('start', date1);
	}
	else {
		parser.searchParams.delete('start');
	}
	if (date2) {
		parser.searchParams.set('end', date2);
	}
	else {
		parser.searchParams.delete('end');
	}
	window.location = parser.href


}
