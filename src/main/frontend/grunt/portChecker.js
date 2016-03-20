module.exports = {
  dist: {
    target: 'connect.e2e-dist.options.port',
    affectPaths: [
      'protractor.options.args.baseUrl'
    ]
  },
  dev: {
    target: 'connect.e2e-dev.options.port',
    affectPaths: [
      'protractor.options.args.baseUrl'
    ]
  }
};
