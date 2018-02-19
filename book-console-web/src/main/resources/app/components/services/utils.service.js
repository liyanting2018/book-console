(function () {
    var app = angular.module(app_name);
    app.factory('utils', function(){
        function containsId(objects, id) {
            return getById(objects, id) != null;
        }

        function getById(objects, id) {
            for(var i=0; i<objects.length; i++) {
                if(objects[i].id == id) {
                    return objects[i];
                }
            }
            return null;
        }

        return {
            model: {
                containsId: containsId,
                getById: getById
            }
        }
    });
})();