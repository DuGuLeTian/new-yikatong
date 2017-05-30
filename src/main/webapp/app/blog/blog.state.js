(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
            parent: 'app',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
