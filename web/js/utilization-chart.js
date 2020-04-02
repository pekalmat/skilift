
// this script generates the utilization table on the detail pages

 

// step 1: 

// step 2: call server to generate forecast data
var auslastung = [2478,3267,4734,5784,5000,3000,4000,4200,4330,4000,2500] //Auslastung, Numbers used to generate in chart
timeLabels = ["7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00" ]


// step 5: generate bar-chart
new Chart(document.getElementById("utilization-chart"), {
    type: 'bar',
    data: {
        labels: timeLabels, //to be calculated by the server
        datasets: [
            {
                label: "Auslastung",
                backgroundColor: "#787878",
                data: auslastung // to be calculated by the server
            }
        ]
    },
    options: {
        legend: { display: false },
        title: {
            display: true,
            text: 'Auslastung',
            fontSize: 20
        },
        scales: {
            xAxes: [{
                gridLines: {
                    color: "rgba(0, 0, 0, 0)",
                },
                gridLines: {
                    display: false,
                },
                ticks: {
                    fontSize: 20,
                    fontStyle: "bold"
                }                
                
            }],
            yAxes: [{
                gridLines: {
                    color: "rgba(0, 0, 0, 0)",
                },
                ticks: {
                    display: false
                }  
            }]
        },
        ticks: {
            display: false
        }
    }
});