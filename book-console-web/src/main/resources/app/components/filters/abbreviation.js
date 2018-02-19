(function () {
    var app = angular.module(app_name);

    app.filter('abbr', function() {
        return function (value, max, tail) {
            if (!value) return '';
            max = parseInt(max, 10);
            if (!max)  {
                max = 16;
            }
            if (value.length <= max) return value;

            value = value.substr(0, max);

            return value + (tail || '…');
        };
    });

})();
