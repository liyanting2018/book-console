(function () {
    var app = angular.module(app_name);

    app.config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when('/punishDateConfig', '/punishDateConfig');
        $stateProvider.state('punishDateConfig', {
            parent: 'home',
            url: '/punishDateConfig',
            templateUrl: 'punishDateConfig/index.html'
        });


        $stateProvider.state('punishDateConfig.punish', {
            url: '/punish',
            templateUrl: 'punishDateConfig/punish/view/index.html',
            controller: 'fruitDetailCtrl'
        });

        $stateProvider.state('punishDateConfig.punishCode', {
            url: '/punishCode',
            templateUrl: 'punishDateConfig/punishCode/view/index.html',
            controller: 'punishCodeCtrl'
        });



    });

})();