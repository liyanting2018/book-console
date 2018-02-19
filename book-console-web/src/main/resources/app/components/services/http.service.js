(function(){

    var app = angular.module(app_name);

    app.factory('resultPreHandle', function($injector, $state, modal, resultPreHandlers){

        var defaults = {
            alert: true,
            redirect: false,
            continue: false
        };
        
        return function(result) {
            var handler = resultPreHandlers[result.code];

            if(angular.isFunction(handler)) {
                return $injector.invoke(handler);
            }

            var config = angular.extend({}, defaults, handler);
            if(config.alert) {
                modal.alert(result.msg);
            }
            if(config.redirect !== false) {
                $state.go(config.redirect);
            }
            return config.continue;
        }
    });

    app.factory('HttpFactory', function($http, resultPreHandle) {

        var transformRequest = function(obj) {
            var str = [];
            for(var p in obj)
                str.push(encodeURIComponent(p) + "=" + (obj[p] == null ? '' : encodeURIComponent(obj[p])));
            return str.join("&");
        };

        var defaults = {
            url: '',
            method: 'GET',
            useJson: false,
            withCredentials: true
        };

        function processConfig(config, url) {
            config = angular.extend({}, defaults, config);

            if(!angular.isDefined(config['url']) || url.length < 1) {
                throw 'HttpFactory 配置出错, url为空';
            }

            if(url.charAt(url.length - 1) == '/' && config['url'].charAt(0) == '/') {
                config['url'] = url.substr(0, url.length - 1) + config['url'];
            } else if(url.charAt(url.length - 1) != '/' && config['url'].charAt(0) != '/') {
                config['url'] = url + '/' + config['url'];
            } else {
                config['url'] = url + config['url'];
            }

            if(!config.useJson) {
                config['headers'] = {'Content-Type': 'application/x-www-form-urlencoded'};
                config['transformRequest'] = transformRequest;
                delete config['useJson'];
            }

            return config;
        }

        function setUrlParams(config, params) {
            var url = config.url;
            var protocolAndDomain = '';
            var PROTOCOL_AND_DOMAIN_REGEX = /^https?:\/\/[^\/]*/;

            url = url.replace(PROTOCOL_AND_DOMAIN_REGEX, function(match){
                protocolAndDomain = match;
                return '';
            });
            url.split(/\//).forEach(function(item){
                if(item.charAt(0) == ':') {
                    url = url.replace(new RegExp(item), function(){
                        var param = item.substr(1);
                        if(params.hasOwnProperty(param)) {
                            return params[param];
                        } else {
                            throw 'url 路径参数出错';
                        }
                    });
                }
            });

            config.url = protocolAndDomain + url;
        }

        return function(url, configs) {
            var http = {};
            for(var cb in configs) {
                http[cb] = (function(config) {
                    config = processConfig(config, url);
                    return function(a1, a2, a3) {
                        var data, success, error;
                        var hasBody = /^(POST|PUT|PATCH)$/i.test(config.method);
                        switch (arguments.length) {
                            case 3:
                                data = a1;
                                success = a2;
                                error = a3;
                                break;
                            case 2:
                                if (angular.isFunction(a1)) {
                                    success = a1;
                                    error = a2;
                                } else {
                                    data = a1;
                                    success = a2;
                                }
                                break;
                            case 1:
                                if (angular.isFunction(a1)) success = a1;
                                else data = a1;
                                break;
                            case 0: break;
                            default:
                                throw 'HttpFactory 调用参数出错';
                        }

                        if(hasBody) {
                            config.data = data;
                        } else {
                            config.params = data;
                        }

                        setUrlParams(config, data);

                        return $http(config).then(function(response){
                            var result = response.data;
                            if(resultPreHandle(result) && angular.isFunction(success)) {
                                success(result);
                            }
                            return result;
                        }, function(err){
                            console.error(err);
                            if(angular.isFunction(error)) {
                                error(err);
                            }
                        });
                    };
                })(configs[cb]);
            }
            return http;
        };
    });

})();