module.exports = {
  app: {
    // exclude: [ /bootstrap/ ], // wait for https://github.com/dwmkerr/angular-modal-service/issues/60
    src: ['<%= config.app %>/index.html']
  },
  test: {
    exclude:    [ /jasmine-core/ ],
    src:        [ 'test/karma.conf.js', 'test/karma.conf.ci.js' ],
    ignorePath: '../',
    devDependencies: true
  }
};
