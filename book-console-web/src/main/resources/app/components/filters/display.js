(function () {
    var app = angular.module(app_name);

    app.filter('display', function() {
        var types = {
            ruleType: {
                0: 'GROOVY',
                1: '正则表达式'
            },
            ruleCreateType: {
                0: '标准输入',
                1: '自定义输入'
            },
            checkResult: {
                1: '正确',
                0: '错误',
                2: '未检查'
            },
            formatValidValue:{
                '0':'0',
                '1':'1',
                '2':'2',
                '':'2'
            }
        };

        return function(value, type) {
            if(type == 'role') {
                return roleNames(value);
            }

            if(type == 'checkResult') {
                if ( value == null || value == ''){
                    return types[type][2];
                }
            }

            if(type == 'formatValidValue'){
                if ( value == null || value == ''){
                    return types[type]['2'];
                }
            }
            return types[type][value];
        }
    });

    function roleNames(role) {
        var roles = {
            1: '普通用户',
            2: '审核人',
            64: '系统管理员',
            127: '超级管理员'
        };
        if(role == 127) return roles['127'];
        var names = [];
        for(var key in roles) {
            if((role & key) == key) {
                names.push(roles[key]);
            }
        }
        return names.join(', ');

    }


})();