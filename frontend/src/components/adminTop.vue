<template>
  <el-row>
    <el-col :span="6" class="c">
      <div class="logo">
        <div class="title"><i>Yummy管理版</i></div>
      </div>
    </el-col>

    <el-col :span="18">
      <div style="height: 60px; background-color: #409EFF">

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
    color: white;
  }

</style>
