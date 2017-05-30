(function () {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', 'toaster', 'Card'];

    function HomeController($scope, Principal, LoginService, $state, toaster, Card) {
        var vm = this;

        vm.currentName = $state.current.name;
        // console.log(vm.currentName);
        // vm.pageState = 'home';
        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        vm.jumpFromHome = jumpFromHome;
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function (account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function register() {
            $state.go('register');
        }

        function jumpFromHome(pageState) {
            // console.log(cardNumber);

            //这里想使用 （闭包函数？） state.go跳转 判断路由状态是否存在，如果不存在就、、、
            //或者县跳转在判断，没有上面的方法好，但是这种实现需要学习。state.go如果跳转失败执行相关语句的方法
            //仅仅是为了深入理解state.go
            // (function () {
            //     $state.go(pageState);
            // }());

            switch (pageState) {
                case 'blog': $state.go(pageState);
                    break;
                case '博客': $state.go('blog');
                    break;
                default:
                    $state.go('home');
                    //此处可以加toaster，但是还没调好
                    //造轮子？
                    break;
            }
        }
    }
})();
