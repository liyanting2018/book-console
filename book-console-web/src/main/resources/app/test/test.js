(function () {
    var app = angular.module(app_name);

    app.config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider.state('test', {
            parent: 'home',
            url: '/test',
            templateUrl: 'test/index.html'
        });

        $stateProvider.state('test.test', {
            url: '/test',
            templateUrl: 'test/index.html',
            controller: 'testCtrl'
        });

    });
    app.controller('testCtrl', function ($scope, $state, $uibModal, Rest, modal) {
        $scope.message={};
        Rest.all('access/getAllBusiness').post().then(function (res) {
            $scope.businesses = res;
        });
        $scope.aliases = [{}];
        $scope.addAlias = function () {
            $scope.aliases.push({});
            disabledDelete();
        };
        $scope.deleteAlias = function (item) {
            $scope.aliases.splice($scope.aliases.indexOf(item), 1);
            disabledDelete();
        };
        disabledDelete();
        function disabledDelete() {
            if ($scope.aliases.length > 1) {
                $scope.disabledDelete = false;
            } else {
                $scope.disabledDelete = true;
            }
        }

        $scope.rules = [];

        Rest.all('access/getAllEvents').post().then(function (res) {
            $scope.events = res;
        });

        Rest.all('access/getAllPunishs').post().then(function (res) {
            $scope.punishs = res;
        });

        $scope.refreshRules = function (name) {
            Rest.all('access/getRulesByName').getList({page: 1, size: 10, name: name}).then(function (response) {
                $scope.rules = response;
            });
        };
        $scope.sendMsg = function () {
            var k = [];
            angular.forEach($scope.aliases, function (data) {
                k.push({key: data.key, value: data.value});
            });
            $scope.message.actionParams = [];
            $scope.message.actionParams = k;
            $scope.loading = Rest.all('test/sendMsg').post($scope.message).then(function (re) {
                $scope.parameters=re.parameters;
                $scope.result=re.result;
            });
        };

        $scope.mock = function () {
            var k = [];
            angular.forEach($scope.aliases, function (data) {
                k.push({key: data.key, value: data.value});
            });
            $scope.message.actionParams = [];
            $scope.message.actionParams = k;
            $scope.loading = Rest.all('test/mock').post($scope.message).then(function (re) {
                $scope.parameters=re.parameters;
                $scope.result=re.result;
            });
        };

    });

})();