module.exports = {
  options: {
    limit: 8
  },
  server: [
  //  'sass:server',
    'ngtemplates:server',
    'clean:coverage',
    'wiredep:app'
  ],
  dist: [
    'ngtemplates:dist',
    'wiredep:app'
  ],
  test: [
    'wiredep:test'
  ]
};
