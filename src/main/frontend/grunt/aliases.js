module.exports = {
  default: 'serve',

  // Builds the application without processing any assets while
  // ignoring lint-, test- or coverage errors.
  'build:dev': [
    'clean:server',
    'concurrent:server',
    'connect:livereload'
  ],

  // Builds the application to the config.dist directory unless errors
  // in the following areas are encountered:
  // * SASS compilation
  // * Linters (JSCS and JSHint)
  // * Unit tests
  // * Integration (e2e) tests (run against the compiled output)
  // * Translation consistency
  // The output will contain minified JavaScript, CSS and HTML and
  // assets will be concatenated where possible.
  'build:dist': [
    'clean',

    'useminPrepare',
    'htmlmin:templates',
    'concurrent:dist',

    'concat:generated',
    'ngAnnotate',
    'cssmin:generated',
    'uglify:generated',
    'uglify:templates',
    'copy:dist',
    'copy:vendor',
    'usemin',
    'htmlmin:index',
    'htmlmin:vendor',
    'manifest:dist'

  ],

  // Stricter version of `build:dist` asserting a certain level
  // of unit test coverage.
  'build:strict': [
    'clean',

    'useminPrepare',
    'htmlmin:templates',
    'concurrent:dist',

    //'karma:dist',

    'clean:coverage',
    'karma:coverageCi',
    'coverage',

    'concat:generated',
    'ngAnnotate',
    'cssmin:generated',
    'uglify:generated',
    'uglify:templates',
    'copy:dist',
    'copy:vendor',
    'usemin',
    'htmlmin:index',
    'htmlmin:vendor',
    'manifest:dist',

    'portChecker:dist',
    'connect:e2e-dist',
    'protractor:local'
  ],

  'pre-unit': [
     'clean:server',
     'concurrent:test'
  ],

  unit: [
    'pre-unit',
    'karma:dist'
  ],

  'unit:all': [
    'pre-unit',
    'karma:all'
  ],

  e2e: [
    'clean:server',
    'concurrent:server',
    'portChecker:dev',
    'connect:e2e-dev',
    'protractor:local'
  ],

  'e2e:debug': [
    'clean:server',
    'concurrent:server',
    'portChecker:dev',
    'connect:e2e-dev',
    'protractor:debug'
  ],

  'e2e:headless': [
    'clean:server',
    'concurrent:server',
    'portChecker:dev',
    'connect:e2e-dev',
    'protractor:headless'
  ],

  'report:coverage': [
    'clean:server',
    'clean:coverage',
    'wiredep:test',
    'karma:coverageCi',
    'open:coverage'
  ],

  'report:complexity': [
    'clean:server',
    'wiredep:test',
    'plato',
    'open:complexity'
  ],

  'report:spec': [
    'clean:spec',
    'wiredep:test',
    'karma:spec',
    'open:spec'
  ],

  lint: [
    'jscs',
    'jshint'
  ],

  'run:dev': [
    'clean',

    'concurrent:server',
    'connect:livereload:keepalive'
  ],

  docs: [
    'clean:docs' //,
    //'dgeni'
  ],

  'docs:serve': [
    'docs',
    'connect:docs:keepalive'
  ],

  'run:dist': [
    'clean',

    'useminPrepare',
    'htmlmin:templates',
    'concurrent:dist',

    'concat:generated',
    'ngAnnotate',
    'cssmin:generated',
    'uglify:generated',
    'uglify:templates',
    'copy:dist',
    'copy:vendor',
    'usemin',
    'htmlmin:index',
    'htmlmin:vendor',
    'manifest:dist' //,

    //'portChecker:dist',
    //'connect:e2e-dist:keepalive'
  ]
};
