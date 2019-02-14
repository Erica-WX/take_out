<template>
  <restNavi paneltitle="发布新品">
    <div class="main-body">
      <el-form ref="food_info" :model="food_info" label-width="80px">
        <el-form-item label="商品名称">
          <el-input v-model="food_info.name"></el-input>
        </el-form-item>
        <el-form-item label="商品类型">
          <el-select style="width: 320px"
            v-model="food_info.type_value"
            filterable
            allow-create
            default-first-option
            placeholder="请选择商品类型">
            <el-option
              v-for="item in food_info.type_list"
              :key="item.value"
              :label="item.label"
              :value="item.value">
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
              placeholder="选择开始日期">
            </el-date-picker>
            <div>-</div>
            <el-date-picker
              v-model="date2"
              type="date"
              placeholder="选择结束日期">
            </el-date-picker>
          </div>

        </el-form-item>
        <el-form-item label="商品图片">
          <el-button>点击上传</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">发布</el-button>
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
      data() {
        return {
          date1: '',
          date2: '',
          food_info:{
            name:'',
            price:0.0,
            num:0,
            type_value:[],
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
      }
    }
</script>

<style scoped>
  .main-body{
    margin-top: 40px;
    margin-left: 20px;
    width: 400px;
  }
</style>
