(function() {
    'use strict';

    angular
        .module('yikatongApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('duguletian', {
            parent: 'entity',
            url: '/duguletian?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Duguletians'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/duguletian/duguletians.html',
                    controller: 'DuguletianController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('duguletian-detail', {
            parent: 'duguletian',
            url: '/duguletian/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Duguletian'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/duguletian/duguletian-detail.html',
                    controller: 'DuguletianDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Duguletian', function($stateParams, Duguletian) {
                    return Duguletian.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'duguletian',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('duguletian-detail.edit', {
            parent: 'duguletian-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/duguletian/duguletian-dialog.html',
                    controller: 'DuguletianDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Duguletian', function(Duguletian) {
                            return Duguletian.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('duguletian.new', {
            parent: 'duguletian',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/duguletian/duguletian-dialog.html',
                    controller: 'DuguletianDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                duguletian_name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('duguletian', null, { reload: 'duguletian' });
                }, function() {
                    $state.go('duguletian');
                });
            }]
        })
        .state('duguletian.edit', {
            parent: 'duguletian',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/duguletian/duguletian-dialog.html',
                    controller: 'DuguletianDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Duguletian', function(Duguletian) {
                            return Duguletian.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('duguletian', null, { reload: 'duguletian' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('duguletian.delete', {
            parent: 'duguletian',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/duguletian/duguletian-delete-dialog.html',
                    controller: 'DuguletianDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Duguletian', function(Duguletian) {
                            return Duguletian.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('duguletian', null, { reload: 'duguletian' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
