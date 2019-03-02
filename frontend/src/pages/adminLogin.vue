<template>
  <div>
    <topBar></topBar>
    <div class="back">
      <div class="main">
        <div>
          <h1 class="title">管理员登录</h1>
        </div>
        <div class="login">
          <el-form ref="login_form" :model="login_form" label-width="80px">
            <el-form-item label="用户名">
              <el-input v-model="login_form.adminName"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input type="password" v-model="login_form.password"></el-input>
            </el-form-item>
          </el-form>
        </div>

        <div class="enter">
          <el-button v-on:click="login">登录</el-button>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
    import topBar from '../components/topBar'
    export default {
      name: "admin-login",
      components: {topBar},
      data() {
        return {
          login_form: {
            adminName: '',
            password: ''
          }
        }
      },
      methods: {
        login() {
          let adminName = this.login_form.adminName;
          let password = this.login_form.password;

          let self = this;

          this.$axios.get('/admin/login', {
            params: {
              adminName: adminName,
              password: password
            }
          }).then(
            function (response) {
              let is_correct = response.data;
              if(!is_correct) {
                self.$alert('用户名或密码错误！', '', {
                  confirmButtonText: '确定',
                  callback: action => {
                    window.location.reload();
                  }
                })
              }else {
                self.$router.push({name: 'adminPage'})
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        }
      }
    }
</script>

<style scoped>

  .back{
    height: 700px;
    background-image: url("/static/index-back.jpg");
    background-size:100% 100%;
    border: 1px solid white;
    /*opacity: 0.35;*/
  }

  .main{
    margin-top: 10%;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    width: 50%;
    height: 45%;
    border: 1px solid #ce9b5f;
    background-color: dodgerblue;
    opacity: 0.8;
  }

  .title{
    margin-top: 4%;
    margin-left: 3%;
    color: white;
  }

  .login{
    width:400px;
    margin-left: auto;
    margin-right: auto;
  }

  .enter{
    margin-top: 10px;
  }

</style>

<style>
  .el-form-item__label{
    color:white !important;
    font-size: 16px !important;
  }
</style>
