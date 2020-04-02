
// this script generates the forecast table on the home page

// generated variables
var forecastDays; //Day names displayed in the forecast
var auslastung; //Auslastung, Numbers used to generate in chart

// step 1: determine next 5 days for forcast
var today = new Date();
var weekdays = ["SO", "MO", "DI", "MI", "DO", "FR", "SA"]
var today = today.getDay(); //0 for Sunday, 1 for Monday, 2 for Tuesday, 3 for Wednesday, 4 for Thursday, 5 for Friday, 6 for Saturday.
forecastDays = [weekdays[today]];
for (var i = today+1; i <= 6; i++) {
    forecastDays.push(weekdays[i]);
}
var i = 0;
while (forecastDays.length != 5) {
    forecastDays.push(weekdays[i]);
    i = i + 1;
}

// step 2: call server to generate forecast data
auslastung = [2478,5267,734,784,433]
auslastungProzent = ["40%", "80%", "10%", "10%", "5%" ]

// step 3: add weather picture above bar-chart
var pictureSize = 45;

let randowWeatherType = Math.floor(Math.random()*(5-1+1)+1); // weather type will be determined by the server, for now we just return random weather picture
var img = document.createElement("img");
img.src = "img/weather" + randowWeatherType +".png";
img.width = pictureSize;
img.height = pictureSize;
document.getElementById("weather0").appendChild(img);

randowWeatherType = Math.floor(Math.random()*(5-1+1)+1); // weather type will be determined by the server, for now we just return random weather picture
var img = document.createElement("img");
img.src = "img/weather" + randowWeatherType +".png";
img.width = pictureSize;
img.height = pictureSize;
document.getElementById("weather1").appendChild(img);

randowWeatherType = Math.floor(Math.random()*(5-1+1)+1); // weather type will be determined by the server, for now we just return random weather picture
var img = document.createElement("img");
img.src = "img/weather" + randowWeatherType +".png";
img.width = pictureSize;
img.height = pictureSize;
document.getElementById("weather2").appendChild(img);

randowWeatherType = Math.floor(Math.random()*(5-1+1)+1); // weather type will be determined by the server, for now we just return random weather picture
var img = document.createElement("img");
img.src = "img/weather" + randowWeatherType +".png";
img.width = pictureSize;
img.height = pictureSize;
document.getElementById("weather3").appendChild(img);

randowWeatherType = Math.floor(Math.random()*(5-1+1)+1); // weather type will be determined by the server, for now we just return random weather picture
var img = document.createElement("img");
img.src = "img/weather" + randowWeatherType +".png";
img.width = pictureSize;
img.height = pictureSize;
document.getElementById("weather4").appendChild(img);

// step 4: Add Day Names Above the bar-chart
var dayLabel = document.getElementById("dayLabel0");
var text = document.createTextNode(forecastDays[0]);
dayLabel.appendChild(text);

var dayLabel = document.getElementById("dayLabel1");
var text = document.createTextNode(forecastDays[1]);
dayLabel.appendChild(text);

var dayLabel = document.getElementById("dayLabel2");
var text = document.createTextNode(forecastDays[2]);
dayLabel.appendChild(text);

var dayLabel = document.getElementById("dayLabel3");
var text = document.createTextNode(forecastDays[3]);
dayLabel.appendChild(text);

var dayLabel = document.getElementById("dayLabel4");
var text = document.createTextNode(forecastDays[4]);
dayLabel.appendChild(text);

// step 5: generate bar-chart
new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: {
        labels: auslastungProzent, //to be calculated by the server
        datasets: [
            {
                label: "",
                backgroundColor: ["#787878", "#787878","#787878","#787878","#787878"],
                data: auslastung // to be calculated by the server
            }
        ]
    },
    options: {
        legend: { display: false },
        title: {
            display: true,
            text: ''
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