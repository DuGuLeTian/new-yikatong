(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('blog-page', {
            parent: 'app',
            url: '/blog/:date',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/blog/blog-page.html',
                    controller: 'BlogPageController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
