(function(){
    var app = angular.module(app_name);

    app.controller('bookFruitCtrl', function ($scope, $state, $uibModal, Rest, modal) {
        $scope.loading = true;
        $scope.page = 1;
        $scope.size = 10;
        
        go($scope.page);
        function go(page) {
            $scope.page = page;
            $scope.loading = true;
            Rest.all('/book/fruit/search').getList({page: $scope.page, name: $scope.bookNm}).then(function (response) {
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
                templateUrl: 'fruit/bookFruit/view/insert.html',
                controller: function (rest, $scope,modal, $uibModalInstance) {
                		Rest.all('/book/fruit/all').post().then(function (res) {
                			$scope.categorys = res;
                    });
                	
                		$scope.detail={};
                        $scope.formats = 'yyyy-MM';

                        $scope.altInputFormats = ['yyyy-M!-d! H!:mm', 'yyyy-M!-d!'];

                        $scope.detail.startTime = new Date();
                        $scope.detail.startTime.setDate($scope.detail.startTime.getDate() + 90);

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };

                    $scope.confirm = function() {
                        $scope.temp = angular.copy($scope.detail);
                        delete $scope.temp["fruitbook"];

                       // $scope.temp.fruitCategoryId = $scope.detail.fruitCategoryd;
                        $scope.loading = Rest.all('/book/fruit/add').post($scope.temp).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };
        $scope.edit = function(detail) {
            $uibModal.open({
                templateUrl: 'fruit/bookFruit/view/update.html',
                controller: function (rest, $scope,modal, $uibModalInstance) {
                	$scope.detail = angular.copy(detail);
                	Rest.all('/book/fruit/all').post().then(function (res) {
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
                        $scope.loading = Rest.one('/book/fruit//update', $scope.temp.id).doPUT($scope.temp).then(function (response) {
                            go(1);
                            $uibModalInstance.close();
                        })
                    };
                }
            });
        };

        $scope.delete = function(detail) {
        	modal.confirm('确定删除【' + detail.bookNm + '】?', function(){
        		$scope.loading = Rest.one('/book/fruit/delete', detail.id).doPUT(detail).then(function (response) {
                    go(1);
                })
                });
            };

       $scope.upload= function(detail) {
            	modal.confirm('确定提交【' + detail.bookNm + '】?', function(){
            		$scope.loading = Rest.one('/book/fruit/upload', detail.id).doPUT(detail).then(function (response) {
                        go(1);
                    })
                    });
                };     

    });
})();