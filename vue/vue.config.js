module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  devServer: {
    proxy: {
      '/api': {
        target: `http://unmask.hrithviksood.me`,
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api' : ''
        }
      }
    }
}
}