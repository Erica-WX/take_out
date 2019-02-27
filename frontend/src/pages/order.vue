<template>
    <memberNavi paneltitle="我的订单">
      <div style="padding-left:20px; padding-top: 30px; width: 600px">
        <el-tabs v-model="index" @tab-click="handleClick">
          <el-tab-pane label="待支付订单" name="1">
            <el-table
              :data="not_paid_list"
              stripe
              width="100%"
              style="margin-top: 15px"
            >
              <el-table-column
                prop="orderTime"
                label="下单时间"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="sum"
                label="订单价格(元)"
                align="center"
              ></el-table-column>
              <el-table-column
                label="操作"
                align="center"
              >
                <template slot-scope="scope">
                  <el-button size="small" v-on:click="check_not_paid(scope.row.id)">查看详情</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="已支付订单" name="2">
            <el-table
              :data="complete_list"
              stripe
              width="100%"
              style="margin-top: 15px"
            >
              <el-table-column
                prop="orderTime"
                label="下单时间"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="sum"
                label="订单价格(元)"
                align="center"
              ></el-table-column>
              <el-table-column
                label="操作"
                align="center"
              >
                <template slot-scope="scope">
                  <el-button size="small">查看详情</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="已失效订单" name="3">
            <el-table
              :data="invalid_list"
              stripe
              width="100%"
              style="margin-top: 15px"
            >
              <el-table-column
                prop="orderTime"
                label="下单时间"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="sum"
                label="订单价格(元)"
                align="center"
              ></el-table-column>
              <el-table-column
                label="操作"
                align="center"
              >
                <template slot-scope="scope">
                  <el-button size="small" v-on:click="check_invalid(scope.row.id)">查看详情</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </div>
    </memberNavi>
</template>

<script>
    import memberNavi from '../components/memberNavi'
    export default {
      name: "order",
      components: {memberNavi},
      mounted: function () {
        this.get_list('1');
      },
      data() {
        return {
          index: '1',
          not_paid_list:[],
          complete_list: [],
          invalid_list: [],

        }
      },
      methods: {

        handleClick(tab, event) {
          let index = this.index;
          this.get_list(index);
        },
        get_list(index) {
          if(index === '1') {
            this.get_need_pay_list();
          }else if(index === '2') {
            this.get_paid_list();
          }else if(index === '3') {
            this.get_invalid_list();
          }
        },
        get_need_pay_list() {
          let email = localStorage.user_email;
          let self = this;
          this.$axios.get('/order/get_not_paid',{
            params: {
              email: email
            }
          }).then(
            function (response) {
              self.not_paid_list = response.data;
              for(let i = 0; i < self.not_paid_list.length; i++) {
                let time = self.not_paid_list[i].orderTime + "";
                time = time.substring(0, 10) + " " + time.substring(11,20);
                self.not_paid_list[i].orderTime = time;
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        },
        get_paid_list() {
          let email = localStorage.user_email;
          let self = this;
          this.$axios.get('/order/get_complete', {
            params: {
              email: email
            }
          }).then(
            function (response) {
              self.complete_list = response.data;
              for(let i = 0; i < self.complete_list.length; i++) {
                let time = self.complete_list[i].orderTime + "";
                time = time.substring(0, 10) + " " + time.substring(11,20);
                self.complete_list[i].orderTime = time;
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        },
        get_invalid_list() {
          let email = localStorage.user_email;
          let self = this;
          this.$axios.get('/order/get_invalid', {
            params: {
              email: email
            }
          }).then(
            function (response) {
              self.invalid_list = response.data;
              for(let i = 0; i < self.invalid_list.length; i++) {
                let time = self.invalid_list[i].orderTime + "";
                time = time.substring(0, 10) + " " + time.substring(11,20);
                self.invalid_list[i].orderTime = time;
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        },
        check_not_paid(id) {
          this.$router.push({name: 'notPaidOrder', params: {id: id}});
        },

        check_invalid(id) {
          this.$router.push({name: 'invalidOrder', params: {id: id}});
        }
      }
    }
</script>

<style scoped>

</style>

<style>
  .el-tabs__item{
    font-size: 18px !important;
  }
</style>
