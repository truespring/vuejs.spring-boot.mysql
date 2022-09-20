// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })

module.exports = {
  outputDir: "../src/main/resources/static",
  indexPath: "../templates/index.html",
  devServer: {
    port: 3000,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  },
  // build: {
  //   index: path.resolve(__dirname, '../src/main/resources/static'),
  //   assetsRoot: path.resolve(__dirname, '../src/main/resources/static'),
  //   assetsSubDirectory: 'static',
  //   assetsPublicPath: '/',
  //
  //   productionSourceMap: true,
  //   devtool: '#source-map',
  //
  //   productionGzip: false,
  //   productionGzipExtensions: ['js', 'css'],
  //
  //   bundleAnalyzerReport: process.env.npm_config_repot
  // }
}

