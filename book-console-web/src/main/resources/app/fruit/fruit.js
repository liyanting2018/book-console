(function () {
    var app = angular.module(app_name);

    app.config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when('/fruit', '/fruit');
        $stateProvider.state('fruit', {
            parent: 'home',
            url: '/fruit',
            templateUrl: 'fruit/index.html'
        });


        $stateProvider.state('fruit.projectFruit', {
            url: '/projectFruit',
            templateUrl: 'fruit/projectFruit/view/index.html',
            controller: 'projectFruitCtrl'
        });

        $stateProvider.state('fruit.paperFruit', {
            url: '/paperFruit',
            templateUrl: 'fruit/paperFruit/view/index.html',
            controller: 'paperFruitCtrl'
        }); 

        $stateProvider.state('fruit.bookFruit', {
            url: '/bookFruit',
            templateUrl: 'fruit/bookFruit/view/index.html',
            controller: 'bookFruitCtrl'
        }); 
        
        $stateProvider.state('fruit.patentFruit', {
            url: '/patentFruit',
            templateUrl: 'fruit/patentFruit/view/index.html',
            controller: 'patentFruitCtrl'
        }); 

    });

})();