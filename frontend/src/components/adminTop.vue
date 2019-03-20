<template>
  <el-row>
    <el-col :span="6" class="c">
      <div class="logo">
        <div class="title"><i>Yummy管理版</i></div>
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
                 :router="routerBool"

        >
          <el-menu-item index="/adminLogin">登出</el-menu-item>

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
          activeIndex: '',
          routerBool:true
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
    color: white;
  }

</style>
