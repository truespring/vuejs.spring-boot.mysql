export default {
  register (detail) {
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'truespring@gmail.com'
        ? resolve({ result: 'success' })
        : reject(new Error('User already exist'))
    })
  }
}
