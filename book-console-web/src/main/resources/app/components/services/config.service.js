(function () {
    var app = angular.module(app_name);

    //角色常量
    app.constant('CONFIG_KEYS', {
        STARTED: 'started',
        YEAR_MONTH: 'year_month',
        MAX_MONEY: 'max_money',
        DEFAULT_GOODS: 'default_goods',
        STOP_DATE: 'stop_date'
    });

    // ConfigService
    app.service('ConfigService', function (CONFIG_KEYS) {

        this.configs = {};

        this.getConfig = function(key) {
            for(var i=0; i<this.configs.length; i++) {
                var config = this.configs[i];
                if(config['key'] == key) return config;
            }
            return null;
        };

        this.isStarted = function() {
            var config = this.getConfig(CONFIG_KEYS.STARTED);
            return config!=null && config.value === 'true';
        };
        this.getYearMonth = function() {
            var config = this.getConfig(CONFIG_KEYS.YEAR_MONTH);
            return config!=null ? config.value : null;
        };
        this.getMaxMoney = function() {
            var config = this.getConfig(CONFIG_KEYS.MAX_MONEY);
            return config!=null ? config.value : null;
        };
        this.getDefaultGoods = function() {
            var config = this.getConfig(CONFIG_KEYS.DEFAULT_GOODS);
            return config!=null ? JSON.parse(config.value) : null;
        };
        this.getStopDate = function() {
            var config = this.getConfig(CONFIG_KEYS.STOP_DATE);
            return config!=null ? config.value : null;
        };
        this.init = function(configs, $scope) {
            this.configs = configs;
            $scope.yearMonth = this.getYearMonth();
            $scope.isStarted = this.isStarted();
            $scope.maxMoney = this.getMaxMoney();
            $scope.defaultGoods = this.getDefaultGoods();
            $scope.stopDate = this.getStopDate();
        }

    });
})();
