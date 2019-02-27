<template>
    <restNavi paneltitle="送餐记录">
      <div style="padding-top: 30px; padding-left: 20px; width: 650px">
        <el-tabs v-model="index" @tab-click="handleClick">
          <el-tab-pane label="待接单订单" name="1">
            <el-table
              :data="not_receive_list"
              stripe
              width="100%"
            >
              <el-table-column
                prop="orderTime"
                label="下单时间"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="foodList"
                label="商品"
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
                label="总价(元)"
                align="center"
              >
              </el-table-column>
              <el-table-column
                label="操作"
                align="center"
              >
                <template slot-scope="scope">
                  <el-button size="small" v-on:click="receive_order(scope.row.oid)">接单</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="待发货订单" name="2">
            <el-table
              :data="not_deliver_list"
              stripe
              width="100%"
            >
              <el-table-column
                prop="orderTime"
                label="下单时间"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="foodList"
                label="商品"
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
                label="总价(元)"
                align="center"
              >
              </el-table-column>
              <el-table-column
                label="操作"
                align="center"
              >
                <template slot-scope="scope">
                  <el-button size="small" v-on:click="deliver_order(scope.row.oid)">发货</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="已发货订单" name="3">
            <el-table
              :data="delivered_list"
              stripe
              width="100%"
            >
              <el-table-column
                prop="orderTime"
                label="下单时间"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="foodList"
                label="商品"
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
                label="总价(元)"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="state"
                label="状态"
                align="center"
              >
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </div>
    </restNavi>
</template>

<script>
    import restNavi from '../components/restNavi'
    export default {
      name: "express-record",
      components:{restNavi},
      mounted: function () {
        this.get_not_receive();
      },
      data() {
        return {
          index: '1',
          not_receive_list: [],
          not_deliver_list: [],
          delivered_list: [],
        }
      },
      methods: {
        handleClick() {
          let index = this.index;
          if(index === '1') {
            this.get_not_receive();
          }else if(index === '2') {
            this.get_not_deliver();
          }else if(index === '3') {
            this.get_delivered();
          }
        },

        get_not_receive() {
          let restId = localStorage.rest_id;
          let self = this;

          this.$axios.get('/order/get_not_receive', {
            params: {
              restId: restId
            }
          }).then(
            function (response) {
              self.not_receive_list = response.data;
              for(let i = 0; i < self.not_receive_list.length; i++) {
                let time = self.not_receive_list[i].orderTime + "";
                time = time.substring(0, 10) + " " + time.substring(11,20);
                self.not_receive_list[i].orderTime = time;
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        get_not_deliver() {
          let restId = localStorage.rest_id;
          let self = this;

          this.$axios.get('/order/get_not_deliver', {
            params: {
              restId: restId
            }
          }).then(
            function (response) {
              self.not_deliver_list = response.data;
              for(let i = 0; i < self.not_deliver_list.length; i++) {
                let time = self.not_deliver_list[i].orderTime + "";
                time = time.substring(0, 10) + " " + time.substring(11,20);
                self.not_deliver_list[i].orderTime = time;
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        get_delivered() {
          let restId = localStorage.rest_id;
          let self = this;

          this.$axios.get('/order/get_delivered', {
            params: {
              restId: restId
            }
          }).then(
            function (response) {
              self.delivered_list = response.data;
              for(let i = 0; i < self.delivered_list.length; i++) {
                let time = self.delivered_list[i].orderTime + "";
                time = time.substring(0, 10) + " " + time.substring(11,20);
                self.delivered_list[i].orderTime = time;
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        receive_order(oid) {
          this.$axios.get('/order/receive_order',{
            params: {
              oid: oid
            }
          }).then(
            function (response) {
              alert("接单成功！");
              window.location.reload();
            }
          ).catch(function (error) {
            console.log(error);
          })
        },
        deliver_order(oid) {
          this.$axios.get('/order/deliver_order',{
            params: {
              oid: oid
            }
          }).then(
            function (response) {
              alert("发货成功！");
              window.location.reload();
            }
          ).catch(function (error) {
            console.log(error);
          })
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
