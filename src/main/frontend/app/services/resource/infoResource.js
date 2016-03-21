'use strict';

angular.module('LoggingStore')

.factory('InfoClient', function ($resource) {
    return $resource('actuator/info/');
});
