module.exports = {
  dist: {
    options: {
      force: true
    },
    files: [{
      dot:   true,
      src: [
        '<%= config.tmp %>',
        '<%= config.dist %>/*'
      ]
    }]
  },
  server: '<%= config.tmp %>',
  coverage: 'report/coverage',
  spec: 'report/spec',
  docs: 'docs/partials'
};
