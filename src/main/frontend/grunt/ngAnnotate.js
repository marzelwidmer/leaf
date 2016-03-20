module.exports = {
  options: {
    singleQuotes: true
  },
  dist: {
    files: [{
      expand: true,
      src:    '<%= config.tmp %>/concat/scripts/**/*.js'
    }]
  }
};
