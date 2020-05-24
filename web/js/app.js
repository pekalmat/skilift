import appSkiliftKomponent from 'Skilifts.js';


var appLifts = Vue.component('app-lifts', {
    data: function () {
        return {
            lifts: null
        }
    },
    template: `
        <ul id="skilifts" class="list-group list-group-flush">
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
                    console.log(response.data);
                })
                .catch ( (error) => {
                    console.log(error);
                })
        }        
    }
})

const router = new VueRouter({
    reoutes: [
        { path: '/lifts', component: appLifts }
    ]
})

// vue instance
var app = new Vue ({
    router,
    el: '#app',
    data: {
        data: null
    },
    mounted: function () {
    },
    methods: {    
    }
})