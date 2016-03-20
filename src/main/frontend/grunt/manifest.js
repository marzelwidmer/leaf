module.exports = {
  options: {
    preferOnline: true,
    timestamp: false,
    hash: true,
    exclude: [
      'manifest.appcache'
    ],
    process: function (path) {
      return path.replace(/^app(\/|$)/, '');
    }
  },
  dev: {
    options: { basePath: '' },
    src: [
      '<%= config.app %>/{flash,images,locales,scripts,views,styles}/**/*.*',
      '<%= config.app %>/index.html',
      '<%= config.models %>/**/*.*'
    ],
    dest: '<%= config.app %>/manifest.appcache'
  },
  dist: {
    options: { basePath: '<%= config.dist %>' },
    src: [ '**/*.*' ],
    exclude: [ 'styles/main.css.map', 'styles/print.css.map' ],
    dest: '<%= config.dist %>/manifest.appcache'
  }
};
