/**
 * Created by lth on 2017/5/21.
 */
(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .config(html5ModeConfig);

    html5ModeConfig.$inject = ['$locationProvider'];

    function html5ModeConfig($locationProvider) {
        $locationProvider.html5Mode({ enabled: true, requireBase: true });
    }
})();
