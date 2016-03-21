'use strict';

angular.module('LoggingStore')

.factory('LogEntryClient', function ($resource) {
  return $resource('logentries/:logEntryId', {logEntryId: '@id'}, {
    patch: {method: "PATCH"}
  });
});
