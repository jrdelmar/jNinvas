'use strict';

/**
 * Route configuration for the RDash module.
 */
angular.module('RDash').config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        // For unmatched routes
        $urlRouterProvider.otherwise('/search');

        // Application routes
        $stateProvider
            .state('search', {
                cache: false,
                url: '/search' ,
                templateUrl: 'templates/search.html',
            })
            .state('history', {
                cache: false,
                url: '/history' ,
                templateUrl: 'templates/history.html',
            })
    }

]);;;;
