module.exports = {
  options: {
    module: 'ccb',
    htmlmin: {
      collapseWhitespace: true,
      collapseBooleanAttributes: true
    }
  },
  dist: {
    cwd: '<%= config.tmp %>',
    src: ['views/**/*.html'],
    dest: '<%= config.tmp %>/scripts/templates.js'
  },
  server: {
    cwd: '<%= config.app %>',
    src: ['views/**/*.html'],
    dest: '<%= config.tmp %>/scripts/templates.js'
  }
};
