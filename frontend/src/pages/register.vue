<template>
    <div>

      <div class="top">
        <div style="padding-left: 40px">
          <i>Take Out</i> 注册
        </div>
      </div>
      <div>
        <el-tabs :tab-position="tabPosition">
          <el-tab-pane label="会员注册">
            <div class="member">
              <div class="tab_title">会员基本信息填写：</div>
              <el-form :label-position="member_form.labelPosition" ref="form" :model="member_form" label-width="80px">
                <el-form-item label="会员名">
                  <el-input v-model="member_form.username"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                  <el-input v-model="member_form.email"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input type="password" v-model="member_form.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                  <div style="margin-left: 120px;margin-top: 20px">
                    <el-button type="primary" v-on:click="member_register">注册</el-button>
                    <el-button>清空</el-button>
                  </div>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>
          <el-tab-pane label="餐厅注册">餐厅注册</el-tab-pane>
        </el-tabs>
      </div>
    </div>
</template>

<script>
    import topBar from '../components/topBar'
    export default {
      name: "register",
      components: {topBar},

      data() {
        return {
          tabPosition: 'left',
          member_form: {
            labelPosition:'top',
            username:'',
            email:'',
            password:''
          },
        }
      },

      methods: {
        member_register() {
          let a = "a";
          let username = this.member_form.username;
          let email = this.member_form.email;
          let password = this.member_form.password;
          console.log(username, email, password);

          this.$axios.post("/user/register",{
            username:username,
            password:password,
            email:email
          }).then(
            function (response) {
              console.log(response);
            }
          )
        }
      }

    }
</script>

<style scoped>

  .top{
    color: white;
    font-size: 50px;
    width: 100%;
    height: 200px;
    line-height: 200px;
    background-color: #409EFF;
    margin-bottom: 30px;
    /*border: 1px solid black;*/
    /*background-image: url("/static/top-back.jpg");*/
   /* background-size: 100% 100%;*/
  }

  .member{
    width: 400px;
    height: 500px;
    /*margin-top: 10px;*/
    margin-left: 30px;
    /*border: 1px solid black;*/
  }

  .tab_title{
    color: #b7b7b7;
    font-size: 20px;
  }

</style>

<style>

  .el-tabs__item{
    font-size: 18px !important;
    padding-left: 50px;
    padding-right: 50px;
  }
</style>
