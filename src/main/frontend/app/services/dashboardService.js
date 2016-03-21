'use strict';

angular
  .module('LoggingStore')
  .service('DashboardService', function() {

    this.initialized = false;

    this.items = [];

    this.sorting = {
      // sorting column: must be one of the column names defined in ch.helsana.web.store.model.Store.java
      column: 'timestamp',
      // sorting order: can be 'asc' oder 'desc'
      order: 'desc'
    }

    this.searching = {
      // the text shown in the search text field
      input: '',
      // the term of the currently active search
      term: ''
    }

    this.pagination = {
      // the current page, first page is 0
      page: 0,
      // the total number of items
      total: 0,
      // the number of items shown on a page
      size: 20
    }

    this.update = function(scope) {
      scope.items = this.items;
      scope.sorting = this.sorting;
      scope.searching = this.searching;
      scope.pagination = this.pagination;
    };

  });
