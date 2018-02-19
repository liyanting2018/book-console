(function () {
    var app = angular.module(app_name);
    app.config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when('/attribute', '/attribute/attribute');
        $stateProvider.state('attribute', {
            parent: 'home',
            url: '/attribute',
            templateUrl: 'attribute/index.html'
        });
        $stateProvider.state('attribute.localAttribute', {
            url: '/localAttribute',
            templateUrl: 'attribute/attribute/view/index.html',
            controller: 'localAttributeCtrl'
        });
        $stateProvider.state('attribute.remoteAttribute', {
            url: '/remoteAttribute',
            templateUrl: 'attribute/remoteAttribute/view/index.html',
            controller: 'remoteAttributeCtrl'
        });

    });
})();