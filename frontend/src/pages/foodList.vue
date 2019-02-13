<template>
    <div>
      <memberNavi></memberNavi>
      <div class="main-body">
        <div class="address">
          当前位置：{{now_address}} <router-link class="change" to="/selectAddress">[切换地址]</router-link>
        </div>
        <div></div>
      </div>
    </div>
</template>

<script>

    import memberNavi from '../components/memberNavi'
    export default {
      name: "food-list",
      components: {memberNavi},
      data() {
        return {
          now_address:'南京市-鼓楼区-南京大学鼓楼校区',
        }
      },
      methods:{
        /*change_address(){
          this.$router.push
        }*/
      }
    }
</script>

<style scoped>
  .main-body{
    width: 100%;
    height: 700px;
    background-color: #f0f0f0;
    padding-left: 60px;
  }

  .address{
    color: #969696;
    font-size: 14px;
    height: 50px;
    line-height: 50px;
  }

  .change{
    color: #409EFF;
    cursor: pointer;
  }

</style>

<style>
  /*.el-menu.el-menu--horizontal{
    border-bottom:solid 1px #409EFF
  }*/
</style>
