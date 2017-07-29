(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .controller('DuguletianDialogController', DuguletianDialogController);

    DuguletianDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Duguletian'];

    function DuguletianDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Duguletian) {
        var vm = this;

        vm.duguletian = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.duguletian.id !== null) {
                Duguletian.update(vm.duguletian, onSaveSuccess, onSaveError);
            } else {
                Duguletian.save(vm.duguletian, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('yikatongApp:duguletianUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
