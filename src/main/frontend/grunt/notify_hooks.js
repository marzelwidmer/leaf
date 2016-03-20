var grunt = require('grunt');

module.exports = {
  options: {
    enabled: !grunt.option('no-notify'),
    max_jshint_notifications: 1
  }
};
