module.exports = {
  options: {
  },
  server: {
    files: [{
      expand: true,
      cwd: '<%= config.app %>/styles',
      src: ['{,**/}*.{scss,sass}'],
      dest: '<%= config.tmp %>/styles',
      ext: '.css'
    }],
    options: {
    }
  },
  dist: {
    files: [{
      expand: true,
      cwd: '<%= config.app %>/styles',
      src: ['{,**/}*.{scss,sass}'],
      dest: '<%= config.dist %>/styles',
      ext: '.css'
    }],
    options: {
      sourceMap: true,
      outputStyle: 'compressed'
    }
  }
};
