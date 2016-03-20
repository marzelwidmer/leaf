module.exports = {
  options: {
    dest: '<%= config.dist %>',
    flow: {
      steps: {
        jsnu: ['concat'],
        js: ['concat', 'uglify'],
        css: ['concat', 'cssmin']
      },
      post: {}
    }
  },

  html: '<%= config.app %>/index.html'
};
