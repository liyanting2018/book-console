(function () {
    var app = angular.module(app_name);

    app.config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when('/accessDateConfig', '/accessDateConfig');
        $stateProvider.state('accessDateConfig', {
            parent: 'home',
            url: '/accessDateConfig',
            templateUrl: 'accessDateConfig/index.html'
        });


        $stateProvider.state('accessDateConfig.access', {
            url: '/access',
            templateUrl: 'accessDateConfig/access/view/index.html',
            controller: 'accessCtrl'
        });
        
    });

})();