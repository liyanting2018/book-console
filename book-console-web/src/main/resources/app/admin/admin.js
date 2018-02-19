(function () {
    var app = angular.module(app_name);

    app.config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.when('/admin', '/admin/user');
        $stateProvider.state('admin', {
            //abstract: true,
            parent: 'home',
            url: '/admin',
            templateUrl: 'admin/view/index.html'
        });

        $stateProvider.state('admin.user', {
            url: '/user',
            templateUrl: 'admin/view/user.html',
            controller: 'AdminCtrl'
        });

    });
    app.controller('AdminCtrl', function ($scope, $state, $uibModal, Rest) {
        $scope.loading = true;
        $scope.username = '';

        $scope.page = 1;
        $scope.size = 10;

        go($scope.page);

        function go(page) {
            $scope.loading = true;
            $scope.page = page;
            Rest.all('user/all').getList({page: $scope.page, username: $scope.username}).then(function (response) {
                $scope.users = response;
                $scope.total = response.pageInfo.total;
                $scope.loading = false;
            });

        }

        $scope.search = function () {
            go(1);
        };

        $scope.pageChanged = function () {
            go($scope.page);
        };

        $scope.updateRole = function (user) {
            $uibModal.open({
                templateUrl: 'admin/view/role.edit.html',
                controller: function (rest, $scope, $uibModalInstance, USER_ROLES) {
                    $scope.user = user;
                    $scope.roles = {};
                    for (var k in USER_ROLES) {
                        if ((USER_ROLES[k] & user.role) == USER_ROLES[k]) {
                            $scope.roles[k] = USER_ROLES[k];
                        }
                    }
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };


                    $scope.confirm = function () {
                        var role = 0;
                        for (var k in $scope.roles) {
                            role |= $scope.roles[k];
                        }
                        var temp = {
                            username: user.username,
                            role: role
                        }
                        Rest.all('user/updateRole').post(temp).then(function (response) {
                            user.role = role;
                            go(1);
                            $uibModalInstance.close();
                        })

                    };

                }
            });
        }


    });

})();