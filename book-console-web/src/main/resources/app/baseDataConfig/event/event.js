(function () {
    var app = angular.module(app_name);

    app.controller('eventCtrl', function ($scope, $state, $uibModal, Rest) {
        $scope.loading = true;
        $scope.page = 1;
        go($scope.page);
        function go(page) {
            $scope.page = page;
            $scope.loading = true;
            Rest.all('event/search').getList({page: $scope.page, name: $scope.name}).then(function (response) {
                $scope.events = response;
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
        $scope.add = function () {
            $uibModal.open({
                templateUrl: 'baseDataConfig/event/view/add.html',
                controller: function (rest, $scope, modal, $uibModalInstance) {
                    $scope.event = {
                        name: ''

                    };
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

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.confirm = function () {
                        var k = [];
                        angular.forEach($scope.aliases, function (data) {
                            k.push(data.name);
                        });
                        k.push($scope.event.code);
                        var d = k.join(",");
                        $scope.event.aliases = d;
                        $scope.loading = Rest.all('event/add').post($scope.event).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };
        $scope.edit = function (event) {
            $uibModal.open({
                templateUrl: 'baseDataConfig/event/view/edit.html',
                controller: function (rest, $scope, modal, $uibModalInstance) {
                    $scope.event = angular.copy(event);
                    $scope.aliases = [];
                    angular.forEach($scope.event.aliasList, function (data) {
                        $scope.aliases.push({name: data});
                    });

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

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.confirm = function () {
                        var k = [];
                        angular.forEach($scope.aliases, function (data) {
                            if (data != $scope.event.code) {
                                k.push(data.name);
                            }
                        });
                        k.push($scope.event.code);
                        var d = k.join(",");
                        $scope.event.aliases = d;
                        $scope.loading = Rest.one('event/update', $scope.event.id).doPUT($scope.event).then(function (response) {
                            go(1);
                            $uibModalInstance.close();
                        })
                    };
                }
            });
        };

        $scope.delete = function (project) {
            $uibModal.open({
                templateUrl: 'baseDataConfig/event/view/delete.html',
                controller: function (rest, $scope, modal, $uibModalInstance) {
                    $scope.project = project;
                    $scope.project.projectName = cloudMap[project.id];

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.confirm = function () {
                        rest.baseDataConfig.projectindexconfig.delete({
                            projectId: $scope.project.id
                        }, function () {
                            go(1);
                        });
                        $uibModalInstance.close();
                    };
                }
            });
        };


    });
})();