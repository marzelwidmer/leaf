module.exports = {
  options: {
    spawn: false
  },
  livereload: {
    files: ['<%= config.tmp %>/**/*.css'],
    tasks: ['livereload']
  },
  bower: {
    files: ['bower.json'],
    tasks: ['wiredep']
  },
  templates: {
    files: ['<%= config.app %>/views/{,**/}*.html'],
    tasks: ['ngtemplates:server']
  },
  gruntfile: {
    files: ['Gruntfile.js', 'grunt/**/*.js'],
    options: {
      reload: true
    }
  },
  styles: {
    files: ['<%= config.app %>/styles/{,**/}*.{scss,sass}'],
    tasks: ['sass:server']
  },
  lint: {
    files: [
      '<%= config.app %>/scripts/**/*.js',
      'spec/unit/**/*.js',
      'grunt/**/*.js'
    ],
    tasks: ['jscs', 'jshint']
  },
  karma: {
    files: [
      '<%= config.app %>/scripts/**/*.js',
      'spec/unit/**/*.js',
      'grunt/**/*.js'
    ],
    tasks: ['karma:coverage:run']
  },
  docs: {
    files: ['<%= config.app %>/scripts/**/*.js'],
    tasks: ['dgeni']
  }
};
