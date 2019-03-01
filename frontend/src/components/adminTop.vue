<template>
  <el-row>
    <el-col :span="6" class="c">
      <div class="logo">
        <div class="title"><i>Take-Out管理版</i></div>
      </div>
    </el-col>

    <el-col :span="18">
      <div>
        <el-menu :default-active="activeIndex"
                 class="el-menu-demo"
                 mode="horizontal"
                 @select="handleSelect"
                 background-color="#409EFF"
                 text-color="#fff"
                 active-text-color="#000000"

        >
          <el-menu-item index="1">个人中心</el-menu-item>
          <el-menu-item index="2">登录</el-menu-item>
          <el-menu-item index="3">注册</el-menu-item>

        </el-menu>
      </div>
    </el-col>
  </el-row>
</template>

<script>
    export default {
      name: "admin-top",
      data() {
        return {
          activeIndex: '1',
        }
      },
      methods: {
        handleSelect() {

        }
      }
    }
</script>

<style scoped>

  .logo{
    color: white;
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

</style>
