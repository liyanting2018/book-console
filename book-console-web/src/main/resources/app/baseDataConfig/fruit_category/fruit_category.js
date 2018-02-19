(function () {
    var app = angular.module(app_name);

    app.controller('fruitCategoryCtrl', function ($scope, $state, $uibModal, Rest) {
        $scope.loading = true;
        $scope.page = 1;

        go($scope.page);
        function go(page) {
            $scope.page = page;
            Rest.all('fruit/category/search').getList({page: $scope.page, name: $scope.name}).then(function (response) {
                $scope.categorys = response;
                $scope.total = response.pageInfo.total;
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
                templateUrl: 'baseDataConfig/fruit_category/view/add.html',
                controller: function (Rest, $scope, modal, $uibModalInstance) {
                	$scope.category= {
                			fruitLevel:""
                	};
                	$scope.fruitLevels = [
                		{"name":"A"},
                		{"name":"B"},
                		{"name":"C"},
                		{"name":"D"}
                		
                	];
                    $scope.fruitcategory = {
                        name: ''
                    };
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.confirm = function () {
                    	$scope.fruitcategory.fruitLevel = $scope.category.fruitLevel.name;
                        $scope.loading = Rest.all('fruit/category/add').post($scope.fruitcategory).then(function () {
                            go(1);
                            $uibModalInstance.close();
                        });
                    };
                }
            });
        };
        $scope.edit = function (category) {
            $uibModal.open({
                templateUrl: 'baseDataConfig/fruit_category/view/edit.html',
                controller: function (Rest, $scope, modal, $uibModalInstance) {
                	$scope.fruitLevels = [
                		{"name":"A"},
                		{"name":"B"},
                		{"name":"C"},
                		{"name":"D"}
                		
                	];
                    $scope.fruitcategory = angular.copy(category);
                    
                    angular.forEach($scope.fruitLevels, function (data) {
                        if (data.name == $scope.fruitcategory.fruitLevel) {
                        	$scope.fruitcategory.fruitLevel = data;
                        }
                    });
                    
                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                    $scope.confirm = function () {

                    	$scope.fruitcategory.fruitLevel = $scope.fruitcategory.fruitLevel.name;
                        
                        $scope.loading = Rest.one('fruit/category/update', $scope.fruitcategory.id).doPUT($scope.fruitcategory).then(function (response) {
                            go(1);
                            $uibModalInstance.close();
                        })

                    };
                }
            });
        };
        
        $scope.deleteFruitCategory = function (fruitcategory) {
            Rest.one('fruit/category/delete', fruitcategory.id).doPUT(fruitcategory).then(function (response) {
                go(1);
            })

        };

    });
})();