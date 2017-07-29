(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('DuguletianDetailController', DuguletianDetailController);

    DuguletianDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Duguletian'];

    function DuguletianDetailController($scope, $rootScope, $stateParams, previousState, entity, Duguletian) {
        var vm = this;

        vm.duguletian = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('yikatongApp:duguletianUpdate', function(event, result) {
            vm.duguletian = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
