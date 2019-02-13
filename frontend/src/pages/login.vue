<template>
  <div>
    <topBar></topBar>
    <div class="back">
      <div class="main">
        <div>
          <h1 class="title">登 录</h1>
        </div>
        <div class="login">
          <el-form ref="login_form" :model="login_form" label-width="80px">
            <el-form-item label="用户邮箱">
              <el-input v-model="login_form.email"></el-input>
            </el-form-item>
            <el-form-item label="用户密码">
              <el-input type="password" v-model="login_form.password"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div>
          <p> <!--<router-link to="/register"><i> >>>没有账号，去注册</i>></router-link>--><a v-on:click="go"><i> >>>没有账号，去注册</i></a></p>
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
      name: "login",
      components: {topBar},
      data() {
        return {
          login_form:{
            email:'',
            password:''
          }
        }
      },
      methods: {
        login() {
          let email = this.login_form.email;
          let password = this.login_form.password;
          let self = this;
          console.log("login");
          this.$axios.get('/user/login',{
            params:{
              email:email,
              password:password
            }
          }).then(
            function(response) {
              console.log(response.data);
              if(response.data !== "") {
                localStorage.user_email = email;
                self.$router.push({name:'selectAddress',params:{username:response.data}});
              }else {
                alert("用户名或者密码错误！");
              }
            }
          ).catch(function(error){

          })
        },
        go(){
          window.location.href = "http://localhost:3000/#/register";
        },
      }
    }
</script>

<style scoped>
  .back{
    height: 700px;
    background-image: url("/static/index-back.jpg");
    background-size:100% 100%;
    border: 1px solid black;
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
    color:white;
    /*font-size: 16px;*/
  }
  /*.el-form-item__label{
    color:white;
    font-size: 16px;
  }

  .el-input{
    font-size: 18px;
    line-height: 20px;
  }*/
</style>
