var middleware,
    proxyMiddleware = require('http-proxy-middleware'),
    grunt = require('grunt');

middleware = function (connect, options) {
  var middlewares = [],
      mountFolder = function (connect, dir) {
        return connect.static(require('path').resolve(dir));
      };

  if (!Array.isArray(options.base)) {
    options.base = [options.base];
  }

  middlewares.push(connect()
    .use('/bower_components', mountFolder(connect, './bower_components')));
  middlewares.push(connect()
    .use('/vendor', mountFolder(connect, './vendor')));
  middlewares.push(connect()
    .use('/model', mountFolder(connect, './model')));
  middlewares.push(proxyMiddleware('/webapi/rest/system_webapi/Session', {
    target: grunt.option('proxy'),
    headers: { origin: grunt.option('origin') },
    changeOrigin: true,
    secure: false
  }));
  middlewares.push(proxyMiddleware('/webapi/rest/partnerkontakt/Antrag', {
    target: grunt.option('proxy'),
    headers: { origin: grunt.option('origin') },
    changeOrigin: true,
    secure: false,
    onError: function (err, req, res) {
      res.writeHead(200, {
        'Content-Type': 'application/json'
      });
      res.end(JSON.stringify({
        GeneriereOfferteResponse: {
          offerteNummer: 'not-valid'
        }
      }));
    }
  }));

  options.base.forEach(function (base) {
    return middlewares.push(connect.static(base));
  });

  middlewares.push(function (req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', '*');
    return next();
  });
  return middlewares;
};

module.exports = function () {
  return {
    options: {
      port: 9000,
      hostname: '0.0.0.0',
      base: ['<%= config.tmp %>', '<%= config.app %>']
    },
    livereload: {
      options: {
        livereload: 35729,
        middleware: middleware
      }
    },
    dist: {
      options: {
        base: '<%= config.dist %>',
        livereload: false
      }
    },
    'e2e-dist': {
      options: {
        // Debugging:
        // 1. Set `debug: true` in this configuration.
        // 2. Use `browser.pause()` anywhere in the spec.
        base: '<%= config.dist %>',
        middleware: middleware,

        // Run on a different port in order to avoid conflicts
        // with development mode (e.g. 'grunt serve').
        port: 9007
      }
    },
    'e2e-dev': {
      options: {
        middleware: middleware,

        // Run on a different port in order to avoid conflicts
        // with development mode (e.g. 'grunt serve').
        port: 9007
      }
    },
    docs: {
      options: {
        base: 'docs',
        port: 9010
      }
    }
  };
};
