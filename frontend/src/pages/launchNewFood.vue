<template>
  <restNavi paneltitle="发布信息 > 发布新品">
    <div class="main-body">
      <el-form ref="food_info" :model="food_info" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="food_info.name" placeholder="请输入商品名称"></el-input>
        </el-form-item>
        <el-form-item label="商品类型">
          <el-select style="width: 360px"
            v-model="food_info.type"
            filterable
            allow-create
            default-first-option
            placeholder="请选择商品类型">
            <el-option
              v-for="item in food_info.type_list"
              :key="item.value"
              :label="item.label"
              :value="item.label">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品单价">
          <el-input v-model="food_info.price"></el-input>
        </el-form-item>
        <el-form-item label="商品数量">
          <el-input v-model="food_info.num"></el-input>
        </el-form-item>
        <el-form-item label="在售时段">
          <div style="display: flex">
            <el-date-picker
              v-model="date1"
              type="date"
              placeholder="选择开始日期"
              
              >
            </el-date-picker>
            <div>-</div>
            <el-date-picker
              v-model="date2"
              type="date"
              placeholder="选择结束日期"
              >
            </el-date-picker>
          </div>

        </el-form-item>
        <el-form-item label="商品图片">
          <el-upload
            class="upload-demo"
            drag
            action="http://localhost:8000/upload/image"
            :onSuccess="uploadSuccess"
            multiple
            >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="launch">发布</el-button>
        </el-form-item>
      </el-form>
    </div>
  </restNavi>
</template>

<script>
    import restNavi from '../components/restNavi'
    export default {
      name: "launch-new-food",
      components:{restNavi},
      mounted: function() {
        
        let restId = localStorage.rest_id;
        let self = this;
        console.log("restId:" + restId);
        this.$axios.get("/rest/is_approved", {
          params: {
            restId: restId
          }
        }).then(
          function(response) {
            let is_approved = response.data;
            if( !is_approved) {
              let self2 = self;
              self.$alert('餐厅修改信息暂未通过审核请稍后！', '', {
                confirmButtonText: '确定',
                callback: action => {
                  self2.$router.push({name: 'restPage'});
              }
              })
            }
          }
        ).catch(function(error){
            console.log(error);
        });
      },
      data() {
        return {
          date1: '',
          date2: '',
          food_info:{

            image:'',
            name:'',
            price:0.0,
            num:0,
            type:'',
            type_list:[{
              value:'1',
              label:'主食'
            },{
              value:'2',
              label:'小食'
            },{
              value:'3',
              label:'甜品'
            },{
              value:'4',
              label:'饮品'
            }],
          }
        }
      },
      methods:{
        uploadSuccess(response, file, fileList) {
          console.log("uploadSuccess");
          this.food_info.image += 'http://localhost:8000/';
          this.food_info.image += response;
          console.log("this.proof:" + this.food_info.image);
        },
        launch() {
          let restId = localStorage.rest_id;
          let name = this.food_info.name;
          let type = this.food_info.type;
          let price = this.food_info.price;
          let amount = this.food_info.num;
          let startDate = this.date1;
          let endDate = this.date2;
          let image = this.food_info.image;

          this.$axios.post('/rest/new_food',{
            restId: restId,
            name: name,
            type: type,
            price: price,
            amount: amount,
            startDate: startDate,
            endDate: endDate,
            image: image
          }).then(
            function (response) {
              alert("发布成功！");
              window.location.reload();
            }
          ).catch(function (error) {

          })
        }
      }
    }
</script>

<style scoped>
  .main-body{
    margin-top: 40px;
    margin-left: 20px;
    width: 440px;
  }
</style>

<style>
.el-form-item__label{
    color:black !important;
    font-size: 16px !important;
  }
</style>
