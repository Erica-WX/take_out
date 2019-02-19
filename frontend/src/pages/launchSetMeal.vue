<template>
    <restNavi paneltitle="发布信息 > 发布套餐">
      <div>
        <el-form ref="setmeal_info" :model="setmeal_info" label-width="80px">
          <el-form-item label="套餐名称">
            <el-input v-model="setmeal_info.name"></el-input>
          </el-form-item>
          <el-input-item label="套餐价格">
            <el-input v-model="setmeal_info.price"></el-input>
          </el-input-item>
        </el-form>
        <el-table
          :data="food_list"
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
          >
            <template slot-scope="scope">
              <el-button>加入套餐</el-button>
            </template>
          </el-table-column>

        </el-table>
      </div>
    </restNavi>
</template>

<script>
    import restNavi from '../components/restNavi'
    export default {
      name: "launch-combo",
      components:{restNavi},
      mounted: {
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
              self.food_list = response.data;
            }
          ).catch(function (error) {
            console.log(error);
          })
        },
      },
      data() {
        return {
          food_list: [],
          setmeal_info:{
            name:'',
            price:0.0
          }
        }
      }
    }
</script>

<style scoped>

</style>
