// import { shallowMount } from '@vue/test-utils'
// import HelloWorld from '@/components/HelloWorld.vue'
//
// describe('HelloWorld.vue', () => {
//   it('renders props.msg when passed', () => {
//     const msg = 'new message'
//     const wrapper = shallowMount(HelloWorld, {
//       props: { msg }
//     })
//     expect(wrapper.text()).toMatch(msg)
//   })
// })
import { shallowMount } from '@vue/test-utils'
import LoginPage from '@/views/LoginPage.vue'

describe('LoginPage.vue', () => {
  it('should render correct contents', () => {
    const content = 'TaskAgile'
    const wrapper = shallowMount(LoginPage, {
      props: { content }
    })
    // @ts-ignore
    expect(wrapper.element.querySelector('h1').textContent).toEqual(content)
  })
})
