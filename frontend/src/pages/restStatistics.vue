<template>
   <restNavi paneltitle="统计信息">
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
         <span class="title">用户名称：</span>
         <el-input style="display: inline-block;width: 220px" size="small" v-model="filter_info.username"></el-input>
       </el-row>
       <el-table
         :data="tableList"
         stripe=""
         style="margin-top: 30px;margin-bottom: 50px"
       >
         <el-table-column
           prop="username"
           label="用户名"
           align="center"
           width="120px"
         ></el-table-column>
         <el-table-column
           prop="sendAddress"
           label="配送地址"
           align="center"
           width="160px"
         >
         </el-table-column>
         <el-table-column
           prop="orderTime"
           label="下单时间"
           align="center"
           width="100px"
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
         <el-table-column
          prop="earning"
          label="收益"
          align="center"
         ></el-table-column>
       </el-table>
     </div>
   </restNavi>
</template>

<script>
    import restNavi from '../components/restNavi'
    export default {
      name: "rest-statistics",
      components:{restNavi},
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
        'filter_info.username':{
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
            username: '',
          },
          tableList: [],
          allList: [],
        }
      },
      methods: {
        get_all_info() {
          let restId = localStorage.rest_id;
          let self = this;
          this.$axios.get('/order/get_rest_statistics', {
            params: {
              restId: restId
            }
          }).then(
            function (response) {
              self.allList = response.data;
              console.log(response.data)
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
          let username = this.filter_info.username;

          console.log("allList:");
          console.log(this.allList);/*
          let list = this.allList;*/
          let list = [];
          for(let i = 0; i < this.allList.length; i++) {
            let obj = this.allList[i];
            list.push(obj);
          }

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

          if(username !== null && username !== "") {
            for(let i = 0; i < list.length; i++) {
              let name = list[i].username;

              if(name.search(username) === -1) {
                list.splice(i, 1);
                i--;
              }
            }
          }

          this.tableList = list;
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
