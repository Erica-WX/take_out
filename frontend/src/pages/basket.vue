<template>
  <memberNavi>
    <div>
      <el-card style="width: 650px;margin-bottom: 40px">
        <div slot="header" class="clearfix">
          <span>订单详情</span>
          <el-button style="float: right; padding: 3px 0" type="text" v-on:click="">< 返回商家修改 </el-button>
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
                <el-input-number v-show="scope.row.num" size="mini" v-model="scope.row.num"></el-input-number>
              </template>
            </el-table-column>
            <el-table-column
              prop="price"
              label="小计(元)"
              align="center"
            >
            </el-table-column>
          </el-table>
          <div style="font-size: 40px;color: red;margin-left: 73%;margin-top: 15px">
            ￥{{this.all}}
          </div>
          <div style="margin-left: 80px;">
            <p style="margin-top: 0"><span style=" font-size: 24px;color: #7e7e7e">收货地址：</span>{{this.now_address}},</p>
            <p><span style="font-size: 24px;color: #7e7e7e"">预计送达时间：</span>{{this.time}}</p>
          </div>
          <div style="margin-left: 80px; display: flex;margin-top: 30px">
            <el-button v-on:click="submit">提交订单</el-button>
            <el-button v-show="this.need_pay">付款</el-button>
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
        let basket = this.$route.params.basket;
        //console.log(basket);
        let express = {
          name: '配送费',
          price: 3.5,
        };
        basket.push(express);
        this.basket = basket;
        /*console.log(basket);*/
        this.cal_sum();

        this.now_address = localStorage.district + " " + localStorage.address;

        this.get_time();

      },
      data() {
        return {
          basket:[],
          all:0,
          now_address:'',
          need_pay:false,
          time:'',
          num_count:[]
        }
      },
      methods: {
        cal_sum() {
          let basket = this.basket;
          let sum = 0;
          for(let i = 0; i < basket.length; i++) {
            sum += basket[i].price;
          }
          console.log("sum:"+sum)
          this.all = sum;
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
          this.need_pay = true;
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
</style>
