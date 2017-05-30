(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('blog', {
            parent: 'app',
            url: '/blog',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/blog/blog.html',
                    controller: 'BlogController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
