<template>
    <restNavi paneltitle="我的餐厅 > 基本信息">
      <div class="rest_info" v-show="!editable">
        <div class="info_item">
          餐厅名：{{this.rest_info.name}}
        </div>
        <div class="info_item">
          餐厅识别码：{{this.rest_info.id}}
        </div>
        <div class="info_item">
          所在区县：{{this.rest_info.district}}
        </div>
        <div class="info_item">
          餐厅地址：{{this.rest_info.address}}
        </div>
        <div class="info_item">
          餐厅类型：{{this.rest_info.type}}
        </div>
        <div style="margin-left: 25px">
          <el-button type="info" plain v-on:click="edit">修改信息</el-button>
        </div>
      </div>
      <div class="rest_form" v-show="editable">
        <el-form :label-position="labelPosition" ref="this.rest_form" :model="rest_form" label-width="100px">
          <el-form-item label="餐厅名">
            <el-input v-model="rest_form.name"></el-input>
          </el-form-item>
          <el-form-item label="餐厅识别码">
            <el-input v-model="rest_form.id" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="所在区县" >
            <el-select v-model="rest_form.district" style="width: 300px">
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
          <el-form-item label="餐厅类别" >
            <el-select v-model="rest_form.type" style="width: 300px">
              <el-option
                  v-for="item in rest_form.type_list"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <div style="margin-top: 40px;margin-left: 43%">
            <el-button type="primary" v-on:click="submit">保存</el-button>
          </div>
        </el-form>
      </div>
    </restNavi>
</template>

<script>

    import restNavi from '../components/restNavi'
    export default {
      name: "rest-page",
      components:{restNavi},
      mounted:function () {
        this.get_info();
      },
      data() {
        return {
          editable: false,
          labelPosition:'left',
          rest_info: {
            id:'12345',
            name:'宽窄巷子',
            district:'鼓楼区',
            address:'南京市鼓楼区湖北路店',
            type:'特色菜系'
          },
          rest_form: {
            id:'',
            name:'',
            district:'',
            district_list:[],
            address:'',
            type:'',
            type_list:[],
          }
        }
      },
      methods: {

        get_info() {

          let id = localStorage.rest_id;
          let self = this;
          this.$axios.get('/rest/login', {
            params: {
              id: id
            }
          }).then(
            function (response) {
              self.rest_info = response.data;
            }
          )

        },
        edit(){
          this.editable = true;

          let obj = {
            name: this.rest_info.name,
            id: this.rest_info.id,
            district: this.rest_info.district,
            address: this.rest_info.address,
            type: this.rest_info.type,
            district_list: [],
            type_list: []
          };
          this.rest_form = obj;
          this.rest_form.district_list = JSON.parse(localStorage.district_list);
          this.rest_form.type_list = JSON.parse(localStorage.type_list);
        },
        submit(){
          this.editable = false;
          let id = localStorage.rest_id;
          let name = this.rest_form.name;
          let district = this.rest_form.district;
          let address = this.rest_form.address;
          let type = this.rest_form.type;

          let self = this;
          this.$axios.post('/rest/save_info',{
            id: id,
            name: name,
            district: district,
            address: address,
            type: type
          }).then(
            function (response) {

              self.$alert('信息待管理员审核完毕后呈现！','', {
                confirmButtonText: '确定',
                callback: action => {

                }
              });

            }
          ).catch(function (error) {
            console.log(error);
          })
        }
      }
    }
</script>

<style scoped>

  .rest_info{
    margin-top: 50px;
    margin-left: 30px;
  }

  .info_item{
    margin-bottom: 30px;
  }

  .rest_form{
    width: 400px;
    margin-top: 40px;
    margin-left: 20px;
  }
</style>

<style>
  .el-form-item__label{
    color: black;
  }
</style>
