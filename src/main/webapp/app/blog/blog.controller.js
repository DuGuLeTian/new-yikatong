(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('BlogController', BlogController);

    BlogController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'toaster', 'Card'];

    function BlogController ($scope, Principal, LoginService, $state, toaster, Card) {
        var vm = this;

        vm.currentName = $state.current.name;

        // console.log(vm.currentName);


    }
})();
