var options = {
  coverageReporter: {
    dir:    'report/coverage/',
    subdir: '.',
    reporters: [{
      type:   'html'
    }, {
      type:   'json'
    }, {
      type:   'text-summary'
    }]
  },

  preprocessors:  {
    '<%= config.app %>/scripts/**/*.js': ['coverage'],
    'app/views/**/*.html': ['ng-html2js']
  }
};

module.exports = {
  options: {
    configFile: 'test/karma.conf.js',
    autoWatch:  false,
    colors:     true,
    singleRun:  true,
    browsers:   ['<%= config.browser %>'],
    reporters:  ['dots'],
    frameworks: ['jasmine'],
    browserNoActivityTimeout: 60000
  },
  unit: {
    background: true,
    singleRun: false
  },
  all: {
    browsers: ['Chrome', 'Firefox']
  },
  dist: {
  },
  dev: {
    browsers: ['<%= config.browser %>'],
    singleRun: false,
    autoWatch: true
  },
  coverage: {
    browsers: ['<%= config.browser %>'],
    background: true,
    singleRun: false,
    reporters: ['dots', 'coverage'],
    preprocessors: options.preprocessors,
    coverageReporter: options.coverageReporter
  },
  coverageCi: {
    singleRun: true,
    reporters: ['dots', 'coverage'],
    preprocessors: options.preprocessors,
    coverageReporter: options.coverageReporter
  },
  spec: {
    reporters: ['progress', 'html'],
    htmlReporter: {
      outputDir:  'report/spec',
      namedFiles: true
    }
  }
};
