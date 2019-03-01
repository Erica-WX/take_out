<template>
  <memberNavi paneltitle="购物车">
    <div>
      <el-card style="width: 650px;margin-bottom: 40px">
        <div slot="header" class="clearfix">
          <span>订单详情</span>
          <el-button style="float: right; padding: 3px 0" type="text" v-on:click="return_to_rest">< 返回商家修改 </el-button>
        </div>
        <div>
          <el-table
            :data="basket"
            stripe
            style="width: 100%"
            @selection-change="handleChange"
          >
            <el-table-column
              prop="name"
              label="商品"
              align="center"
            ></el-table-column>
            <el-table-column
              prop="num"
              label="份数"
              align="center"
            >
              <template  slot-scope="scope" >
                <el-input-number size="mini" v-model="scope.row.num"></el-input-number>
              </template>
            </el-table-column>
            <el-table-column
              prop="price"
              label="小计(元)"
              align="center"
            >
              <template  slot-scope="scope" >
                <span>{{scope.row.num * scope.row.cost}}</span>
              </template>
            </el-table-column>
          </el-table>
          <div style="color: #7e7e7e;padding-left: 68%;margin-top: 20px">
            <div align="center">
              <div class="title">
                配送费：3.5元
              </div>
              <div class="title">
                会员折扣：-{{this.disMoneyByLevel}}元
              </div>
              <div class="title" v-show="fullMoney !== 0">
                店铺满{{this.fullMoney}}减{{this.disMoneyByRest}}：-{{this.disMoneyByRest}}元
              </div>
            </div>

          </div>
          <div style="font-size: 40px;color: red;padding-left: 67%; margin-top: 10px">
            <div align="center" style="width: 200px;">
              ￥{{this.all}}
            </div>
          </div>
          <div style="margin-left: 80px;">
            <p style="margin-top: 0"><span style=" font-size: 24px;color: #7e7e7e">收货地址：</span>{{this.now_address}},</p>
            <p><span style="font-size: 24px;color: #7e7e7e">预计送达时间：</span>{{this.time}}</p>
          </div>
          <div style="margin-left: 80px; display: flex;margin-top: 30px">
            <el-button v-on:click="submit">提交订单</el-button>
            <!--<el-button v-show="this.need_pay">付款</el-button>-->
          </div>
        </div>
      </el-card>
    </div>
  </memberNavi>
</template>

<script>
    import memberNavi from '../components/memberNavi'
    export default {
      name: "basket",
      components: {memberNavi},
      mounted: function () {

        this.id = this.$route.params.id;

        this.basket = this.$route.params.basket;

        this.cal_sum();

        this.now_address = localStorage.district + " " + localStorage.address;

        this.get_time();

      },
      data() {
        return {
          id:'',
          basket:[],
          all:0,
          disMoneyByLevel: 0,
          fullMoney: 0,
          disMoneyByRest: 0,
          now_address:'',
          need_pay:false,
          time:'',
        }
      },
      methods: {
        cal_sum() {
          let basket = this.basket;
          let sum = 0;
          for(let i = 0; i < basket.length; i++) {
            sum += basket[i].price;
          }
          console.log("sum:"+sum);

          let email = localStorage.user_email;
          let level = 1;
          this.$axios.get('/user/get_level',{
            params: {
              email: email
            }
          }).then(
            function (response) {
              level = response.data;
            }
          ).catch(function (error) {
            console.log(error);
          });

          let restId = this.id;
          let self = this;
          this.$axios.post('/rest/cal_order',{
            restId: restId,
            level: level,
            sum: sum,
          }).then(
            function (response) {
              console.log(response.data);
              let order = response.data;
              self.all = order.sum + 3.5;
              self.disMoneyByLevel = order.disMoneyByLevel;
              self.fullMoney = order.fullMoney;
              self.disMoneyByRest = order.disMoneyByRest;
            }
          ).catch(function (error) {
            console.log(error);
          })

        },

        get_time(){

          let self = this;
          this.$axios.post('/time/get_time').then(
            function (response) {
              console.log(response.data);
              self.time = response.data;
            }
          )
        },

        submit() {
          let email = localStorage.user_email;
          let restId = this.id;
          let sum = this.all;
          let foodList = this.basket;
          let disByLevel = this.disMoneyByLevel;
          let disByRest = this.disMoneyByRest;
          let fullMoney = this.fullMoney;
          let aid = parseInt(localStorage.aid);

          let self = this;
          this.$axios.post('/order/new_order',{
            email: email,
            restId: restId,
            sum: sum,
            foodList: foodList,
            disByLevel: disByLevel,
            disByRest: disByRest,
            fullMoney: fullMoney,
            aid: aid
          }).then(
            function (response) {
              alert("订单提交完成！\n请在2分钟内在‘我的订单’中完成支付");
              self.$router.push({name: 'foodList'});
            }
          ).catch(
            function (error) {
              console.log(error);
            }
          )
        },

        return_to_rest() {
          this.$router.push({name:'restInfo', params: {basket: this.basket, id: this.id}})
        },

        handleChange() {
          console.log(this.basket);
        }
      },

      computed: {
        a:function() {
          console.log(this.basket);
        }
      }
    }
</script>

<style scoped>
/*Vue中表格修改一项数据后，另一项如何实时变化*/

  .title{
    margin-bottom: 20px;
  }
</style>
