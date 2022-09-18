// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide

// module.exports = {
//   'default e2e tests': browser => {
//     browser
//       .init()
//       .waitForElementVisible('#app')
//       .assert.elementPresent('.hello')
//       .assert.containsText('h1', 'Welcome to Your Vue.js + TypeScript App')
//       .assert.elementCount('img', 1)
//       .end()
//   },
//
//   'example e2e test using a custom command': browser => {
//     browser
//       .openHomepage()
//       .assert.elementPresent('.hello')
//       .end()
//   }
// }

module.exports = {
  'login test': function (browser) {
    browser
      .url(process.env.VUE_DEV_SERVER_URL + 'login')
      .waitForElementVisible('#app', 5000)
      .assert.containsText('h1', 'TaskAgile')
      .end()
  }
}
