(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('FooterController', FooterController);

    FooterController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function FooterController ($state, Auth, Principal, ProfileService, LoginService) {
        var vm = this;


    }
})();
