(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('BlogController', BlogController);

    BlogController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'toaster', 'Card'];

    function BlogController ($scope, Principal, LoginService, $state, toaster, Card) {
        var vm = this;

        vm.currentName = $state.current.name;
        vm.cards = [];
        // console.log(vm.currentName);
        vm.cardIsReclaimed = false;
        vm.cardNumber = null;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.searchCardNumber = searchCardNumber;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }
        function searchCardNumber(cardNumber) {
            // console.log(cardNumber);

            if (vm.cardNumber === null) {
                toaster.pop('error', '', '请输入卡号');
            } else {
                Card.query(function(result) {
                    vm.cards = result;

                });
                for (var i = 0; i < vm.cards.length; i++) {
                    if (parseInt(vm.cardNumber) === vm.cards[i].number) {
                        toaster.pop('success', '', '您的一卡通已被系统回收');
                        vm.cardIsReclaimed = true;
                        break;
                    }
                }

                if (vm.cardIsReclaimed === false) {
                    toaster.pop('error', '', '您的一卡通未被系统回收');
                }
            }
            vm.cardNumber = null;
            vm.cardIsReclaimed = false;
        }
    }
})();
