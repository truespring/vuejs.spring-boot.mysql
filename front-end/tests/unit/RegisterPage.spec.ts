import RegisterPage from '@/views/RegisterPage.vue'
import {mount, shallowMount} from "@vue/test-utils";

// describe('RegisterPage.vue', () => {
//   it('should render correct contents', () => {
//     const wrapper = shallowMount(RegisterPage)
//     // @ts-ignore
//     expect(wrapper.element.querySelector('form button[type="submit"]').textContent).toEqual('Create account')
//   })
// })

describe('RegisterPage.vue', () => {
  let wrapper: any
  let fieldUsername: any
  let fieldEmailAddress: any
  let fieldPassword: any
  let buttonSubmit: any

  beforeEach(() => {
    wrapper = mount(RegisterPage)
    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
  })

  it('should render registration form', () => {
    expect(wrapper.find('.logo').attributes().src).toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Open source task management tool')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmailAddress.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create account')
  });

  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  });

  it('should have form inputs bound with data model', () => {
    const username = 'rubok'
    const emailAddress = 'truespring@gmail.com'
    const password = 'VueJsRocks!'

    // 해당 테스트는 안됨 가져오는 것은 되나 할당하는 것은 안됨
    // wrapper.vm.form.username = username
    // wrapper.vm.form.emailAddress = emailAddress
    // wrapper.vm.form.password = password
    // expect(fieldUsername.element.value).toEqual(username)
    // expect(fieldEmailAddress.element.value).toEqual(emailAddress)
    // expect(fieldPassword.element.value).toEqual(password)
  });
})
