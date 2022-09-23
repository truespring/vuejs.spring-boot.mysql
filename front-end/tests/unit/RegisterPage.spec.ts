import RegisterPage from '@/views/RegisterPage.vue'
import {mount, shallowMount} from "@vue/test-utils";

jest.mock('@/services/registration')
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
  const mockRouter = {
    push: jest.fn()
  }

  beforeEach(() => {
    wrapper = mount(RegisterPage, {
      global: {
        mocks: {
          $router: mockRouter,
        }
      }
    })
    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
  })

  afterAll(() => {
    jest.restoreAllMocks()
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

  it('should have form inputs bound with data model', async () => {
    const username = 'rubok'
    const emailAddress = 'truespring@gmail.com'
    const password = 'VueJsRocks!'

    await fieldUsername.setValue(username)
    await fieldEmailAddress.setValue(emailAddress)
    await fieldPassword.setValue(password)

    expect(wrapper.vm.form.username).toEqual(username)
    expect(wrapper.vm.form.emailAddress).toEqual(emailAddress)
    expect(wrapper.vm.form.password).toEqual(password)
  });

  it('should have form submit event handler `submitForm`', async () => {
    const spyFn = jest.spyOn(wrapper.vm, 'submitForm')

    await buttonSubmit.trigger('submit')
    expect(spyFn).toBeCalled()
  });

  it('should register when it is a new user', async () => {
    wrapper.vm.form.username = 'rubok'
    wrapper.vm.form.emailAddress = 'truespring@gmail.com'
    wrapper.vm.form.password = 'VueJsRocks!'
    await buttonSubmit.trigger('submit')
    expect(mockRouter.push).toHaveBeenCalledWith({ name: 'LoginPage' })
  });

  it('should fail it is not a new user', async () => {
    wrapper.vm.form.emailAddress = 'truespring1@gmail.com'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    await buttonSubmit.trigger('submit')
    expect(wrapper.find('.failed').isVisible()).toBe(true)
  });
})
