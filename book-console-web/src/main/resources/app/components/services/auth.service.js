(function () {
    var app = angular.module(app_name);

    //角色常量
    app.constant('USER_ROLES', {
        ALL: 0,
        NORMAL: 1,
        EDITOR: 2,
        MANAGER: 64,
        ADMIN: 127
    });

    // AuthService
    app.factory('AuthService', function($cookies, rest, USER_ROLES, Session) {
        var authService = {};

        authService.ssoLogin = function(token, cb) {
            rest.user.ssoLogin(token, function(result){
                Session.create(result.data);
                if(angular.isFunction(cb)) {
                    cb(result.data);
                }
            });
        };

        authService.login = function(credentials, cb) {
            rest.user.login(credentials, function(result){
                Session.create(result.data);
                if(angular.isFunction(cb)) {
                    cb(result.data);
                }
            });
        };

        authService.logout = function(cb) {
            rest.user.logout(function(){
                Session.destroy();
                if(angular.isFunction(cb)) {
                    cb();
                }
            });
        };

        authService.isAuthenticated = function () {
            if(!Session.user) {
                var userJson = $cookies.get('pajk_user');
                if(userJson) {
                    Session.user = JSON.parse(decodeURIComponent(userJson));
                }
            }
            return !!Session.user;
        };

        authService.isAuthorized = function (roles) {
            if (!angular.isArray(roles)) {
                roles = [roles];
            }
            return (authService.isAuthenticated() && hasRole(roles, Session.user.role));

            function hasRole(roles, role) {
                if(role == USER_ROLES.ADMIN) return true;

                for(var i=0; i<roles.length; i++) {
                    if(roles[i]== USER_ROLES.ALL || ((role & roles[i]) == roles[i])) return true;
                }
                return false;
            }
        };

        return authService;

    });

    app.service('Session', function(){
        this.create = function(user) {
            this.user = user;
        };
        this.destroy = function() {
            this.user = null;
        };
    });

    app.service('RedirectService', function() {
        this.name = default_state;
        this.params = {};

        this.add = function(name, params) {
            this.name = name == 'login' ? default_state : name;
            this.params = params;
        };

    });
    
})();