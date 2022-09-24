// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })

module.exports = {
  outputDir: '../src/main/resources/static',
  indexPath: '../templates/index.html',
  devServer: {
    port: 3000,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  },
  configureWebpack: {
    entry: {
      app: './src/main.ts',
      style: [
        'bootstrap/dist/css/bootstrap.min.css'
      ]
    }
  }
}
