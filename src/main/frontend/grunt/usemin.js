module.exports = {
  options: {
    assetsDirs: [
      '<%= config.dist %>',
      '<%= config.dist %>/images'
    ],
    blockReplacements: {
      jsnu: function (block) {
        return '<script src="' + block.dest + '"></script>';
      }
    }
  },

  html: '<%= config.dist %>/**/*.html',
  css:  '<%= config.dist %>/styles**/*.css'
};
