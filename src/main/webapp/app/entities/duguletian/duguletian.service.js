(function() {
    'use strict';
    angular
        .module('yikatongApp')
        .factory('Duguletian', Duguletian);

    Duguletian.$inject = ['$resource'];

    function Duguletian ($resource) {
        var resourceUrl =  'api/duguletians/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
