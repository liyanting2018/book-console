(function(){
    var app = angular.module(app_name);

    app.factory('modal', function($uibModal){
        return {
            alert: function(title, content, cb) {
                if (arguments.length == 2 && angular.isFunction(content)) {
                    cb = content;
                    content = title;
                    title = '提示';
                }

                if(arguments.length == 1) {
                    content = title;
                    title = '提示';
                }

                $uibModal.open({
                    templateUrl: 'components/view/alert.html',
                    controller: function ($scope, $uibModalInstance) {
                        $scope.title = title;
                        $scope.content = content;

                        $scope.confirm = function () {
                            if(angular.isFunction(cb)) {
                                cb();
                            }
                            $uibModalInstance.close();
                        };
                    }
                });
            },

            confirm: function(content, cb) {
                $uibModal.open({
                    templateUrl: 'components/view/confirm.html',
                    controller: function ($scope, $uibModalInstance) {
                        $scope.confirmInfo = content;
                        $scope.close = function () {
                            $uibModalInstance.close();
                        };
                        $scope.confirm = function () {
                            if(angular.isFunction(cb)) {
                                cb();
                            }
                            $uibModalInstance.close();
                        };
                    }
                });
            }
        };
    });
})();