  var chart;
            $(document).ready(function(){


var ctx = document.getElementById("myChart").getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ["ITC", "Britannia", "Nestle", "Cadbury", "Coca-Cola", "Kissan"],
        datasets: [{
            label: 'Company',
            data: [12, 19, 8, 5, 15, 18],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        title: {
            display: true,
            position: "top",
            text: "Most Sold",
            fontSize: 18,
            fontColor: "#111"
        },
        legend: {
            display: true,
            position: "bottom",
            labels: {
                fontColor: "#333",
                fontSize: 16
            }
        },
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});

var graph_type = "Day Wise Sales.";
var labels_array = ["1", "2", "3", "4", "5", "6", "7", "9"];
var ctx = document.getElementById("daywisesales").getContext('2d');
var daywisesales = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels_array,
        datasets: [{
            label: 'Week',
            data: [12, 19, 8, 5, 15, 18, 26],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        title: {
            display: true,
            position: "top",
            text: graph_type,
            fontSize: 18,
            fontColor: "#111"
        },
        legend: {
            display: true,
            position: "bottom",
            labels: {
                fontColor: "#333",
                fontSize: 16
            }
        },
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});




var ctx_myLineChart = document.getElementById("myLineChart").getContext('2d');
var myLineChart = new Chart(ctx_myLineChart, {
    type: 'line',
    data: {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "June"],
        datasets: [{
            label: 'Monthly',
            data: [12, 19, 8, 5, 15, 18],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        title: {
            display: true,
            position: "top",
            text: "Sales",
            fontSize: 18,
            fontColor: "#111"
        },
        legend: {
            display: true,
            position: "bottom",
            labels: {
                fontColor: "#333",
                fontSize: 16
            }
        },
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero:true
                }
            }]
        }
    }
});




 //get the doughnut chart canvas
    var ctx1 = $("#doughnut-chartcanvas-1");

    //doughnut chart data
    var data1 = {
        labels: ["CGST", "SGST", "IGST"],
        datasets: [
            {
                label: "Tax Score",
                data: [60, 15, 25],
                backgroundColor: [
                    "#00BFFF",
                    "#800080",
                    "#FFA500"
                ],
                borderColor: [
                    "#CDA776",
                    "#989898",
                    "#CB252B"
                ],
                borderWidth: [1, 1, 1]
            }
        ]
    };

    var options = {
        responsive: true,
        title: {
            display: true,
            position: "top",
            text: "Taxes",
            fontSize: 18,
            fontColor: "#111"
        },
        legend: {
            display: true,
            position: "bottom",
            labels: {
                fontColor: "#333",
                fontSize: 16
            }
        }
    };

    //create Chart class object
    var chart1 = new Chart(ctx1, {
        type: "doughnut",
        data: data1,
        options: options
    });






chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container'
        },
        credits: {
            enabled: false
        },
        legend: {
            layout: 'horizontal',
            verticalAlign: 'bottom'
        },
        title: {
            text: ''
        },
        tooltip: {
            formatter: function () {
                if (this.series.name == 'Accumulated') {
                    return this.y + '%';
                }
                return this.x + '<br/>' + '<b> ' + this.y.toString().replace('.', ',') + ' </b>';
            }
        },
        xAxis: {
            categories: ['A', 'B', 'C', 'D']
        },
        yAxis: [{
            title: {
                text: ''
            }
        }, {
            labels: {
                formatter: function () {
                    return this.value + '%';
                }
            },
            max: 100,
            min: 0,
            opposite: true,
            plotLines: [{
                color: '#89A54E',
                dashStyle: 'shortdash',
                value: 80,
                width: 3,
                zIndex: 10
            }],
            title: {
                text: ''
            }
        }],
        series: [{
            data: [5.6000000000, 5.1000000000, 2.8000000000, 1.3000000000],
            name: 'Options',
            type: 'column'
        }, {
            data: [38, 72, 91, 100],
            name: 'Accumulated',
            type: 'spline',
            yAxis: 1,
            id: 'accumulated'
        }]
    },function(chart){


        var x = 0.8 * chart.plotWidth;

        chart.renderer.path([
                 'M',
                 x, chart.plotTop,
                 'L',
                 x, chart.plotTop + chart.plotHeight
            ]).attr({
                'stroke-width': 2,
                stroke: 'red',
                id: 'vert',
               'stroke-dasharray':"5,5",
                zIndex: 2000
            }).add();

    });








});
