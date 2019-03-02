<template>
  <el-row>
    <el-col :span="4" class="c">
      <div class="logo">
        <div class="title"><i>Yummy</i></div>
      </div>
    </el-col>

    <el-col :span="20">
      <div>
        <el-menu :default-active="activeIndex"
                 class="el-menu-demo"
                 mode="horizontal"
                 @select="handleSelect"
                 background-color="#409EFF"
                 text-color="#fff"
                 active-text-color="#000000"
                 :router="routerBool"

        >
          <el-menu-item index="/login">登出</el-menu-item>
          <!--<el-menu-item index="2">登录</el-menu-item>
          <el-menu-item index="3">注册</el-menu-item>-->

        </el-menu>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: "member-top",
    data() {
      return {
        activeIndex: '',
        routerBool:true
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key,keyPath);
        if(key === "/login") {
          this.logout();
        }
      },

      logout() {
        localStorage.user_email = "";
        localStorage.address = "";
        localStorage.district = "";
        localStorage.username = "";
      }
    },
  }
</script>

<style scoped>

  .c{
    background-color: #409EFF;
    border-bottom:solid 1px #409EFF;
  }
  .logo{
    background-color: #409EFF;
    text-align: center;
    /*font-size: 18px;*/
    height: 60px;
    line-height: 60px;
    margin: auto;
  }

  .title{
    font-size: 30px;
  }

  .title:hover{
    cursor: pointer;
  }

</style>

<style>
  .el-menu.el-menu--horizontal{
    border-bottom:solid 1px #409EFF
  }
</style>
