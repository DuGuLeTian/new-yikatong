(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('CardController', CardController);

    CardController.$inject = ['Card'];

    function CardController(Card) {

        var vm = this;

        vm.cards = [];

        loadAll();

        function loadAll() {
            Card.query(function(result) {
                vm.cards = result;
                vm.searchQuery = null;
            });
        }
    }
})();
