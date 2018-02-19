(function(){
    var app = angular.module(app_name);

    app.config(function($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise( function($injector) {
            var $state = $injector.get("$state");
            $state.go(default_state);
        });

        $stateProvider.state('home', {
            //url: '/home',
            templateUrl: 'home.html',
            controller: 'HomeCtrl'
        });
        $stateProvider.state('login', {
            url: '^/login',
            templateUrl: 'login.html',
            controller: 'LoginCtrl'
        });

        $stateProvider.state('sso', {
            url: '^/sso/:redirect',
            templateUrl: 'sso.html',
            controller: 'SSOCtrl'
        });

        $stateProvider.state('reject', {
            parent: 'home',
            url: '^/reject',
            template: '<h3>无权限</h3>'
        });

        $stateProvider.state('404', {
            parent: 'home',
            url: '^/404',
            template: '<h3>页面不存在</h3>'
        });
    });

    app.controller('LoginCtrl', function ($scope, $state, AuthService, RedirectService, $uibModal,Rest) {

        $scope.credentials = {
            username: '',
            password: ''
        };
        if (!$("body").hasClass("layout-top-nav")) {
            $("body").addClass("layout-top-nav");
        }
        $scope.submit = function() {
        	AuthService.login($scope.credentials, function(){
                $state.go(RedirectService.name, RedirectService.params);
            });
        }
        $scope.register = function(){
        	 $uibModal.open({
                 templateUrl: '/register.html',
                 controller: function (Rest, $scope,modal, $uibModalInstance) {
                	 $scope.register = {
                			 username:"",
                			 chineseName:""
                			 
                	 };
                     $scope.close = function () {
                         $uibModalInstance.close();
                     };
                     
                     $scope.confirm = function() {
                    	 $scope.loading = Rest.all('/user/register').post($scope.register).then(function () {
                             $uibModalInstance.close();
                         });
                     };
                 }
             });
        }
        
    });

    app.controller('HomeCtrl', function ($scope, $state, $stateParams, AuthService, USER_ROLES, Session, RedirectService) {

        $scope.currentUser = Session.user;
        $scope.userRoles = USER_ROLES;

        $scope.isAuthorized = AuthService.isAuthorized;

        $scope.size = 10;

        if (angular.isDefined(app.pageStyle)) {
            $scope.pageStyle = app.pageStyle;
        } else {
            if (angular.isDefined($stateParams.pageStyle)) {
                $scope.pageStyle = $stateParams.pageStyle;
            } else {
                $scope.pageStyle = '1';
            }
        }

        if ($("body").hasClass("layout-top-nav")) {
            $("body").removeClass("layout-top-nav");
        }

        $scope.sidebarToggle = function () {
            if ($("body").hasClass("sidebar-collapse")) {
                $("body").removeClass("sidebar-collapse");
            } else {
                $("body").addClass("sidebar-collapse");
            }
        }

        $scope.logout = function() {
            AuthService.logout(function(){
                //RedirectService.add($state.current.name, $stateParams);
                $state.go('login');
            });
        }
    });

    app.controller('SSOCtrl', function($scope, $state, $stateParams, AuthService, RedirectService) {
        var redirect = $stateParams.redirect || RedirectService.name;
        $.ajax({
            type: "get",
            async: false,
            url: "http://sso.pajk-ent.com/auth/auth_sso_token.js",
            dataType : "jsonp",
            jsonp: "callback",
            jsonpCallback: "set_cookie",
            success: function(token){
                AuthService.ssoLogin({token: token}, function(){
                    $state.go(redirect, RedirectService.params);
                });
            },
            error: function(error) {
                console.log(error);
                var redirectUrl = static_app_host + '/index.html#/sso/' + redirect;
                location.href = 'http://sso.pajk-ent.com/auth/login?redirect_url=' + encodeURIComponent(redirectUrl);
            }
        });


    });
})();