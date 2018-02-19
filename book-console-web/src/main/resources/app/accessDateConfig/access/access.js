(function () {
    var app = angular.module(app_name);

    app.controller('accessCtrl', function ($scope, $state, $uibModal, Rest, modal) {
        $scope.loading = true;
        $scope.page = 1;
        $scope.size = 10;

        go($scope.page);

        Rest.all('access/getAllBusiness').post().then(function (res) {
            $scope.businesses = res;
        });

        Rest.all('access/getAllEvents').post().then(function (res) {
            $scope.events = res;
        });

        function go(page) {
            $scope.page = page;
            $scope.loading = true;
            var businessCode = '';
            if ($scope.selectedBusiness != null && $scope.selectedBusiness != undefined) {
                businessCode = $scope.selectedBusiness.code;
            }

            var eventCode = '';
            if ($scope.selectedEvent != null && $scope.selectedEvent != undefined) {
                eventCode = $scope.selectedEvent.code;
            }

            Rest.all('access/all').getList({
                page: $scope.page, businessCode: businessCode,
                eventCode: eventCode
            }).then(function (response) {
                $scope.accesses = response;
                $scope.total = response.pageInfo.total;
                $scope.loading = false;
            });
        }

        $scope.search = function () {
            go(1);
        };

        function reload() {
            go($scope.page);
        }

        $scope.pageChanged = function () {
            go($scope.page);
        };


        $scope.add = function () {
            $uibModal.open({
                templateUrl: 'accessDateConfig/access/view/add.html',
                controller: function (rest, $scope, modal, $uibModalInstance) {
                    Rest.all('access/getAllBusiness').post().then(function (res) {
                        $scope.businesses = res;
                    });
                    $scope.access = {};
                    $scope.rules = [];

                    Rest.all('access/getAllEvents').post().then(function (res) {
                        $scope.events = res;
                    });

                    Rest.all('access/getAllPunishs').post().then(function (res) {
                        $scope.punishs = res;
                    });
                    /*$scope.access.rules = [];*/
                    $scope.onRuleRemove = function () {
                        if ($scope.access.rules) {
                            if ($scope.access.rules.length < 1) {
                                $scope.access.rules = null;
                            }
                        }
                    };

                    $scope.refreshRules = function (name) {
                        Rest.all('access/getRulesByName').getList({
                            page: 1,
                            size: 10,
                            name: name
                        }).then(function (response) {
                            $scope.rules = response;
                        });
                    };

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };

                    $scope.confirm = function () {
                        $scope.loading = Rest.all('access/addAccess').post($scope.access).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };
        $scope.edit = function (access) {
            $uibModal.open({
                templateUrl: 'accessDateConfig/access/view/edit.html',
                controller: function (rest, $scope, modal, $uibModalInstance) {
                    $scope.access = {};
                    Rest.all('access/getAllBusiness').post().then(function (res) {
                        $scope.businesses = res;
                    });

                    Rest.all('access/getAllEvents').post().then(function (res) {
                        $scope.events = res;
                    });

                    Rest.all('access/getAllPunishs').post().then(function (res) {
                        $scope.punishs = res;
                    });

                    $scope.refreshRules = function (name) {
                        Rest.all('access/getRulesByName?name=' + name).post().then(function (response) {
                            $scope.allRules = response;
                        });
                    };

                    Rest.all('access/getAccessById').post(access).then(function (res) {
                        $scope.access = res;

                    });

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.access.rules = [];
                    $scope.onRuleRemove = function () {
                        if ($scope.access.rules) {
                            if ($scope.access.rules.length < 1) {
                                $scope.access.rules = null;
                            }
                        }
                    };

                    $scope.confirm = function () {
                        $scope.loading = Rest.all('access/editAccess').post($scope.access).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };

        $scope.delete = function (punish) {
            modal.confirm('确定删除【' + punish.punishGroupname + '】?', function () {
                Rest.all('punish/delPunish').getList({id: punish.id}).then(function () {
                    go(1);
                });
            });
        };


    });
})();