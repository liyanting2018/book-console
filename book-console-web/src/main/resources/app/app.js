(function(){
    var app = angular.module(app_name, [
        'ui.bootstrap',
        'ui.router',
        'ngResource',
        'ngCookies',
        'ngSanitize',
        'w5c.validator',
        'ui.select',
        'ui.sortable',
        'ui.ace',
        'cgBusy',
        'restangular',
        'ngFileUpload'
    ]);

    app.run(function ($rootScope, $state, AuthService, RedirectService) {

        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {

            if (toState.name !== 'sso' && toState.name !== 'login' && !AuthService.isAuthenticated()) {
                RedirectService.add(toState.name, toParams);
                $state.go('login');
                event.preventDefault();
            }

        });

        $state.go(AuthService.isAuthenticated() ? default_state : 'login');

    });

    app.value('cgBusyDefaults', {
        message:'加载中...',
        backdrop: true,
        delay: 0,
        minDuration: 0,
        wrapperClass: ''
    });

})();

