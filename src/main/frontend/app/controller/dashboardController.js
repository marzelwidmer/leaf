'use strict';

angular
  .module('LoggingStore')
  .controller('DashboardController',
              function($scope, $http, $resource, $location, LogEntryClient, SearchClient, DashboardService) {

                function setResponseData(data) {
                  var page = data.page;
                  DashboardService.items = data._embedded.logEntries;
                  DashboardService.pagination.total = page.totalElements;
                  DashboardService.pagination.page = page.number + 1;
                  DashboardService.pagination.size = page.size;
                  DashboardService.update($scope);
                }

                function loadPage() {
                  var client = LogEntryClient;
                  var params = {
                    page: DashboardService.pagination.page - 1,
                    size: DashboardService.pagination.size,
                    sort: DashboardService.sorting.column + ',' + DashboardService.sorting.order
                  };

                  if (DashboardService.searching.term) {
                    client = SearchClient;
                    params['contains'] = DashboardService.searching.term;
                  }

                  client.get(params).$promise.then(function(data) {
                    setResponseData(data);
                  });
                }

                if (!DashboardService.initialized) {
                  DashboardService.initialized = true;
                  loadPage();
                } else {
                  DashboardService.update($scope);
                }

                $scope.sort = function(column, order) {
                  DashboardService.sorting = {column: column, order: order};
                  loadPage();
                };

                // The pagination widget calls this function when the user clicks on "next", "last", ...
                $scope.pageChanged = function(page) {
                  DashboardService.pagination.page = page;
                  loadPage();
                };

                $scope.search = function(term) {
                  DashboardService.searching.input = term;
                  DashboardService.searching.term = term;
                  DashboardService.pagination.page = 0;
                  loadPage();
                };

                $scope.clearSearch = function() {
                  DashboardService.searching.input = '';
                  DashboardService.searching.term = '';
                  DashboardService.pagination.page = 0;
                  loadPage();
                };

                $scope.refresh = function() {
                  loadPage();
                }

                $scope.updateStatus = function(logEntry) {
                  LogEntryClient.patch({logEntryId: logEntry.id}, {status: logEntry.status});
                }

              }
  );
