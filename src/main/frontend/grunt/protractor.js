module.exports = {
  options: {
    configFile: 'spec/protractor.conf.js',

    keepAlive: false,

    noColor: false,

    args: {
      baseUrl: 'http://localhost:9007/',
      specs: ['spec/e2e/**/*.js'],
      allScriptsTimeout: 30000,
      getPageTimeout: 20000,
      framework: 'jasmine2',
      directConnect: true,
      capabilities: {
        browserName: 'chrome'
      }
    }
  },
  local: {
    options: {
    }
  },
  debug: {
    options: {
      debug: true
    }
  },
  headless: {
    options: {
      args: {
        baseUrl: 'http://localhost:9007/',
        specs: ['spec/e2e/**/*.js'],
        capabilities: {
          browserName: 'phantomjs'
        }
      }
    }
  }
};
