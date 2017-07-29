(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('DuguletianDeleteController',DuguletianDeleteController);

    DuguletianDeleteController.$inject = ['$uibModalInstance', 'entity', 'Duguletian'];

    function DuguletianDeleteController($uibModalInstance, entity, Duguletian) {
        var vm = this;

        vm.duguletian = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Duguletian.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
