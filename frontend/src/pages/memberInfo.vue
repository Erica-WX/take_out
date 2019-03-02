<template>
    <memberNavi paneltitle="我的信息">
      <div class="member_info" v-show="!editable">
        <div class="info_item">
          用户名：{{this.member_info.username}}
        </div>
        <div class="info_item">
          邮箱：{{this.member_info.email}}
        </div>
        <div class="info_item">
          手机：{{this.member_info.phone}}
        </div>
        <div class="info_item"
             v-for="(add ,index) in member_info.addressList"
             :key="add.key"
        >
          地址{{(index + 1)}}：{{add.district}} {{add.address}}
        </div>
        <div class="info_item">
          当前等级：{{this.member_info.level}}
        </div>
        <div class="info_item">
          当前积分：{{this.member_info.score}}
        </div>
        <div style="margin-left: 20px; display: flex">
          <el-button type="info" plain v-on:click="edit" size="middle">修改信息</el-button>
          <el-button type="danger" plain="" v-on:click="deleteMember" size="middle">注销账号</el-button>
        </div>
      </div>
      <div v-show="editable" style="margin-bottom: 50px">
        <el-form class="member_form" ref="member_info" :model="member_info" :label-position="labelPosition" label-width="80px">
          <el-form-item label="用户名">
            <el-input class="input_box" v-model="member_info.username"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input class="input_box" v-model="member_info.email" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="手机">
            <el-input class="input_box" v-model="member_info.phone"></el-input>
          </el-form-item>
          <el-form-item
            v-for="(add ,index) in member_info.addressList"
            :label="'地址' + (index + 1)"
            :key="add.key"
          >
            <div style="display: flex">
              <el-select v-model="add.district" style="width: 120px">
                <el-option
                  v-for="item in district_list"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
              <el-input v-model="add.address" style="width: 300px"></el-input>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary"  v-on:click="submit">保存</el-button>
            <el-button v-on:click="add_address">新增地址</el-button>
          </el-form-item>
        </el-form>
      </div>
    </memberNavi>
</template>

<script>
    import memberNavi from '../components/memberNavi'
    export default {
      name: "member-info",
      components: {memberNavi},
      mounted: function () {
        this.district_list = JSON.parse(localStorage.district_list);
        this.get_info();
      },
      data() {
        return {
          labelPosition: "left",
          editable: false,
          member_info: {},
          district_list:[],
        }
      },
      methods: {

        edit() {
          this.editable = true;
        },

        deleteMember() {
          let email = localStorage.user_email;
          let self = this;
          this.$confirm('注销账户后该账户永不可用，是否注销账户？','提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(()=>{
            let self2 = self;
              self.$axios.get('/user/delete_member', {
                params: {
                  email: email,
                }
              }).then(
                function (response) {
                  self2.$message({
                    message: '注销成功！',
                    type: 'success'
                  });
                  setTimeout("window.location.href = 'http://localhost:3000/#/login'", 3000);

                }
              ).catch(function (error) {
                console.log(error);
              })
          }
          ).catch(

          )
        },

        submit() {
          this.editable = false;

          let username = this.member_info.username;
          let email = this.member_info.email;
          let phone = this.member_info.phone;
          let addressList = this.member_info.addressList;

          let self = this;
          this.$axios.post('/user/edit_info',{
            username: username,
            email: email,
            phone: phone,
            addressList: addressList
          }).then(
            function (response) {
              self.$alert('保存成功！', '', {
                confirmButtonText: '确定'
              })
          }).catch(function (error) {
              console.log(error);
          })

        },

        add_address(){
          this.member_info.addressList.push({
            id: -1,
            district: '',
            address: '',
          });
        },

        get_info() {
          let email = localStorage.user_email;
          let self = this;
          this.$axios.get('/user/get_info',{
            params: {
              email: email
            }
          }).then(
            function (response) {
              console.log(response.data);
              self.member_info = response.data;
            }
          ).catch(function (error) {
            console.log(error);
          })
        }
      }
    }
</script>

<style scoped>
  .member_info{
    margin-top: 50px;
    margin-left: 30px;
    margin-bottom: 50px;
  }

  .info_item{
    margin-bottom: 30px;
  }

  .member_form{
    /*width: 500px;*/
    margin-top: 40px;
    margin-left: 20px;
  }

  .input_box{
    width: 420px;
  }
</style>

<style>
  .el-form-item__label{
    color:black !important;
    font-size: 16px !important;
  }
</style>
