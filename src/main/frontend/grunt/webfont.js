module.exports = {
  icons: {
    src:     '<%= config.app %>/icons/*.svg',
    dest:    '<%= config.app %>/styles/fonts',
    destCss: '<%= config.app %>/styles/global',

    options: {
      engine:     'fontforge',
      hashes:     false,
      normalize:  false,
      ie7:        false,
      font:       'ccb_icons',
      types:      'eot,woff,ttf,svg',
      stylesheet: 'scss',
      htmlDemo:   true,
      relativeFontPath: 'fonts',
      templateOptions: {
        baseClass: 'icon',
        classPrefix: 'icon--',
        mixinPrefix: 'icon-'
      }
    }
  }
};
