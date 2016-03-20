module.exports = {
  options: {
    jshintrc: '.jshintrc'
  },
  default: {
    src: [
      '<%= config.app %>/scripts/**/*.js',
      'spec/**/*.js',
      'grunt/**/*.js'
    ]
  }
};
