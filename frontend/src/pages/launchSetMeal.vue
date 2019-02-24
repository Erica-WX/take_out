<template>
    <restNavi paneltitle="发布信息 > 发布套餐">
      <div class="main_body">
        <el-form style="width: 600px" ref="setmeal_info" :model="setmeal_info" label-width="80px">
          <el-form-item label="套餐名称">
            <el-input v-model="setmeal_info.name"></el-input>
          </el-form-item>
          <el-form-item label="套餐价格">
            <el-input v-model="setmeal_info.price"></el-input>
          </el-form-item>
          <div class="title">添加商品：</div>
          <el-table
            :data="setmeal_info.food_list"
            stripe=""
            style="width: 100%"
          >
            <el-table-column
              prop="name"
              label="名称"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="price"
              label="单价"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="num"
              label="数量"
              align="center"
            >
              <template slot-scope="scope">
                <el-input-number size="mini" v-model="scope.row.num"></el-input-number>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              align="center"
            >
              <template slot-scope="scope">
                <el-button size="mini" @click="add_food(scope.row.name, scope.row.price, scope.row.num)">加入套餐</el-button>
              </template>
            </el-table-column>

          </el-table>
        </el-form>

        <el-form style="width: 600px; margin-top: 50px">
          <div class="title">套餐内容:</div>

          <el-table
            :data="real_setmeal.food_list"
            stripe=""
            style="margin-bottom: 30px"
          >
            <el-table-column
              prop="name"
              label="名称"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="price"
              label="单价"
              align="center"
            ></el-table-column>
            <el-table-column
              prop="num"
              label="数量"
              align="center"
            ></el-table-column>
            <el-table-column
              label="操作"
              align="center"
            >
              <template slot-scope="scope">
                <el-button size="mini" type="danger" @click="delete_food(scope.$index)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
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
          <div>
            <el-button style="margin-left: 45%; margin-top: 30px" type="primary" v-on:click="submit">提交</el-button>
          </div>
        </el-form>

      </div>
    </restNavi>
</template>

<script>
    import restNavi from '../components/restNavi'
    export default {
      name: "launch-setmeal",
      components:{restNavi},
      mounted: function () {
        this.get_food_list();
      },
      data() {
        return {
          setmeal_info:{
            name:'',
            price:0.0,
            food_list: [
              {
                name:'土豆',
                price:10,
                num:1,
              }
            ],
          },
          real_setmeal: {
            food_list:[],
            image:'',
          }
        }
      },
      methods: {
        uploadSuccess(response, file, fileList) {
          console.log("uploadSuccess");
          this.real_setmeal.image += 'http://localhost:8000/';
          this.real_setmeal.image += response;
          console.log("this.proof:" + this.real_setmeal.image);
        },
        add_food(name, price, num) {

          let isNew = true;
          for(let i = 0; i < this.real_setmeal.food_list.length; i++){
            if(this.real_setmeal.food_list[i].name === name && this.real_setmeal.food_list[i].price === price){
              this.real_setmeal.food_list[i].num += num;
              isNew = false;
              break;
            }else {
              isNew = true;
            }
          }

          if(isNew){
            let food = {
              name: name,
              price: price,
              num: num
            }

            this.real_setmeal.food_list.push(food);
          }

        },

        delete_food(index) {
          console.log(index);
          this.real_setmeal.food_list .splice(index, 1);
        },

        submit(){
          let name = this.setmeal_info.name;
          let price = this.setmeal_info.price;
          let image = this.real_setmeal.image;
          let food_list = this.real_setmeal.food_list;

          this.$axios.post('',{
            name: name,
            price: price,
            image:image,
            food_list: food_list
          }).then(
            function (response) {
              //console
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        get_food_list() {
          let id = localStorage.rest_id;
          let self = this;
          this.$axios.get('/rest/get_food_list',{
            params:{
              id:id
            }
          }).then(
            function (response) {
              console.log(response.data);
              self.setmeal_info.food_list = response.data;
            }
          ).catch(function (error) {
            console.log(error);
          })
        },
      }
    }
</script>

<style scoped>

  .title{
    font-size: 24px;
    color: #7e7e7e;
  }

  .main_body{
    margin-top: 40px;
    margin-left: 20px;
    width: 440px;
    margin-bottom: 50px;
  }

</style>
