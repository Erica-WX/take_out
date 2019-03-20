<template>
  <el-row>
    <el-col :span="6" class="c">
      <div class="logo">
        <div class="title"><i>Yummy餐厅版</i></div>
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
          <el-menu-item index="/restLogin">登出</el-menu-item>

        </el-menu>
      </div>
    </el-col>
  </el-row>
</template>

<script>
  export default {
    name: "rest-top",
    data() {
      return {
        activeIndex: '',
        routerBool:true
      }
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key,keyPath);
        if(key === "/restLogin") {
          this.logout();
        }
      },

      logout() {
        localStorage.rest_id = "";
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
