module.exports = {
  options: {
    config: '.jscsrc'
  },
  default: {
    src: [
      '<%= config.app %>/scripts/**/*.js',
      'spec/**/*.js',
      'grunt/**/*.js'
    ]
  }
};
