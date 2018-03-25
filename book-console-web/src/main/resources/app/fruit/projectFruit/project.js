(function(){
    var app = angular.module(app_name);

    app.controller('projectFruitCtrl', function ($scope, $state, $uibModal, Rest, modal) {
        $scope.loading = true;
        $scope.page = 1;
        $scope.size = 10;
        
        go($scope.page);
        function go(page) {
            $scope.page = page;
            $scope.loading = true;
            Rest.all('/project/fruit/search').getList({page: $scope.page, name: $scope.projectNm}).then(function (response) {
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
                templateUrl: 'fruit/projectFruit/view/insert.html',
                controller: function (rest, $scope,modal, $uibModalInstance) {
                		Rest.all('/project/fruit/all').post().then(function (res) {
                			$scope.categorys = res;
                    });
                	
                		$scope.detail={};
                        $scope.formats = 'yyyy-MM-dd';

                        $scope.altInputFormats = ['yyyy-M!-d! H!:mm', 'yyyy-M!-d!'];

                        $scope.detail.startTime = new Date();
                        $scope.detail.startTime.setDate($scope.detail.startTime.getDate() + 90);

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };

                    $scope.confirm = function() {
                        $scope.temp = angular.copy($scope.detail);
                        delete $scope.temp["fruitProject"];

                       // $scope.temp.fruitCategoryId = $scope.detail.fruitCategoryd;
                        $scope.loading = Rest.all('/project/fruit/add').post($scope.temp).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };
        $scope.edit = function(detail) {
            $uibModal.open({
                templateUrl: 'fruit/projectFruit/view/update.html',
                controller: function (rest, $scope,modal, $uibModalInstance) {
                	$scope.detail = angular.copy(detail);
                	Rest.all('/project/fruit/all').post().then(function (res) {
                		$scope.categorys = res;
                		 angular.forEach($scope.categorys, function (data) {
                             if (data.name == $scope.detail.fruitCategoryId) {
                             	$scope.detail.fruitCategoryId = data;
                             }
                         });
                    });
                    
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    
                    $scope.confirm = function() {
                    	$scope.temp =  angular.copy($scope.detail);
                        //delete $scope.temp["fruitCategoryId"];
                        //$scope.temp.fruitCategoryId = $scope.detail.fruitCategory.id;
                        $scope.loading = Rest.one('/project/fruit//update', $scope.temp.id).doPUT($scope.temp).then(function (response) {
                            go(1);
                            $uibModalInstance.close();
                        })
                    };
                }
            });
        };

        $scope.delete = function(detail) {
        	modal.confirm('确定删除【' + detail.projectNm + '】?', function(){
        		$scope.loading = Rest.one('/project/fruit/delete', detail.id).doPUT(detail).then(function (response) {
                    go(1);
                })
                });
            };

       $scope.upload= function(detail) {
            	modal.confirm('确定提交【' + detail.projectNm + '】?', function(){
            		$scope.loading = Rest.one('/project/fruit/upload', detail.id).doPUT(detail).then(function (response) {
                        go(1);
                    })
                    });
                };     

    });
})();