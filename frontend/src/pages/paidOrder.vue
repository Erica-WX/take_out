<template>
  <memberNavi paneltitle="已支付订单">
    <div style="width: 600px;margin-bottom: 50px">
      <div style="margin-top: 20px; margin-bottom: 20px; display: flex">
        <div style="font-size: 25px;width: 250px">
          订单状态：<span style="color: #409EFF" v-show="!isCancel">已支付</span><span style="color: #409EFF" v-show="isCancel">申请退货中</span>
        </div>
        <div style="margin-left: 210px; margin-top: 10px">
          <router-link :to="{name:'order'}" style="color: #409EFF">>>>返回订单列表</router-link>
        </div>
      </div>

      <el-table
        :data="foodList"
        stripe
        width="100%"
      >
        <el-table-column
          prop="name"
          label="商品名称"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="num"
          label="商品数量"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="price"
          label="商品单价(元)"
          align="center"
        ></el-table-column>

      </el-table>
      <div style="color: #7e7e7e;padding-left: 68%;margin-top: 20px">
        <div align="center">
          <div class="title">
            配送费：3.5元
          </div>
          <div class="title">
            会员折扣：-{{this.disByLevel}}元
          </div>
          <div class="title" v-show="fullMoney !== 0">
            店铺满{{this.fullMoney}}减{{this.disByRest}}：-{{this.disByRest}}元
          </div>
        </div>

      </div>
      <div style="font-size: 40px;color: red;padding-left: 67%; margin-top: 10px">
        <div align="center" style="width: 200px;">
          ￥{{this.sum}}
        </div>
      </div>

      <div style="font-size: 20px">
        配送状态：<span style="color: #409EFF">{{this.express_state}}</span>
      </div>

      <el-row style="padding-left: 35%;margin-top: 25px" v-show="express_state !== '已送达' || express_state.isCancel">
        <el-button type="primary"  v-on:click="accept_order">确认收货</el-button>
        <el-button v-on:click="cancel_order">退订</el-button>
      </el-row>
    </div>
  </memberNavi>
</template>

<script>
  import memberNavi from '../components/memberNavi'
  export default {
    name: "paid-order",
    components: {memberNavi},
    mounted: function () {
      this.oid = this.$route.params.id;
      this.get_order_info();
      this.get_express_state();
    },
    data() {
      return {
        oid: 0,
        foodList:[],
        disByLevel: 0,
        disByRest: 0,
        fullMoney: 0,
        sum: 0,
        express_state: '',
        isCancel: false,
      }
    },

    methods: {
      get_order_info() {
        let oid = this.oid;
        let self = this;
        this.$axios.get('/order/get_order_detail', {
          params: {
            oid: oid
          }
        }).then(
          function (response) {
            let info = response.data;
            console.log(info);
            self.foodList = info.foodList;
            self.disByLevel = info.disByLevel;
            self.disByRest = info.disByRest;
            self.fullMoney = info.fullMoney;
            self.sum = info.sum;
            self.isCancel = info.isCancel;
          }
        ).catch(function (error) {
          console.log(error);
        })
      },

      get_express_state() {
        let oid = this.oid;
        let self = this;
        this.$axios.get('/order/get_express_state',{
          params: {
            oid: oid
          }
        }).then(
          function (response) {
            self.express_state = response.data;
          }
        ).catch(function (error) {
          console.log(error);
        })
      },

      accept_order() {
        let oid = this.oid;
        let self = this;
        this.$axios.get('/order/accept_order', {
          params: {
            oid: oid
          }
        }).then(
          function (response) {
            alert("收货成功！");
            self.$router.push({name: 'order'});
          }
        ).catch(function (error) {
          console.log(error);
        })
      },

      cancel_order() {
        let oid = this.oid;
        let self = this;

        let tip = "";
        let state = this.express_state;
        if(state === "等待商家接单"){
          tip = "是否退订？"
        }else if(state === "等待商家发货"){
          tip = "此时退订会扣除5%的费用，是否继续退订？";
        }else if(state === "配送中"){
          tip = "此时退订会扣除10%的费用，是否继续退订？";
        }else if(state === "已送达"){
          tip = "此时退订会扣除20%的费用，是否继续退订？";
        }

        this.$confirm(tip,'提示',{
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          self.$axios.get('/order/set_order_cancel', {
            params: {
              oid: oid
            }
          }).then(
            function (response) {

              self.$alert('退订成功!\n你可以在失效订单中查看相关信息，钱款待商家确定后返还', '', {
                confirmButtonText: '确定',
                callback: action => {
                  self.$router.push({name: 'order'});
                }
              });

              /*self.$message({
                type: 'success',
                message:'退订成功!你可以在失效订单中查看相关信息，钱款待商家确定后返还'
              });
              self.$router.push({name: 'order'});*/
            }
          ).catch(function (error) {
            console.log(error);
          });

      }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消退订'
          });
          }

        );
      }
    }
  }
</script>

<style scoped>

  .title{
    margin-bottom: 20px;
  }

</style>
