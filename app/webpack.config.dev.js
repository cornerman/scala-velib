var webpack = require('webpack');

module.exports = require('./scalajs.webpack.config');

const Path = require('path');
const rootDir = Path.resolve(__dirname, '../../../..');
module.exports.devServer = {
  contentBase: [
    Path.resolve(__dirname, 'dev'), // fastOptJS output
    Path.resolve(rootDir, 'assets') // project root containing index.html
  ],
  proxy: {
    '/velib': {
      target: 'https://velib-metropole-opendata.smoove.pro',
      pathRewrite: {'^/velib' : '/'},
      changeOrigin: true,
      logLevel: "debug",
      secure: false
    }
  },
  allowedHosts: [ ".localhost" ],
  watchContentBase: true,
  hot: false,
  hotOnly: false, // only reload when build is successful
  inline: true // live reloading
};