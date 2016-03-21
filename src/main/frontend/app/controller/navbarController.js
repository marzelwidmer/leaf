'use strict';

angular
.module('LoggingStore')
.controller('NavBarController', function ($scope, EnvClient, InfoClient) {

  EnvClient.get().$promise.then(function (data) {
    $scope.profiles = data.profiles;
    for (var key in data) {
      if (key.indexOf('applicationConfig') == 0) {
        $scope.database = data[key]['spring.datasource.url'];
        break;
      }
    }
  });

  InfoClient.get().$promise.then(function (data) {
    $scope.appVersion = data.app.version;
  });

});
