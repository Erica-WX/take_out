<template>
  <div class="back">
    <topBar></topBar>
    <div class="welcome">
      <div class="title">
        {{this.username}}, 欢迎前来点单~
      </div>
      <el-form ref="address_form" :model="address_form" label-width="100px">
        <el-form-item label="收货地址">
          <el-select v-model="address_form.value" placeholder="请选择你的收货地址" >
            <el-option
              v-for="item in address_form.address_list"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item v-show="needAdd" label="新增地址">

          <div style="display: flex">
            <el-input v-model="address_form.new_address"></el-input><el-button>搜索</el-button>
          </div>

        </el-form-item>
        <el-form-item v-show="!needAdd">
          没有地址，<a class="add" v-on:click="add">新增一个</a>
        </el-form-item>

      </el-form>
    </div>
    <div></div>
  </div>
</template>

<script>
  import topBar from '../components/topBar'
  export default {
    name: "select-address",
    components: {topBar},
    mounted:function() {
      /*console.log(this.$route.params.username);
      this.username = this.$route.params.username;*/
    },
    data() {
      return {
        username:'Erica',
        needAdd:false,
        address_form:{
          address_list:[
            {
              value:'1',
              label:'address1'
            },{
              value:'2',
              label:'address2'
            },
            {
              value:'3',
              label:'address3'
            },
            {
              value:'4',
              label:'address4'
            },
          ],
          value:'',
          new_address:''
        }
      }
    },
    methods:{
      add(){
        console.log("add address");
        this.needAdd = true;
      }
    }
  }
</script>

<style scoped>
  .back{
    width: 100%;
    height: 700px;
    background-color: #409EFF;
  }

  .welcome{
    font-size: 25px;
    color: white;
    margin-left: auto;
    margin-right: auto;
    margin-top: 8%;
    width: 480px;

  }

  .title{
    margin-left: 11px;
    margin-bottom: 40px;
  }

  .add:hover{
    cursor:pointer;
  }


</style>

<style>

  .el-form-item__label{
    color:white;
    font-size: 20px;
  }

  .el-input{
    font-size: 21px;
    line-height: 22px;
  }
</style>
