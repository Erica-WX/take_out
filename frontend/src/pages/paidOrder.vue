<template>
  <memberNavi paneltitle="已支付订单">
    <div style="width: 600px;margin-bottom: 50px">
      <div style="margin-top: 20px; margin-bottom: 20px; display: flex">
        <div style="font-size: 25px;">
          订单状态：<span style="color: #409EFF">已支付</span>
        </div>
        <div style="margin-left: 230px; margin-top: 10px">
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

      <el-row style="padding-left: 35%;margin-top: 25px">
        <el-button type="primary">确认收货</el-button>
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
        express_state: ''
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
            self.foodList = info.foodList;
            self.disByLevel = info.disByLevel;
            self.disByRest = info.disByRest;
            self.fullMoney = info.fullMoney;
            self.sum = info.sum;
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

      cancel_order() {
        let oid = this.oid;
        let self = this;
        this.$axios.get('/order/cancel_order', {
          params: {
            oid: oid
          }
        }).then(
          function (response) {
            alert("该订单已被取消\n你可以在已失效订单中查看");
            self.$router.push({name: 'order'});
          }
        ).catch(function (error) {
          console.log(error);
        })
      }
    }
  }
</script>

<style scoped>

  .title{
    margin-bottom: 20px;
  }

</style>
