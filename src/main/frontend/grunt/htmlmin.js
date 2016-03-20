module.exports = {
  options: {
    collapseBooleanAttributes: true,
    collapseWhitespace: true,
    removeAttributeQuotes: true,
    removeCommentsFromCDATA: true,
    removeEmptyAttributes: true,
    removeOptionalTags: true,
    removeRedundantAttributes: true,
    useShortDoctype: true
  },

  index: {
    options: {
      removeComments: true
    },
    files: [{
      expand: true,
      cwd: '<%= config.dist %>',
      src: 'index.html',
      dest: '<%= config.dist %>'
    }]
  },

  templates: {
    files: [{
      expand: true,
      cwd: '<%= config.app %>',
      src: 'views/**/*.html',
      dest: '<%= config.tmp %>'
    }]
  },

  vendor: {
    files: [{
      expand: true,
      cwd: '<%= config.dist %>/vendor',
      src: '**/*.html',
      dest: '<%= config.dist %>/vendor'
    }]
  }
};
