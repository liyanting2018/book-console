(function () {
    var app = angular.module(app_name);

    app.config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when('/baseDataConfig', '/baseDataConfig');
        $stateProvider.state('baseDataConfig', {
            parent: 'home',
            url: '/baseDataConfig',
            templateUrl: 'baseDataConfig/index.html'
        });


        $stateProvider.state('baseDataConfig.fruit_category', {
            url: '/fruitcategory',
            templateUrl: 'baseDataConfig/fruit_category/view/index.html',
            controller: 'fruitCategoryCtrl'
        });

        $stateProvider.state('baseDataConfig.event', {
            url: '/event',
            templateUrl: 'baseDataConfig/event/view/index.html',
            controller: 'eventCtrl'
        });



    });

})();