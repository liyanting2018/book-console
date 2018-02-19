(function(){
    var app = angular.module(app_name);

    app.controller('punishCodeCtrl', function ($scope, $state, $uibModal, Rest, modal) {
        $scope.loading = true;
        $scope.page = 1;
        $scope.size = 10;
        
        go($scope.page);
        function go(page) {
            $scope.page = page;
            $scope.loading = true;
            Rest.all('punishCode/all').getList({page: $scope.page, name: $scope.punishName}).then(function (response) {
                $scope.punishCodes = response;
                $scope.total = response.pageInfo.total;
                $scope.loading = false;
            });
        }
        
        $scope.search = function() {
            go(1);
        };
        
        function reload() {
        	go($scope.page);
        }
        
        $scope.pageChanged = function() {
            go($scope.page);
        };
        
        
        $scope.add = function(){
            $uibModal.open({
                templateUrl: 'punishDateConfig/punishCode/view/add.html',
                controller: function (rest, $scope,modal, $uibModalInstance) {
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    
                    $scope.confirm = function() {
                        $scope.loading = Rest.all('punishCode/addPunishCode').post($scope.punishCode).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };
        $scope.edit = function(punishCode) {
            $uibModal.open({
                templateUrl: 'punishDateConfig/punishCode/view/edit.html',
                controller: function (rest, $scope,modal, $uibModalInstance) {
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    
                    Rest.all('punishCode/getPunishCodeById').post(punishCode).then(function (res) {
                		$scope.punishCode = res;
                    });
                    
                    $scope.confirm = function() {
                        $scope.loading = Rest.all('punishCode/editPunishCode').post($scope.punishCode).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };

        $scope.delete = function(punish) {
        	modal.confirm('确定删除【' + punish.punishGroupname + '】?', function(){
        		Rest.all('punish/delPunish').getList({id:punish.id}).then(function () {
                    go(1);
                });
                });
            };


    });
})();