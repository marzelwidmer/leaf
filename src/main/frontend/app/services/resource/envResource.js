'use strict';

angular.module('LoggingStore')

.factory('EnvClient', function ($resource) {
    return $resource('actuator/env/');
});
