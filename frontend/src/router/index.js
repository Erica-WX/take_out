import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/index'
import login from '@/pages/login'
import memberInfo from '@/pages/memberInfo'
import restLogin from '@/pages/restLogin'
import register from '@/pages/register'
import selectAddress from '@/pages/selectAddress'
import foodList from '@/pages/foodList'
import restInfo from '@/pages/restInfo'
import basket from '@/pages/basket'
import restPage from '@/pages/restPage'
import launchNewFood from '@/pages/launchNewFood'
import launchSetMeal from '@/pages/launchSetMeal'
import launchDiscount from '@/pages/launchDiscount'

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
      path: '/memberInfo',
      name: 'memberInfo',
      component: memberInfo
    },
    {
      path: '/restLogin',
      name: 'restLogin',
      component: restLogin
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
    },
    {
      path: '/foodList',
      name: 'foodList',
      component: foodList
    },
    {
      path: '/restInfo',
      name: 'restInfo',
      component: restInfo
    },
    {
      path: '/basket',
      name: 'basket',
      component: basket
    },
    {
      path: '/restPage',
      name: 'restPage',
      component: restPage
    },
    {
      path: '/launchNewFood',
      name: 'launchNewFood',
      component: launchNewFood
    },
    {
      path: '/launchSetMeal',
      name: 'launchSetMeal',
      component: launchSetMeal
    },
    {
      path: '/launchDiscount',
      name: 'launchDiscount',
      component: launchDiscount
    }
  ]
})
