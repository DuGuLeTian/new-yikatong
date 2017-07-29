(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('TournamentDetailController', TournamentDetailController);

    TournamentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tournament'];

    function TournamentDetailController($scope, $rootScope, $stateParams, previousState, entity, Tournament) {
        var vm = this;

        vm.tournament = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('yikatongApp:tournamentUpdate', function(event, result) {
            vm.tournament = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
