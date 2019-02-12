import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/index'
import login from '@/pages/login'
import register from '@/pages/register'
import selectAddress from '@/pages/selectAddress'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '',
      component: index
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/selectAddress',
      name: 'selectAddress',
      component: selectAddress
    }
  ]
})
