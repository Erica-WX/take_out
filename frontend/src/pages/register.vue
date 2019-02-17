<template>
    <div>

      <div class="top">
        <div style="padding-left: 40px">
          <i>Take Out</i> 注册
        </div>
      </div>
      <div>
        <el-tabs :tab-position="tabPosition" v-model="index">
          <el-tab-pane label="会员注册" name="1">
            <div class="member">
              <div class="tab_title">会员基本信息填写：</div>
              <el-form :label-position="member_form.labelPosition" ref="member_form" :model="member_form" label-width="80px">
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
                  <div style="margin-top: 20px">
                    <el-button type="primary" v-on:click="member_register">注册</el-button>
                    <el-button>清空</el-button>
                  </div>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>
          <el-tab-pane label="餐厅注册" name="2">
            <div class="member">
              <div class="tab_title">
                餐厅基本信息填写：
              </div>
              <el-form :label-position="rest_form.labelPosition" ref="rest_form" :model="rest_form" label-width="80px">
                <el-form-item label="餐厅名">
                  <el-input v-model="rest_form.rest_name"></el-input>
                </el-form-item>
                <el-form-item label="所在区县">
                  <el-select v-model="rest_form.district" style="width: 400px">
                    <el-option
                        v-for="item in rest_form.district_list"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="餐厅地址">
                  <el-input v-model="rest_form.address"></el-input>
                </el-form-item>
                <el-form-item label="餐厅类型">
                  <el-select v-model="rest_form.value" style="width: 400px">
                    <el-option
                        v-for="item in rest_form.type"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    >
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <div style="margin-top: 20px">
                    <el-button type="primary" v-on:click="rest_register">注册</el-button>
                    <el-button v-on:click=clear>清空</el-button>
                  </div>
                </el-form-item>
              </el-form>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
</template>

<script>
    import topBar from '../components/topBar'
    export default {
      name: "register",
      components: {topBar},
      mounted:function(){
        localStorage.type_list = JSON.stringify(this.rest_form.type);
        localStorage.district_list = JSON.stringify(this.rest_form.district_list);
        let index = this.$route.params.index;
        this.index = index;
      },
      data() {
        return {
          tabPosition: 'left',
          index: '1',
          member_form: {
            labelPosition:'top',
            username:'',
            email:'',
            password:''
          },
          rest_form:{
            labelPosition:'top',
            rest_name:'',
            address:'',
            type:[
              {
                value:'甜品饮品',
                label:'甜品饮品'
              },
              {
                value:'快餐便当',
                label:'快餐便当'
              },
              {
                value:'小吃夜宵',
                label:'小吃夜宵'
              },
              {
                value:'特色菜系',
                label:'特色菜系'
              }
            ],
            value:'',
            district:'',
            district_list:[
              {
                value:'玄武区',
                label:'玄武区'
              },
              {
                value:'秦淮区',
                label:'秦淮区'
              },
              {
                value:'鼓楼区',
                label:'鼓楼区'
              },
              {
                value:'建邺区',
                label:'建邺区'
              },
              {
                value:'雨花台区',
                label:'雨花台区',
              },
              {
                value:'栖霞区',
                label:'栖霞区'
              },
              {
                value:'浦口区',
                label:'浦口区'
              },
              {
                value:'六合区',
                label:'六合区'
              },
              {
                value:'江宁区',
                label:'江宁区'
              },
              {
                value:'溧水区',
                label:'溧水区'
              },
              {
                value:'高淳区',
                label:'高淳区'
              }
            ],
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
            username: username,
            password: password,
            email: email
          }).then(
            function (response) {
              console.log(response);
            }
          )
        },
        rest_register() {
          console.log(this.rest_form.value);

          let name = this.rest_form.rest_name;
          let district = this.rest_form.district;
          let address = this.rest_form.address;
          let type = this.rest_form.value;

          let self = this;
          this.$axios.post('/rest/register',{
            name: name,
            district:district,
            address: address,
            type: type
          }).then(
            function (response) {
              console.log(response.data);
              let id = response.data;
              alert("您餐厅的7位识别码是："+id+"\n 管理员信息审核完毕后即可使用该识别码登录。");
              self.$router.push({name:'restLogin'});
            }
          ).catch(function (error) {
            console.log(error);
          })
        },
        clear() {

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
    height: 600px;
    /*margin-top: 10px;*/
    margin-left: 30px;
    /*border: 1px solid black;*/
  }

  .tab_title{
    color: #b7b7b7;
    font-size: 20px;
    margin-bottom: 20px;
  }

</style>

<style>

  .el-form-item__label{
    color:black;
    font-size: 16px;
  }

  .el-tabs__item{
    font-size: 18px !important;
    padding-left: 50px;
    padding-right: 50px;
  }

</style>
