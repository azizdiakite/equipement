$(document).ready(function () {
   //temps total d'arrêt des équipements
   $.get("/dashboard/total_breakdown_time" + window.location.search,
      function (data) {
         Highcharts
            .chart(
               'equip_breakdown_time', {
                  chart: {
                     type: 'column'
                  },
                  title: {
                     text: "Temps total d'arrêts des équipements(En jours)"
                  },
                  xAxis: {
                     type: 'category'
                  },
                  yAxis: {
                     title: {
                        text: "Nombre de jours"
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
                     pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y} jours </b><br/>'
                  },

                  series: [{
                     name: "Equipements",
                     colorByPoint: true,
                     data: data
                  }]
               });
      });

   //equipement par type de labo
   $.get("/dashboard/total_repair_time" + window.location.search,
      function (data) {
         Highcharts
            .chart(
               'equip_repair_time', {
                  chart: {
                     type: 'column'
                  },
                  title: {
                     text: "Temps total des réparations des équipements(En jours)"
                  },
                  xAxis: {
                     type: 'category'
                  },
                  yAxis: {
                     title: {
                        text: "Nombre de jours"
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
                     pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y} jours </b><br/>'
                  },

                  series: [{
                     name: "Equipements",
                     colorByPoint: true,
                     data: data
                  }]
               });
      });

   //délai entre pannes
   $.get("/dashboard/mtbf" + window.location.search,
      function (data) {
         Highcharts
            .chart(
               'mtbf', {
                  chart: {
                     type: 'column'
                  },
                  title: {
                     text: "Délai moyen entre pannes(En jours)"
                  },
                  xAxis: {
                     type: 'category'
                  },
                  yAxis: {
                     title: {
                        text: "Nombre de jours"
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
                     pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y} jours</b><br/>'
                  },

                  series: [{
                     name: "Equipements",
                     colorByPoint: true,
                     data: data
                  }]
               });
      });

   //Taux de disponibilité des équipements
   $
      .get(
         "/dashboard/availability" + window.location.search,
         function (data) {
            Highcharts
               .chart(
                  'availability', {
                     chart: {
                        type: 'column'
                     },
                     title: {
                        text: "Taux de disponibilité des équipements(En jours)"
                     },
                     xAxis: {
                        type: 'category'
                     },
                     yAxis: {
                        title: {
                           text: "Nombre de jours",
                        },
                        allowDecimals: false,
                        max: 100,

                     },
                     legend: {
                        enabled: false
                     },
                     plotOptions: {
                        series: {
                           borderWidth: 0,
                           dataLabels: {
                              enabled: true,
                              format: '{y:.2f} %'
                           }
                        }
                     },
                     tooltip: {
                        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f} %</b><br/>'
                     },

                     series: [{
                        name: "Equipements",
                        colorByPoint: true,
                        data: data
                     }]
                  });
         });

   //délai moyen des réparations
   $
      .get(
         "/dashboard/mttr" + window.location.search,
         function (data) {
            Highcharts
               .chart(
                  'mttr', {
                     chart: {
                        type: 'column'
                     },
                     title: {
                        text: "Temps moyen des réparations(En jours)"
                     },
                     xAxis: {
                        categories: data.categories,
                        crosshair: true
                     },
                     yAxis: {
                        title: {
                           text: "Nombre de jours"
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
                           name: "Délai moyen des réparations",
                           data: data.TimeToRepair,
                           color: "#AADD44"
                        },
                        {
                           name: "Durée moyenne des réparations",
                           data: data.ReparationTime,
                           color: "#66BB99"
                        }
                     ]
                  });
         });


   //Nombre de pannes pour l'année en cours
   $
      .get(
         "/dashboard/year_first_maintenance" +
         window.location.search,
         function (data) {
            Highcharts
               .chart(
                  'year_first_maintenance', {
                     chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie'
                     },
                     title: {
                        text: "Nombre de maintenance pour l'année " + new Date().getFullYear()
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
                              format: '<b>{point.name}</b>',
                              connectorColor: 'silver'
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
                        data: data.datas,
                     }]
                  });
         });
});