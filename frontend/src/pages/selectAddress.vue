<template>
  <div class="back">
    <memberNavi></memberNavi>
    <div class="welcome">
      <div class="title">
        {{this.username}}, 欢迎前来点单~
      </div>
      <el-form ref="address_form" :model="address_form" label-width="100px" v-show="!needAdd">
        <el-form-item label="收货地址">
          <el-select v-model="address_form.value" placeholder="请选择你的收货地址" style="width: 380px">
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
            <el-input v-model="address_form.address"></el-input><el-button v-on:click="addAddress">搜索</el-button>
          </div>

        </el-form-item>
        <el-form-item>
          没有地址，<a class="add" v-on:click="addLine">新增一个</a>
        </el-form-item>

      </el-form>

      <el-form ref="new_form" :model="new_form" label-width="100px" v-show="needAdd">
        <el-form-item label="选择区县">
          <el-select v-model="new_form.district" style="width: 380px">
            <el-option
                v-for="item in new_form.district_list"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="详细地址">
          <el-input v-model="new_form.address"></el-input>
        </el-form-item>

        <div style="margin-left: 50%;margin-top: 40px">
          <el-button v-on:click="addAddress">搜索</el-button>
        </div>
      </el-form>
    </div>
    <div></div>
  </div>
</template>

<script>
  import memberNavi from '../components/memberTop'
  export default {
    name: "select-address",
    components: {memberNavi},
    mounted:function() {

      this.username = localStorage.username;

      let email = localStorage.user_email;
      let self = this;
      this.$axios.get('/user/get_address',{
        params:{
          email:email
        }
      }).then(
        function (response) {
          console.log(response.data);
          let list = response.data;
          let address_list = [];
          for(let i = 0; i < list.length; i++) {
            let a = {
              value: list[i].aid,
              label: list[i].district+ " " + list[i].address,
            };
            address_list.push(a);
          }
          self.address_form.address_list = address_list;
        }
      ).catch(function (error) {
        console.log(error)
      })
    },
    data() {
      return {
        username:'',
        needAdd:false,

        address_form:{
          address_list:[],
          value:''
        },
        new_form:{
          district:'',
          district_list:[],
          address:''
        }
      }
    },
    watch: {
      'address_form.value':{
        handler(){
          this.split_address();
        }
      },
      deep:true,
    },
    methods: {
      addLine(){
        console.log("add address");
        this.needAdd = true;
        this.new_form.district_list = JSON.parse(localStorage.district_list);
      },
      addAddress(){
        let email = localStorage.user_email;
        let district = this.new_form.district;
        let address = this.new_form.address;

        let self = this;
        this.$axios.get("/user/new_address",{
          params:{
            email:email,
            district:district,
            address:address
          }
        }).then(
          function (response) {
            self.search(district, address);
          }
        ).catch(function (error) {

        });
      },
      search(district, address) {
        localStorage.district = district;
        localStorage.address = address;
        this.$router.push({name:'foodList'})
      },

      split_address(){

        let aid = this.address_form.value;
        localStorage.aid = aid;
        let self = this;
        this.$axios.get('/user/get_the_address', {
          params: {
            aid: aid
          }
        }).then(
          function (response) {
            let info = response.data;
            let district = info.district;
            let address = info.address;
            self.search(district, address);
          }
        ).catch(function (error) {
          console.log(error);
        });

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
    color:white !important;
    font-size: 20px;
  }

</style>
