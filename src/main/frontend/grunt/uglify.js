module.exports = {
  options: {
    mangle: false,
    screwIE8: true
  },
  templates: {
    files: [{
      src:    '<%= config.tmp %>/scripts/templates.js',
      dest:   '<%= config.dist %>/scripts/templates.js'
    }]
  }
};
