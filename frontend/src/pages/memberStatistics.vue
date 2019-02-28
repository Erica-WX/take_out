<template>
    <memberNavi paneltitle="统计信息">
      <div>
        <el-row class="row">
          <span class="title">开始日期：</span>
          <el-date-picker
            v-model="filter_info.startDate"
            type="date"
            size="small"
            value-format="yyyy-MM-dd"
            placeholder="选择开始日期">
          </el-date-picker>
          <span class="title">结束日期：</span>
          <el-date-picker
            v-model="filter_info.endDate"
            type="date"
            size="small"
            value-format="yyyy-MM-dd"
            placeholder="选择结束日期">
          </el-date-picker>
        </el-row>
      <el-row class="row">
        <span class="title">最低金额：</span>
        <el-input style="display: inline-block;width: 220px" size="small" v-model="filter_info.leastMoney"></el-input>
        <span class="title">最高金额：</span>
        <el-input style="display: inline-block;width: 220px" size="small" v-model="filter_info.mostMoney"></el-input>
      </el-row>

        <el-row class="row">
          <span class="title">餐厅名称：</span>
          <el-input style="display: inline-block;width: 220px" size="small" v-model="filter_info.restName"></el-input>
        </el-row>
        <el-table
          :data="tableList"
          stripe=""
          style="margin-top: 30px;margin-bottom: 50px"
        >
          <el-table-column
            prop="restName"
            label="餐厅名称"
            align="center"
            width="120px"
          ></el-table-column>
          <el-table-column
            prop="restType"
            label="餐厅类型"
            align="center"
            width="120px"
            :filters="[{ text: '甜品饮品', value: '甜品饮品' }, { text: '小吃夜宵', value: '小吃夜宵' }, { text: '快餐便当', value: '快餐便当' }, { text: '特色菜系', value: '特色菜系' }, ]"
            :filter-method="filterRestType"
            filter-placement="bottom-end"
          >
          </el-table-column>
          <el-table-column
            prop="orderTime"
            label="下单时间"
            align="center"
          ></el-table-column>
          <el-table-column
            label="订单商品"
            align="center"
          >
            <template slot-scope="scope">
              <p
                v-for="item in scope.row.foodList"
                :key="item.id"
              >
                {{item.name + ' x'}}{{item.num}}
              </p>
            </template>
          </el-table-column>
          <el-table-column
            prop="sum"
            label="订单总价"
            align="center"
            width="100px"
          >
          </el-table-column>
          <el-table-column
            prop="isCancel"
            label="是否退订"
            align="center"
            width="100px"
            :filters="[{ text: '是', value: '是' }, { text: '否', value: '否' }]"
            :filter-method="filterCancel"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              <div v-show="scope.row.isCancel">是</div> <div v-show="!scope.row.isCancel">否</div>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </memberNavi>
</template>

<script>
    import memberNavi from '../components/memberNavi'
    export default {
      name: "member-statistics",
      components: {memberNavi},
      mounted: function () {
        this.get_all_info();
      },
      watch: {
        'filter_info.startDate':{
          handler(){
            this.filter_order_info();
          }
        },
        'filter_info.endDate':{
          handler(){
            this.filter_order_info();
          }
        },
        'filter_info.leastMoney':{
          handler(){
            this.filter_order_info();
          }
        },
        'filter_info.mostMoney':{
          handler(){
            this.filter_order_info();
          }
        },
        'filter_info.restName':{
          handler(){
            this.filter_order_info();
          }
        },
        deep:true,
      },
      data() {
        return {
          filter_info:{
            startDate: '',
            endDate: '',
            leastMoney: '',
            mostMoney: '',
            restName: '',
          },
          tableList: [],
          allList: []
        }
      },
      methods: {
        get_all_info() {
          let email = localStorage.user_email;
          let self = this;
          this.$axios.get('/order/get_member_statistics', {
            params: {
              email: email
            }
          }).then(
            function (response) {
              self.allList = response.data;
              console.log(response.data);
              for(let i = 0; i < self.allList.length; i++) {
                let time = self.allList[i].orderTime + "";
                time = time.substring(0, 10) + " " + time.substring(11,20);
                self.allList[i].orderTime = time;
              }
              self.tableList = self.allList;
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        filter_order_info() {
          console.log(this.filter_info);
          let startDate = this.filter_info.startDate;
          let endDate = this.filter_info.endDate;
          let leastMoney = this.filter_info.leastMoney;
          let mostMoney = this.filter_info.mostMoney;
          let restName = this.filter_info.restName;

          console.log("allList:");
          console.log(this.allList);/*
          let list = this.allList;*/
          let list = [];
          for(let i = 0; i < this.allList.length; i++) {
            let obj = this.allList[i];
            list.push(obj);
          }
         /* let date1 = '2019-01-15';
          let date2 = '2019-02-13';

          let s = new Date(Date.parse(date1));
          let e = new Date(Date.parse(date2));

          console.log(s > e);*/
          if(startDate !== null && startDate !== "") {
            for(let i = 0; i < list.length; i++) {
              let date = list[i].orderTime.substring(0,10);
              let date1 = new Date(Date.parse(startDate));
              let date2 = new Date(Date.parse(date));

              if(date2 < date1) {
                list.splice(i, 1);
                i--;
              }
            }
          }

          if(endDate !== null && endDate !== "") {
            for(let i = 0; i < list.length; i++) {
              let date = list[i].orderTime.substring(0,10);
              let date1 = new Date(Date.parse(endDate));
              let date2 = new Date(Date.parse(date));

              if(date2 > date1) {
                list.splice(i, 1);
                i--;
              }
            }
          }

          if(leastMoney !== null && leastMoney !== "") {
            for(let i = 0; i < list.length; i++) {
              let money = parseFloat(leastMoney);
              let sum = list[i].sum;

              if(sum < money) {
                list.splice(i, 1);
                i--;
              }
            }
          }

          if(mostMoney !== null && mostMoney !== "") {
            for(let i = 0; i < list.length; i++) {
              let money = parseFloat(mostMoney);
              let sum = list[i].sum;

              if(sum > money) {
                list.splice(i, 1);
                i--;
              }
            }
          }

          if(restName !== null && restName !== "") {
            for(let i = 0; i < list.length; i++) {
              let name = list[i].restName;

              if(name.search(restName) === -1) {
                list.splice(i, 1);
                i--;
              }
            }
          }

          this.tableList = list;
        },

        filterRestType(value, row) {
          return row.restType === value;
        },

        filterCancel(value, row) {
          if(value === '是'){
            return row.isCancel;
          }else if(value === '否'){
            return !row.isCancel;
          }

        },
      }
    }
</script>

<style scoped>
  .title{
    color: #7e7e7e;
    padding-top: 5px;
    margin-left: 30px;
  }

  .row{
    margin-top: 20px;
  }
</style>
