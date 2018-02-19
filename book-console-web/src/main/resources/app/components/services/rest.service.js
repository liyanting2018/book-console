(function () {
    var app = angular.module(app_name);

    /**
     * api请求
     * method: GET, POST ... 默认 GET
     * url: 请求url, base + url
     * useJson: 请求体是否是json, 默认 false, 使用 application/x-www-form-urlencoded
     */
    app.factory('rest', function (HttpFactory) {
        var user = HttpFactory(api_endpoint + '/user', {
            currentUser: {method: 'GET', url: '/current'},
            login: {method: 'POST', url: '/login'},
            ssoLogin: {method: 'POST', url: '/sso/login'},
            logout: {method: 'POST', url: '/logout'},
            all: {method: 'GET', url: '/all'},
            updateRole: {method: 'POST', url: '/updateRole'}

        });
        return {
            user: user
        }
    });



    /**
     * 统一错误处理， 默认 {alert: true, redirect: false, continue: false}
     * 即: 弹出错误信息， 不跳转到其他页面，不继续往下处理
     * 如果未配置某个错误码， 则按上述处理
     * 也可以配置成函数, 例如:
     *   {
     *       '0': function() {
     *            // 处理逻辑
     *            return false;
     *       }
     *   }
     * 注: 函数可以自动注入angular注册的组件，返回布尔值标示请求是否往下处理, false: 不处理
     */
    app.factory('resultPreHandlers', function () {

        return {
            //成功
            '0': {alert: false, continue: true},
            //系统错误
            '1': {},
            //表单错误
            '2': {},
            //无权限
            '3': function (modal) {
                modal.alert('无权限');
                return false;
            },
            //未登录
            '1000': {alert: false, redirect: 'login'},
            //登陆失败
            '1001': {},
            //用户名密码不正确
            '1002': function (modal) {
                modal.alert('用户名密码不正确, ^.^');
                return false;
            }
        };
    });
    app.factory('Rest', function(Restangular, resultPreHandle, modal) {
        return Restangular.withConfig(function(RestangularConfigurer) {
            RestangularConfigurer.setBaseUrl(api_endpoint);
            RestangularConfigurer.addResponseInterceptor(function(data, operation, what, url, response, deferred) {
                if(resultPreHandle(data)) {
                    var extractedData;
                    if (operation === "getList") {
                        if(data.data.pageNum != null) {
                            extractedData = data.data.list;
                            if (extractedData != null) {
                                extractedData.pageInfo = data.data;
                            }
                        }else{
                            extractedData = data.data;
                        }
                    } else {
                        extractedData = data.data;
                    }
                    return extractedData;
                }
                return deferred.reject(data);
            });

            RestangularConfigurer.setErrorInterceptor(function() {
                modal.alert('请求失败，请重试！');
                return false;
            });
        });
    });

})();