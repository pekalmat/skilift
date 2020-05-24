// lift status component
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
        <a href="skiliftsDetail.html" class="">
            <div class="d-flex bd-highlight">
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
        </a>    
    `
    ,
    methods: {
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

// vue instance
var app = new Vue ({
    el: '#skilifts',
    data: {
        lifts: null
    },
    mounted: function () {
        this.getAllLifts();
    },
    methods: {
        getAllLifts: function () {
            axios.get('http://127.0.0.1:8081/skiapp/lifts')
                .then( (response) => {
                    this.lifts = response.data;
                    console.log(response.data);
                })
                .catch ( (error) => {
                    console.log(error);
                })
        }        
    }
})