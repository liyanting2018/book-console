(function () {
    var app = angular.module(app_name);
    app.controller('localAttributeCtrl', function ($scope, $state, $uibModal, Rest) {
        $scope.loading = true;
        $scope.page = 1;
        go($scope.page);
        function go(page) {
            $scope.page = page;
            $scope.loading = true;
            Rest.all('attribute/search').getList({page: $scope.page, name: $scope.name}).then(function (response) {
                $scope.attributes = response;
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
                templateUrl: 'attribute/attribute/view/add.html',
                controller: function (rest, $scope, modal, $uibModalInstance) {
                    $scope.attribute = {
                        name: ''
                    };
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.confirm = function () {
                        $scope.loading = Rest.all('attribute/add').post($scope.attribute).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };
        $scope.edit = function (attribute) {
            $uibModal.open({
                templateUrl: 'attribute/attribute/view/edit.html',
                controller: function (rest, $scope, modal, $uibModalInstance) {
                    $scope.attribute = angular.copy(attribute);
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.confirm = function () {
                        $scope.loading = Rest.one('attribute/update', $scope.attribute.id).doPUT($scope.attribute).then(function (response) {
                            modal.confirm("操作成功！", function () {
                                go(1);
                                $uibModalInstance.close();
                            });
                        })
                    };
                }
            });
        };

    });
})();