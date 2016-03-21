'use strict';

angular.module('LoggingStore')

.factory('SearchClient', function ($resource) {
    return $resource('logentries/search/all');
});
