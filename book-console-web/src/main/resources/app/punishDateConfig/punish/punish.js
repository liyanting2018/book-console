(function(){
    var app = angular.module(app_name);

    app.controller('fruitDetailCtrl', function ($scope, $state, $uibModal, Rest, modal) {
        $scope.loading = true;
        $scope.page = 1;
        $scope.size = 10;
        
        go($scope.page);
        function go(page) {
            $scope.page = page;
            $scope.loading = true;
            Rest.all('/fruit/detail/search').getList({page: $scope.page, name: $scope.punishName}).then(function (response) {
                $scope.details = response;
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
                templateUrl: 'punishDateConfig/punish/view/insert.html',
                controller: function (rest, $scope,modal, $uibModalInstance) {
                	Rest.all('/fruit/category/all').post().then(function (res) {
                		$scope.categorys = res;
                    });

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };

                    $scope.confirm = function() {
                        $scope.temp = angular.copy($scope.detail);
                        delete $scope.temp["fruitCategory"];

                       /* if($scope.punish.slightAccuracy&&temp==''){
                            modal.alert("请选择惩罚等级(低)");
                            return;
                        }*/
                        $scope.temp.fruitCategoryId = $scope.detail.fruitCategory.id;
                        $scope.loading = Rest.all('/fruit/detail/add').post($scope.temp).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };
        $scope.edit = function(detail) {
            $uibModal.open({
                templateUrl: 'punishDateConfig/punish/view/update.html',
                controller: function (rest, $scope,modal, $uibModalInstance) {
                	$scope.detail = angular.copy(detail);
                	Rest.all('/fruit/category/all').post().then(function (res) {
                		$scope.categorys = res;
                		 angular.forEach($scope.categorys, function (data) {
                             if (data.name == $scope.detail.fruitLevel) {
                             	$scope.detail.fruitCategory = data;
                             }
                         });
                    });
                    
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    
                    $scope.confirm = function() {
                    	$scope.temp =  angular.copy($scope.detail);
                        delete $scope.temp["fruitCategory"];
                        $scope.temp.fruitCategoryId = $scope.detail.fruitCategory.id;
                        $scope.loading = Rest.one('/fruit/detail/update', $scope.temp.id).doPUT($scope.temp).then(function (response) {
                            go(1);
                            $uibModalInstance.close();
                        })
                    };
                }
            });
        };

        $scope.delete = function(detail) {
        	modal.confirm('确定删除【' + detail.fruitNm + '】?', function(){
        		$scope.loading = Rest.one('/fruit/detail/delete', detail.id).doPUT(detail).then(function (response) {
                    go(1);
                })
                });
            };

       $scope.upload= function(detail) {
            	modal.confirm('确定提交【' + detail.fruitNm + '】?', function(){
            		$scope.loading = Rest.one('/fruit/detail/upload', detail.id).doPUT(detail).then(function (response) {
                        go(1);
                    })
                    });
                };     

    });
})();