import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/index'
import register from '@/pages/register'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '',
      component: index
    },
    {
      path: '/register',
      name: 'register',
      component: register
    }
  ]
})
