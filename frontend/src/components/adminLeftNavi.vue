<template>
  <div>
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      :router="routerBool">
      <el-menu-item index="/adminPage">
        <i class="el-icon-location-outline bar-icon"></i>
        <span slot="title">审核信息</span>
      </el-menu-item>
      <el-menu-item index="/memberInfo" class="bar-main">
        <i class="el-icon-menu bar-icon"></i>
        <span slot="title">我的信息</span>
      </el-menu-item>
      <el-menu-item index="/order" class="bar-main">
        <i class="el-icon-menu bar-icon"></i>
        <span slot="title">我的订单</span>
      </el-menu-item>
      <el-menu-item index="/memberStatistics" class="bar-main">
        <i class="el-icon-location bar-icon"></i>
        <span slot="title">统计信息</span>
      </el-menu-item>
      <!--<el-submenu index="4" class="bar-main">
        <template slot="title">
          <i class="el-icon-location bar-icon"></i>
          <span>统计信息查询</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/UserSpace/NormalCheck">点餐信息</el-menu-item>
          <el-menu-item index="/UserSpace/AdvancedCheck">退订信息</el-menu-item>
          <el-menu-item index="/UserSpace/CrossCheck">财务信息</el-menu-item>
        </el-menu-item-group>
      </el-submenu>-->
    </el-menu>
  </div>

</template>

<script>
    export default {
      name: "admin-left-navi",
      data(){
        return{
          routerBool:true
        }
      },
    }
</script>

<style scoped>

  .bar-icon{
    color: dodgerblue;
  }

  .bar-main{
    /*background-color:ghostwhite;*/
  }

  div.leftsidenav{
    background:white;
    border:1px solid #e4e4e4;
    border-top:5px solid dodgerblue;
    height:650px;
    margin-right: 10%;
    margin-left: 16%;
    box-shadow:
      0 1px 6px 0 rgba(0,0,0, .12),
      0 1px 6px 0 rgba(0,0,0, .12);
    border-radius: 3px;

  }
  /*----------左侧导航栏修正----------*/
  .left_menus ul{
    list-style: none;
  }

  .left_head {
    font-size: 16px;
    color: #48576a;
    text-indent: 8%;
    margin-top: -3%;
    margin-bottom: -3%;
  }

  .left_ul li{
    margin: 3%;
    padding: 0;
    min-width: 80px;
    font-size: 15px;
  }

  .left_ul li a{
    color: #777777;
    text-decoration:none;
  }

  .left_ul li a:hover{
    color: dodgerblue;
  }

  .left_ul li a.active{
    color: dodgerblue;
  }


  #leftnav li{
    margin: 0;
    padding: 0;
    min-width: 80px;
    text-align: center;
    background-color: white;
  }

  #leftnav li a{
    color: black;
  }

  #leftnav li.active a{
    color: white;
    background-color: black;
  }

  #leftnav li a:hover{
    background-color: black;
  }

  #leftnav li:hover a{
    color:white;
  }

  #left_sub_nav li{
    margin: 0;
    padding: 0;
    min-width: 80px;
    text-align: center;
    background-color: snow;
  }

  #left_sub_nav li a{
    color: black;
  }

  #left_sub_nav .active a{
    color: darkviolet;
  }

  #left_sub_nav li a:hover{
    color:darkviolet;
  }

</style>
