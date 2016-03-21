/**
 * Main Grunt configuration.
 *
 * For plugin configuration, see `grunt` folder, which contains files
 * managed by `load-grunt-config`.
 *
 * Options and their defaults:
 * - `--app-dir=app`: the main application folder.
 * - `--dist-dir=../../../target-dist`: the target of `grunt build`.
 * - `--tmp-dir=.tmp`: location of temporary files.
 * - `--models-dir=model`: location of PROMOS model files.
 * - `--vendor-dir=vendor`: the vendor folder.
 * - `--browser=Chrome`: browser to use for unit tests.
 * - `--profile=false`: flag whether to profile grunt via `time-grunt`.
 * - `--proxy=https://portal.helsana-test.ch`: proxy used for API requests.
 *
 */
var fs    = require('fs');

module.exports = function (grunt) {
  var config = {
    app:     grunt.option('app-dir')    || 'app',
    dist:    grunt.option('dist-dir')   || '../resources/static',
    tmp:     grunt.option('tmp-dir')    || '.tmp',
    models:  grunt.option('models-dir') || 'model',
    vendor:  grunt.option('vendor-dir') || 'vendor',
    browser: grunt.option('browser')    || 'Chrome',
    origin:  grunt.option('origin')     || 'https://portal.helsana-u1-test.ch',
    apiMode: grunt.option('api-mode')   || 'mock'
  };

  grunt.option('origin', grunt.option('origin') || config.origin);
  grunt.option('api-mode', grunt.option('api-mode') || config.apiMode);

  // Measure timings only when requested via --profile
  if (grunt.option('profile')) {
    require('time-grunt')(grunt);
  }

  require('load-grunt-config')(grunt, {
    data: { config: config }
  });

  grunt.registerTask('serve', function (target) {
    // Start Livereload manually instead of using grunt-contrib-watch's
    // built in solution, see:
    // https://github.com/gruntjs/grunt-contrib-watch/issues/186
    var livereload = require('tiny-lr')();
    livereload.listen(35729);
    grunt.registerTask('livereload', function () {
      livereload.changed({
        body: {
          files: [
            '.tmp/styles/leaf.css'
          ]
        }
      });
    });

    if (target === 'dist') {
      return grunt.task.run([
        'build:dist',
        'connect:dist:keepalive'
      ]);
    } else {
      return grunt.task.run([
        'build:dev',
        'watch'
      ]);
    }
  });

  grunt.registerTask('build', function (target) {
    var env = target || 'dist';
    return grunt.task.run(['build:' + env]);
  });

  // Apply notification settings, this is required because
  // it hooks itself into other tasks automatically
  grunt.task.run('notify_hooks');
};
