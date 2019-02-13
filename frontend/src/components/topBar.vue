<template>
  <el-row>
    <el-col :span="4" >
      <div class="logo">
        <div class="title"><i>Take-Out</i></div>
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

        >
          <el-menu-item index="1">外卖</el-menu-item>
          <el-menu-item index="2">登录</el-menu-item>
          <el-menu-item index="3">注册</el-menu-item>

        </el-menu>
      </div>
    </el-col>
  </el-row>

</template>

<script>
    export default {
      name: "top-bar",
      data() {
        return {
          activeIndex: '1',
        }
      },
      methods: {
        handleSelect(key, keyPath) {
          console.log(key, keyPath);
        }
      },
    }
</script>

<style scoped>

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

  .c{
    color: #409EFF;
  }

</style>

<style>

  /*.el-menu.el-menu--horizontal{border-bottom:solid 1px #409EFF}*/
</style>
