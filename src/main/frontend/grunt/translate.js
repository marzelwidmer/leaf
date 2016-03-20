/*
(function () {
  var fs = require('fs'),
      path = require('path'),
      grunt = require('grunt'),
      PATH = 'app/locales',
      TSV = PATH + '/translations.tsv',
      LANGUAGES = [],
      TRANSLATIONS = [];

  // detect available languages
  fs.readdirSync(PATH).forEach(function (file) {
    var match = file.match(/^locale_([a-z]{2})\.json$/);
    if (match && match[1] !== 'zz') {
      LANGUAGES.push(match[1]);
    }
  });

  grunt.task.registerTask('translate:import', 'Import translations from ' + TSV + ' to JSON', function () {
    var lines = fs.readFileSync(TSV, { encoding: 'UTF-8' })
      .trim()
      .split(/\r?\n\r?/);

    // parse all translations
    for (var index = 1; index < lines.length; ) {
      var line = '',
          translation = {},
          parts;

      // keep reading lines until we have an even number of quotes
      do {
        line = line + ' ' + lines[index++].toString();
        parts = line.split('\t');
      } while (!line || (line.match(/^"|[^\\]"/g) || []).length % 2 === 1);

      // verify the number of tabs
      if (parts.length > LANGUAGES.length + 1) {
        grunt.log.error(
          '  ' + path.basename(TSV) + ': invalid format on line ' + (index + 1) +
          ', expected ' + (LANGUAGES.length + 1) + ' columns but got ' + (parts.length + 1)
        );

        process.exit(1);
      }

      // the first column contains the translation key + punctuation
      translation.key = parts[0];

      if (translation.key === '') {
        break;
      } else if (translation.key.match(/:$/)) {
        // collect translated strings in following columns
        for (var column = 0; column < LANGUAGES.length; column++) {
          var string = (parts[column + 1] || '')
            .replace(/\n+/g, ' ')
            .replace(/^"|"$/g, '')
            .replace(/\\*"+/g, '\\"');

          translation[LANGUAGES[column]] = string;

          if (index > 1413 && index < 1423) {
            console.log('language: ' + LANGUAGES[column]);
            console.log('before: ' + parts[column + 1]);
            console.log('after: ' + translation[LANGUAGES[column]]);
          }
        }
      }

      TRANSLATIONS.push(translation);
    }

    // write JSON file for each language
    LANGUAGES.forEach(function (language) {
      var output = '';

      TRANSLATIONS.forEach(function (translation) {
        output += translation.key;
        if (translation.hasOwnProperty(language)) {
          output += ' "' + (translation[language] || '').replace(/\\*"/g, '\\"') + '",';
        }
        output += '\n';
      });

      // remove commas before closing braces
      output = output.replace(/,(\s*)\}/g, '$1}');

      fs.writeFileSync(PATH + '/locale_' + language + '.json', output);
    });
  });

  grunt.task.registerTask('translate:export', 'Export translations from JSON to ' + TSV, function () {
    var output = [];

    LANGUAGES.forEach(function (language) {
      var stats = { translated: 0, missing: 0 },
          lines = fs.readFileSync(PATH + '/locale_' + language + '.json', { encoding: 'UTF-8' })
                    .trim()
                    .split(/\r?\n\r?/);

      // verify number of lines matches the first file
      if (TRANSLATIONS.length > 0 && lines.length !== TRANSLATIONS.length) {
        grunt.log.error(
          '  ' + language + ': conflicting file length, expected ' + TRANSLATIONS.length + ' lines but got ' + lines.length
        );

        process.exit(1);
      }

      // load all translations for this language
      lines.forEach(function (line, index) {
        var translation = TRANSLATIONS[index] || {},
            match = line.match(/^([^:]+\s*:)\s*"(.*)"\s*,?\s*$/);

        if (!match) {
          // store key without translation
          translation.key = line;
        } else if (translation.key && translation.key !== match[1]) {
          // detect conflicting keys
          grunt.log.error(
            '  ' + language + ': conflicting key on line ' + (index + 1) + ', expected ' + translation.key.trim() + ' but got ' + match[1].trim()
          );

          process.exit(1);

        } else {
          // store key with translation
          translation.key = match[1];
          translation[language] = match[2];

          // count translated/missing strings
          if (match[2].match(/[^\s]/)) {
            stats.translated++;
          } else {
            stats.missing++;
          }
        }

        TRANSLATIONS[index] = translation;
      });

      grunt.log.ok('  ' + language + ': ' + stats.translated + ' translations, ' + stats.missing + ' missing');
    });

    // output the combined translations
    output += [ 'Key' ].concat(LANGUAGES).join('\t') + '\n';

    TRANSLATIONS.forEach(function (translation) {
      var keyTranslations = LANGUAGES.map(function (language) {
        return translation[language] || '';
      });

      output += [ translation.key ].concat(keyTranslations).join('\t') + '\n';
    });

    fs.writeFileSync(TSV, output);
  });

})();
*/
