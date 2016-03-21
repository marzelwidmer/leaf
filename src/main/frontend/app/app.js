'use strict';

angular.module('LoggingStore', [
    'ui.router',
    'ngRoute',
    'ngResource',
    'ui.bootstrap',
    'angular-clipboard'
])

.config(function ($routeProvider) {
$routeProvider
    .when('/', {
        templateUrl: 'partials/dashboard.html',
        controller: 'DashboardController'
    })
    .when('/details/:logEntryId', {
        templateUrl: 'partials/details.html',
        controller: 'DetailsController'
    })
    .when('/api', {
        templateUrl: 'partials/public-rest-api.html'
    })
    .otherwise({
        redirectTo: '/'
    });
});

