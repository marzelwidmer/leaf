'use strict';

angular
  .module('LoggingStore')
  .controller('DetailsController', function($scope, $routeParams, LogEntryClient, DashboardService) {

    // Check if the log entry is already available in the dashboard service.
    // If it is (which is usually the case) then use the instance from there
    // to assure the changes made to the log entry (e.g. change the status)
    // are also propagated to the dashboard view.

    var logEntries = DashboardService.items.filter(function(item) {
      // item.id is a number, $routeParams['logEntryId'] is a string
      return item.id == $routeParams['logEntryId'];
    });

    if (logEntries.length > 0) {
      $scope.logEntry = logEntries[0];
      $scope.json = createJson($scope.logEntry);
    } else {
      LogEntryClient.get({logEntryId: $routeParams['logEntryId']}).$promise.then(function(data) {
        $scope.logEntry = data;
        $scope.json = createJson($scope.logEntry);
      });
    }

    $scope.copyFailed = function(err) {
      console.error("Failed to copy JSON data.", err);
    };

    $scope.updateStatus = function() {
      LogEntryClient.patch({logEntryId: $scope.logEntry.id}, {status: $scope.logEntry.status});
    };

    // Creates the nicely formatted JSON snippets shown in the respective tabs
    function createJson(logEntry) {
      return {
        errors: logEntry.errors.map(function(error) {
          return JSON.stringify(error, null, 2);
        }),
        parameters: JSON.stringify(logEntry.parameters, null, 2),
        session: JSON.stringify(logEntry.sessionData, null, 2),
        notifier: JSON.stringify(logEntry.notifier, null, 2),
        context: JSON.stringify(logEntry.context, null, 2),
        environment: JSON.stringify(logEntry.environment, null, 2)
      };
    }

  });
