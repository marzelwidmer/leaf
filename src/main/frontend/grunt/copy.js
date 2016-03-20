module.exports = {
  dist: {
    files: [{
      expand: true,
      dot:    true,
      cwd:    '<%= config.app %>',
      dest:   '<%= config.dist %>',
      src: [
        'index.html',
        'partials/*.html',
        'views/**',
        'styles/*.css'
      ]
    }]
  },
  vendor: {
    files: [
      {
        expand: true,
        dot:    true,
        cwd:    'bower_components/bootstrap/',
        dest:   '<%= config.dist %>',
        src: [
                'fonts/*.{eot,ttf,svg,woff,woff2}'
        ]
      },
      {
        expand: true,
        dot:    true,
        cwd:    'bower_components/angular-ui-grid/',
        dest:   '<%= config.dist %>/fonts',
        src: [
                '*.eot'
        ]
      }
    ]
  }
};
