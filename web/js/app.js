Vue.use(VueRouter);

// ################# RESERVATION COMPONENTS #####################################################################################
var appReservation = Vue.component('app-reservation', {
    data: function () {
        return {
            gastronomy: null,
            reservation: {
                vorname: '',
                nachname: '',
                email: '',
                innen: false,
                aussen: false,
                anzPersonen: '',
                zeit: ''
            }
        }
    },
    template: `
        <div id="content">
            <div class="d-flex flex-column bg-white contentWrapper">

                <div class="d-flex w-100 align-items-center my-2">
                    <routerLink :to="getBackLinkToGastronomy(gastronomy.id)" ><button class="back-button"></button></routerLink>
                    <img class="pictogramM mx-1" :src="showGastronomyTypeImage(gastronomy.gastronomyType)">
                    <h1>{{gastronomy.name}}</h1>
                </div>

                <div class="d-flex align-items-center flex-column my-auto">
                    <h3>Reservation</h3>
                    <form>
                        <div class="form-group">
                            <label for="vorname">Vorname</label>
                            <input type="text" class="form-control" id="vorname" placeholder="Vorname" v-model="reservation.vorname">
                        </div>
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input type="text" class="form-control" id="name" placeholder="Name" v-model="reservation.nachname">
                        </div>
                        <div class="form-group">
                            <label for="email">Email address</label>
                            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email" v-model="reservation.email">
                            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="form-group">
                            <label for="seatType">Sitzplatz</label>
                            <div id="seatType">
                                <fieldset>
                                    <input type="radio" id="innen" name="type" value="innen" v-model="reservation.innen">
                                    <label for="innen">Innen</label>
                                    <input type="radio" id="aussen" name="type" value="aussen" v-model="reservation.aussen">
                                    <label for="aussen">Aussen</label>
                                </fieldset>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="seats">Anzahl Personen</label>
                            <input type="number" class="form-control" id="seats" placeholder="Anzahl Personen" v-model="reservation.anzPersonen">
                        </div>
                        <div class="form-group">
                            <label for="time">Zeit</label>
                            <input type="time" class="form-control" id="time" placeholder="Zeit" v-model="reservation.zeit">
                        </div>
                    </form>
                    <button type="submit" v-on:click="submitReservation()" class="btn btn-primary">Reservieren</button>
                </div>
            </div>
        </div>
    `,
    mounted: function () {
        this.getGastronomyDetails();
    },
    methods: {
        getGastronomyDetails: function () {
            var gastronomyId = this.$route.params.id;
            axios.get('http://127.0.0.1:8081/skiapp/gastronomy/'+gastronomyId)
                .then( (response) => {
                    this.gastronomy = response.data;
                })
                .catch ( (error) => {
                    console.log(error);
                })
        },
        getBackLinkToGastronomy: function (gastronomyId) {
            return '/gastronomy/' + gastronomyId;
        },
        showGastronomyTypeImage: function (gastronomyType) {
            if (gastronomyType.id == 1) {
                return '../img/bar.png';
            } else if (gastronomyType.id == 2 || gastronomyType.id == 3) {
                return '../img/restaurant.png';
            } 
        },
        submitReservation: function () {
            console.log("reservation submited");
        }
    }
})

// ################# GASTRONOMY DETAIL COMPONENTS ##################################################################################### 
var appGastronomy = Vue.component('app-gastronomy', {
    data: function () {
        return {
            gastronomy: null
        }
    },
    template: `
        <div class="d-flex flex-column bg-white contentWrapper">

            <div class="d-flex w-100 align-items-center my-2">
                <router-link to="/gastronomies"><button class="back-button"></button></router-Link>
                <img class="pictogramM mx-1" :src="showGastronomyTypeImage(gastronomy.gastronomyType)">
                <h1>{{gastronomy.name}}</h1>
            </div>

            <div class="d-flex align-items-start flex-column pl-4">
                <p>{{gastronomy.gastronomyType.gastronomyType}}</p>
                <p class="mt-1"><span class="font-weight-bold">Spezialität:</span> {{gastronomy.speciality}}</p>
                <div class="mt-1 w-100">
                    <p class="font-weight-bold">Aktuell Freie Plätze:</p>
                    <div class="d-flex w-100">
                        <div class="">
                            <p>Aussen: {{gastronomy.outdoorSeats}}</p>
                            <p>Innen: {{gastronomy.indoorSeats}}</p>
                        </div>

                        <div class="m-auto">
                            <router-link :to="getDynamicRouteToReservation(gastronomy.id)">
                                <button class="reservation-button">Platz reservieren</button>
                            </router-Link>
                        </div>
                    </div>

                </div>
            </div>

            <div class="d-flex flex-column">
                <canvas id="utilization-chart" width="550" height="270"></canvas>
            </div>

            <img class="w-100" height="250" src="../img/LiftsAndSlopes.png">

        </div>

    `,
    mounted: function () {
        this.getGastronomyDetails();
        this.getGastronomyUtilizationData();
    },
    methods: {
        getGastronomyDetails: function () {
            var gastronomyId = this.$route.params.id;
            axios.get('http://127.0.0.1:8081/skiapp/gastronomy/'+gastronomyId)
                .then( (response) => {
                    this.gastronomy = response.data;
                })
                .catch ( (error) => {
                    console.log(error);
                })
        },
        getGastronomyUtilizationData: function() {
            var gastronomyId = this.$route.params.id;
            axios.get('http://127.0.0.1:8081/skiapp/gastronomies/'+gastronomyId+'/utilization')
            .then( (response) => {
                var gastronomyUtilizationData = response.data;
                this.createGastronomyUtilizationChart(gastronomyUtilizationData);
            })
            .catch ( (error) => {
                console.log(error);
            })
        },
        createGastronomyUtilizationChart: function(gastronomyUtilizationData) {
            new Chart(document.getElementById("utilization-chart"), {
                type: 'bar',
                data: {
                    labels: gastronomyUtilizationData.utilizationSummaryLabels, 
                    datasets: [
                        {
                            label: "Auslastung",
                            backgroundColor: "#787878",
                            data: gastronomyUtilizationData.utilizationSummaryPerHour
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
        },
        showGastronomyTypeImage: function (gastronomyType) {
            if (gastronomyType.id == 1) {
                return '../img/bar.png';
            } else if (gastronomyType.id == 2 || gastronomyType.id == 3) {
                return '../img/restaurant.png';
            } 
        },
        getDynamicRouteToReservation: function (gastronomyId) {
            return "/gastronomy/"+ gastronomyId +"/reservation/";

        }
    }
})

// ################# GOSTRONOMIES PAGE COMPONENTS #####################################################################################

Vue.component('gastronomy-item-component', {
    props: ['gastronomy'],
    data: function () {
        return {
            data: null
        }
    },
    template: `
        <router-link :to="getDynamicRouteToGastronomyDetail(gastronomy.id)">
            <div class="d-flex bd-highlight align-items-center">
                <div class="p-2 bd-highlight"><img class="symbols" :src="showGastronomyTypeImage(gastronomy.gastronomyType)"></div>
                <div class="p-2 liftTitle align-items-center">
                    <p>{{gastronomy.name}}</p>
                </div>
                <div class="d-flex ml-auto p-2 align-itemes-center">
                    <div class="d-flex align-items-center">
                        <img class="pictogramS" src="../img/flag.png">
                        <p class="pBlack">50m</p>
                    </div>
                </div>
            </div>
        </router-link>   
    `
    ,
    methods: {
        getDynamicRouteToGastronomyDetail(gastronomyId){
            return "/gastronomy/" + gastronomyId;
        },
        showGastronomyTypeImage: function (gastronomyType) {
            if (gastronomyType.id == 1) {
                return '../img/bar.png';
            } else if (gastronomyType.id == 2 || gastronomyType.id == 3) {
                return '../img/restaurant.png';
            } 
        }
    }
})

var appGastronomies = Vue.component('app-gastronomies', {
    data: function () {
        return {
            gastronomies: null
        }
    },
    template: `
        <ul class="list-group list-group-flush">
            <li v-for="gastronomy in gastronomies" class="list-group-item">
                <gastronomy-item-component :gastronomy="gastronomy"></gastronomy-item-component>
            </li>
        </ul> 

    `,
    mounted: function () {
        this.getAllGastronomies();
    },
    methods: {
        getAllGastronomies: function () {
            axios.get('http://127.0.0.1:8081/skiapp/gastronomies')
                .then( (response) => {
                    this.gastronomies = response.data;
                })
                .catch ( (error) => {
                    console.log(error);
                })
        }        
    }
})

// ################# LIFT DETAIL COMPONENTS ##################################################################################### 
var appLift = Vue.component('app-lift', {
    data: function () {
        return {
            lift: null,
            hasBlueNeighbourSlope: false,
            hasRedNeighbourSlope: false,
            hasBlackNeighbourSlope: false,
            neighbourGastronomies: null,
            neighbourGastronomiesExist: false
        }
    },
    template: `
        <div id="content">
            <div class="d-flex flex-column bg-white contentWrapper">
                <div id="waitTimeBanner"></div>
                
                <div class="d-flex w-100 align-items-center my-2">
                <router-link to="/lifts"><button class="back-button"></button></router-Link>
                    <img class="pictogramM mx-1" :src="showLiftTypeImage(lift.liftType)">
                    <h1>{{lift.name}}</h1>
                </div>
                <div class="container">
                    
                </div>
                <div class="d-flex align-items-start flex-column pl-4">
                    <p><span class="font-weight-bold">Wartezeit: </span>{{lift.wait}} min</p>
                    
                    <p class="d-flex align-items-center font-weight-bold">
                        Angrenzende Pisten: 
                        <span class="circle blue" v-if="this.hasBlueNeighbourSlope"></span>
                        <span class="circle red"  v-if="hasRedNeighbourSlope"></span>
                        <span class="circle black" v-if="hasBlackNeighbourSlope"></span></p>
                    <p class="font-weight-bold">Gastronomie in der Nähe:</p>
                    
                    <p v-if="!neighbourGastronomiesExist">Keine Gastronomien in der nähe.</p>
                    <ul v-if="neighbourGastronomiesExist" class="list-group list-group-flush">
                        <li v-for="gastronomy in neighbourGastronomies" class="list-group-item">
                            <router-link :to="getDynamicRouteToGastronomy(gastronomy.id)">{{gastronomy.name}}</router-link>
                        </li>
                     </ul> 
                
                </div>
                
                <div class="d-flex flex-column">
                    <canvas id="utilization-chart" width="550" height="270"></canvas>
                </div>

                <img class="w-100" height="250" src="../img/LiftsAndSlopes.png">

            </div>
        </div> 
    `,
    mounted: function () {
        this.getLiftDetails();
        this.getNeigbourSlopes();
        this.getNeighbourGastronomies();
        this.getLiftUtilizationData();
    },
    methods: {
        getLiftUtilizationData: function() {
            var liftId = this.$route.params.id;
            axios.get('http://127.0.0.1:8081/skiapp/lifts/'+liftId+'/utilization')
            .then( (response) => {
                var liftUtilizationData = response.data;
                this.createLiftUtilizationChart(liftUtilizationData);
            })
            .catch ( (error) => {
                console.log(error);
            })
        },
        getNeigbourSlopes: function() {
            var liftId = this.$route.params.id;
            axios.get('http://127.0.0.1:8081/skiapp/lifts/'+liftId+'/neighbourSlopes')
            .then( (response) => {
                if (response.status == '200'){
                    var neighbourSlopes = response.data;
                    for (var i = 0; i < neighbourSlopes.length; i++) {
                        if (neighbourSlopes[i].color.color == "blue") {
                            this.hasBlueNeighbourSlope = true;
                        } else if (neighbourSlopes[i].color.color == "red") {
                            this.hasRedNeighbourSlope = true;

                        } else if (neighbourSlopes[i].color.color == "black") {
                            this.hasBlackNeighbourSlope = true;
                        }
                    }
                }
            })
            .catch ( (error) => {
                console.log(error);
            })
        },
        getNeighbourGastronomies: function() {
            var liftId = this.$route.params.id;
            axios.get('http://127.0.0.1:8081/skiapp/lifts/'+liftId+'/neighbourGastronomies')
            .then( (response) => {
                if (response.status == '200'){
                    this.neighbourGastronomies = response.data;
                    this.neighbourGastronomiesExist = true;
                }
            })
            .catch ( (error) => {
                console.log(error);
            })
        },
        getLiftDetails: function() {
            var liftId = this.$route.params.id;
            axios.get('http://127.0.0.1:8081/skiapp/lifts/'+liftId)
                .then( (response) => {
                    this.lift = response.data;
                })
                .catch ( (error) => {
                    console.log(error);
                })
        },
        showLiftTypeImage: function (liftType) {
            if (liftType.id == 1) {
                return '../img/chairLift4.png';
            } else if (liftType.id == 2) {
                return '../img/chairLift6.png';
            } else if (liftType.id == 3) {
                return '../img/gondolaLift.png';
            } else if (liftType.id == 4) {
                return '../img/dragLift.png';
            } else if (liftType.id == 5) {
                return '../img/babyLift.png';
            }
        },
        getDynamicRouteToGastronomy: function (gastronomyId) {
            return '/gastronomy/' + gastronomyId;
        },
        createLiftUtilizationChart: function(liftUtilizationData) {
            new Chart(document.getElementById("utilization-chart"), {
                type: 'bar',
                data: {
                    labels: liftUtilizationData.utilizationSummaryLabels, 
                    datasets: [
                        {
                            label: "Auslastung",
                            backgroundColor: "#787878",
                            data: liftUtilizationData.utilizationSummaryPerHour
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
        }
    }
})

// ################# LIFTS PAGE COMPONENTS #####################################################################################
Vue.component('lift-status-component', {
    props: ['wait', 'liftstatus'],
    data: function () {
        return {
            greenBackGroundColor: false,
            yellowBackGroundColor: false,
            redBackGroundColor: false
        }
    },
    template: `
        <div class="d-flex justify-content-between align-items-center" v-if="!isLiftOpen(liftstatus)">
            <p><s>Closed</s></p>
        </div>
        <div class="d-flex justify-content-between align-items-center wait-time-background green" v-else-if="greenBackGroundColor && isLiftOpen(liftstatus)">
            <img class="pictogramS" src="../img/time.png">
            <p class="pBlack">{{this.wait}}</p>
        </div>
        <div class="d-flex justify-content-between align-items-center wait-time-background yellow" v-else-if="yellowBackGroundColor && isLiftOpen(liftstatus)">
            <img class="pictogramS" src="../img/time.png">
            <p class="pBlack">{{this.wait}}</p>
        </div>
        <div class="d-flex justify-content-between align-items-center wait-time-background red" v-else-if="redBackGroundColor && isLiftOpen(liftstatus)">
            <img class="pictogramS" src="../img/time.png">
            <p class="pBlack">{{this.wait}}</p>
        </div>
    `
    ,
    mounted: function () {
        if (this.wait <= 10) {
            this.greenBackGroundColor = true;
        } else if (this.wait >= 10 && this.wait <= 20) {
            this.yellowBackGroundColor = true;
        } else if (this.wait >= 20) {
            this.redBackGroundColor = true;
        }
    },
    methods: {
        isLiftOpen: function (liftStatus) {
            if (liftStatus.status == ('Open')) {
                return true;
            } else {
                return false;
            }
        }
    }
})

Vue.component('lift-item-component', {
    props: ['lift'],
    data: function () {
        return {
            data: null
        }
    },
    template: `
        <router-link :to="getDynamicRouteToLiftDetail(lift.id)">
            <div class="d-flex bd-highlight align-items-center">
                <div class="p-2 bd-highlight"><img :src="showLiftTypeImage(lift.liftType)"></div>
                <div class="p-2 liftTitle align-items-center">
                    <p>{{lift.name}}</p>
                </div>
                <div class="ml-auto p-2">
                    <div class="d-flex justify-content-between align-items-center">
                        <img class="pictogramS" src="../img/flag.png">
                        <p class="pBlack">50m</p>
                    </div>
                    <lift-status-component :liftstatus="lift.status" :wait="lift.wait"></lift-status-component>
                </div>
            </div>
        </router-link>   
    `
    ,
    methods: {
        getDynamicRouteToLiftDetail(liftId){
            return "/lift/" + liftId;
        },
        showLiftTypeImage: function (liftType) {
            if (liftType.id == 1) {
                return '../img/chairLift4.png';
            } else if (liftType.id == 2) {
                return '../img/chairLift6.png';
            } else if (liftType.id == 3) {
                return '../img/gondolaLift.png';
            } else if (liftType.id == 4) {
                return '../img/dragLift.png';
            } else if (liftType.id == 5) {
                return '../img/babyLift.png';
            }
        }
    }
})

var appLifts = Vue.component('app-lifts', {
    data: function () {
        return {
            lifts: null
        }
    },
    template: `
        <ul class="list-group list-group-flush">
            <li v-for="lift in lifts" class="list-group-item">
                <lift-item-component :lift="lift"></lift-item-component>
            </li>
        </ul>  
    `,
    mounted: function () {
        this.getAllLifts();
    },
    methods: {
        getAllLifts: function () {
            axios.get('http://127.0.0.1:8081/skiapp/lifts')
                .then( (response) => {
                    this.lifts = response.data;
                })
                .catch ( (error) => {
                    console.log(error);
                })
        }        
    }
})

// ################# HOME PAGE COMPONENT #####################################################################################
var appHome = Vue.component('app-home', {
    template: `
        <div>
            <div>
                <img class="d-block w-100" src="img/LiftsAndSlopes.png" alt="Map with lifts and slopes">
                <div class="d-flex justify-content-around border-bottom align-items-center">
                    <p class="d-flex align-items-center"><span class="circle green"></span>Offen</p>
                    <p class="d-flex align-items-center"><span class="circle yellow"></span>In Vorbereitung</p>
                    <p class="d-flex align-items-center"><span class="circle red"></span>Geschlossen</p>
                </div>
            </div>
            
            <div class="container">
                <div class="d-flex justify-content-between">
                    <p class="text-dark" id="forecastTitle">Forecast</p>
                    <img id="regionLogo" class="" src="img/region-logo.png" alt="Regionales Logo">
                </div>
                
                <div class="row weatherrow">
                    <div class="col weathercol" id="weather0"></div>
                    <div class="col weathercol" id="weather1"></div>
                    <div class="col weathercol" id="weather2"></div>
                    <div class="col weathercol" id="weather3"></div>
                    <div class="col weathercol" id="weather4"></div>
                </div>
                <div id="day-row" class="row weatherrow">
                    <div class="col weathercol"><p class="daylabel" id="dayLabel0"></p></div>
                    <div class="col weathercol"><p class="daylabel" id="dayLabel1"></p></div>
                    <div class="col weathercol"><p class="daylabel" id="dayLabel2"></p></div>
                    <div class="col weathercol"><p class="daylabel" id="dayLabel3"></p></div>
                    <div class="col weathercol"><p class="daylabel" id="dayLabel4"></p></div>
                </div>
                <div class="row chart-row">
                    <canvas id="bar-chart" width="800" height="300"></canvas>
                </div>
                <div class="row">
                    <p class="text-dark mx-auto font-weight-bold">Auslastung</p>
                </div>
            </div>
        </div>
    `,
    mounted: function () {
        this.getForecastData();
        this.createForecastChart();
    },
    methods: {
        getForecastData: function() {
            axios.get('http://127.0.0.1:8081/skiapp/forecastChartData')
                .then( (response) => {
                    var testData =  response.data;
                    this.createForecastChart(testData)
                })
                .catch ( (error) => {
                    console.log(error);
                })
        },
        createForecastChart: function (forecastData) {
            //{"dayLabel":"SO","dayForecast":2478,"dayForecastPercentage":40,"weatherType":{"id":3,"weatherType":"Cloudy"}}          
            var forecastPercentageLabel = [];
            var forecastDayData = [];
            for (i = 0; i < forecastData.length; i++) {
                // generate weather type icons for each forecast-day
                var weatherType = "" + forecastData[i].weatherType.weatherType;
                var img = document.createElement("img");
                img.src = "img/" + weatherType.replace(" ", "") +".png";
                img.width = 45;
                img.height = 45;
                document.getElementById("weather" + i).appendChild(img);
                // add label for each forecast-day
                var dayLabel = document.getElementById("dayLabel" + i);
                var text = document.createTextNode(forecastData[i].dayLabel);
                dayLabel.appendChild(text);
                // creates array with percentage labels and chart data
                forecastPercentageLabel.push(forecastData[i].dayForecastPercentage);
                forecastDayData.push(forecastData[i].dayForecast);
            }
            // generate bar-chart
            new Chart(document.getElementById("bar-chart"), {
                type: 'bar',
                data: {
                    labels: forecastPercentageLabel,
                    datasets: [
                        {
                            label: "",
                            backgroundColor: "#787878",
                            data: forecastDayData
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
        }
    }
})

// ################# VUE ROUTER #####################################################################################
const router = new VueRouter({
    routes: [
        { path: '/', component: appHome },
        { path: '/lifts', component: appLifts },
        { path: '/lift/:id', props: true, component: appLift },
        { path: '/gastronomies', component: appGastronomies },
        { path: '/gastronomy/:id', props: true, component: appGastronomy},
        { path: '/gastronomy/:id/reservation/', props: true, component: appReservation}
    ]
})

// ################# VUE INSTANCE #####################################################################################
var app = new Vue ({
    router: router,
    el: '#app',
    data: {
        data: null //TODO: data and methods to store login session -> login for reservation
    },
    mounted: function () {
    },
    methods: {    
    }
})