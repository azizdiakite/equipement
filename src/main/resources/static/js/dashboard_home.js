$(document).ready(function() {
	$.get(
		"/dashboard/equipement_by_name" + window.location.search,
		function(data) {
			Highcharts
				.chart(
					'equipement_cat', {
					chart: {
						type: 'column'
					},
					title: {
						text: "Nombres d'équipements par catégorie"
					},
					xAxis: {
						type: 'category'
					},
					yAxis: {
						title: {
							text: "Nombre d'équipements"
						}

					},
					legend: {
						enabled: false
					},
					plotOptions: {
						series: {
							borderWidth: 0,
							dataLabels: {
								enabled: true,
							}
						}
					},

					tooltip: {
						headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
					},

					series: [{
						name: "Equipements",
						colorByPoint: true,
						data: data
					}]
				});
		});

	//equipement par type de labo
	$.get("/dashboard/equipement_by_lab_type" + window.location.search,
		function(data) {
			Highcharts
				.chart(
					'equipement_lab_type', {
					chart: {
						type: 'pie'
					},
					title: {
						text: "Nombres d'équipements par type de laboratoire"
					},
					legend: {
						enabled: true
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								format: '<b>{point.name}</b>: {point.y} ({point.percentage:.1f}) %'
							}
						}
					},
					tooltip: {
						pointFormat: '{series.name}: <b>{point.y} ({point.percentage:.1f}%)</b>'
					},

					series: [{
						name: "Equipements",
						colorByPoint: true,
						data: data
					}]
				});
		});

	//garanti et sous contrat
	$.get("/dashboard/equipement_warrant" +
		window.location.search,
		function(data) {
			Highcharts
				.chart(
					'equipement_warrant', {
					chart: {
						type: 'column'
					},
					title: {
						text: "Equipements sous garanti et sous contrat"
					},
					xAxis: {
						type: 'category'
					},
					yAxis: {
						title: {
							text: "Nombre d'équipements"
						}

					},
					legend: {
						enabled: false
					},
					plotOptions: {
						series: {
							borderWidth: 0,
							dataLabels: {
								enabled: true,
							}
						}
					},

					tooltip: {
						headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
					},

					series: [{
						name: "Equipements",
						colorByPoint: true,
						data: data
					}]
				});
		});

	//type de maintenance
	/*		$
					.get(
							"/dashboard/equipement_by_maintenance_type?l_id=0&e_id=0",
							function(data) {
								Highcharts
										.chart(
												'maintenance_type',
												{
													chart : {
														type : 'pie'
													},
													title : {
														text : "Maintenance réalisées par type"
													},
													legend : {
														enabled : true
													},
													plotOptions : {
														pie : {
															allowPointSelect : true,
															cursor : 'pointer',
															dataLabels : {
																enabled : true,
																format : '<b>{point.name}</b>: {point.percentage:.1f} %'
															}
														}
													},
													tooltip : {
														pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
													},

													series : [ {
														name : "Equipements",
														colorByPoint : true,
														data : data
													} ]
												});
							}); */

	//type de panne rapportée
	$.get("/dashboard/panne_type" + window.location.search,
		function(data) {
			Highcharts
				.chart(
					'panne_type', {
					chart: {
						type: 'bar'
					},
					title: {
						text: "Classification des pannes rapportées"
					},
					xAxis: {
						type: 'category'
					},
					yAxis: {
						title: {
							text: "Nombre d'équipements",
						},
						allowDecimals: false

					},
					legend: {
						enabled: false
					},
					plotOptions: {
						series: {
							borderWidth: 0,
							dataLabels: {
								enabled: true,
							}
						}
					},
					tooltip: {
						headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
					},

					series: [{
						name: "Equipements",
						colorByPoint: true,
						data: data
					}]
				});
		});

	//Maintenances préventives (planifiées et réalisées)
	$
		.get(
			"/dashboard/preventive_maintenance" +
			window.location.search,
			function(data) {
				Highcharts
					.chart(
						'preventive_maintenance', {
						chart: {
							type: 'column'
						},
						title: {
							text: "Nombre de maintenance préventive planifiée et réalisée"
						},
						xAxis: {
							categories: data.categories,
							crosshair: true
						},
						yAxis: {
							title: {
								text: "Nombre de maintenance"
							}
						},
						legend: {
							enabled: true
						},
						plotOptions: {
							series: {
								allowPointSelect: true,
								dataLabels: {
									enabled: true,
								}
							}
						},
						tooltip: {
							headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
								'<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
							footerFormat: '</table>',
							shared: true,
							useHTML: true
						},

						series: [{
							name: "Maintenances préventives planifiées",
							data: data.prev,
							color: "#AADD44"
						},
						{
							name: "Maintenances préventives réalisées",
							data: data.done,
							color: "#66BB99"
						}
						]
					});
			});

	//Maintenances curatives
	$
		.get(
			"/dashboard/curative_maintenance" +
			window.location.search,
			function(data) {
				Highcharts
					.chart(
						'curative_maintenance', {
						chart: {
							type: 'column'
						},
						title: {
							text: "Nombre de maintenance curative"
						},
						xAxis: {
							categories: data.categories,
							crosshair: true
						},
						yAxis: {
							title: {
								text: "Nombre de maintenance"
							}
						},
						legend: {
							enabled: true
						},
						plotOptions: {
							series: {
								allowPointSelect: true,
								dataLabels: {
									enabled: true,
								}
							}
						},
						tooltip: {
							headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
							pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
								'<td style="padding:0"><b>{point.y:.0f} </b></td></tr>',
							footerFormat: '</table>',
							shared: true,
							useHTML: true
						},

						series: [{
							name: "Maintenances curatives réalisées",
							data: data.done,
							color: "#22AA99"
						}]
					});
			});
});