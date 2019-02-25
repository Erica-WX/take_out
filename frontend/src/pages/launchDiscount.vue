<template>
    <restNavi paneltitle="发布信息 > 发布优惠">
      <div style="margin-top: 50px">
        <el-card class="box">
          <div slot="header" class="clearfix">
            <span>已有优惠</span>
          </div>
          <div style="font-size: 25px" v-show="is_empty">
            目前还没有发布优惠~
          </div>
          <div v-show="!is_empty">
            <el-table
            :data="discount_list"
            stripe
            >
              <el-table-column
                prop="name"
                label="优惠"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="startDate"
                label="开始日期"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="endDate"
                label="结束日期"
                align="center"
              ></el-table-column>
            </el-table>
          </div>
        </el-card>
        <el-card class="box">
          <div slot="header" class="clearfix">
            <span>发布优惠</span>
          </div>
          <div>
            <el-form ref="dis_form" :model="dis_form" label-width="80px">
              <el-form-item label="起止时间">
                <div style="display: flex">
                  <el-date-picker
                    v-model="dis_form.startDate"
                    type="date"
                    placeholder="选择开始日期">
                  </el-date-picker>
                  <div style="margin-left: 10px;margin-right: 10px">-</div>
                  <el-date-picker
                    v-model="dis_form.endDate"
                    type="date"
                    placeholder="选择结束日期">
                  </el-date-picker>
                </div>
              </el-form-item>

              <el-form-item label="满">
                <div style="display: flex">
                  <el-input v-model="dis_form.full_money"></el-input>
                  <div style="margin-left: 10px;margin-right: 10px">减</div>
                  <el-input v-model="dis_form.dis_money"></el-input>
                </div>
              </el-form-item>
              <div style="padding-left: 45%">
                <el-button type="primary" v-on:click="submit">发布</el-button>
              </div>
            </el-form>
          </div>
        </el-card>
      </div>
    </restNavi>
</template>

<script>
    import restNavi from '../components/restNavi'
    export default {
      name: "launch-discount",
      components:{restNavi},
      mounted: function () {
        this.get_discount_list();
      },
      data() {
        return {
          discount_list:[],
          is_empty: false,
          dis_form:{
            startDate:'',
            endDate:'',
            full_money: 0,
            dis_money: 0
          },
        }
      },

      methods:{

        submit() {
          let restId = localStorage.rest_id;
          let startDate = this.dis_form.startDate;
          let endDate = this.dis_form.endDate;
          let fullMoney = this.dis_form.full_money;
          let disMoney = this.dis_form.dis_money;

          this.$axios.post('/rest/set_discount',{
            restId: restId,
            fullMoney: fullMoney,
            disMoney: disMoney,
            startDate: startDate,
            endDate: endDate
          }).then(
            function (response) {
              alert("发布成功！");
              window.location.reload();
            }
          ).catch(function (error) {
            console,log(error);
          })
        },

        get_discount_list() {
          let rest_id = localStorage.rest_id;
          let self = this;
          this.$axios.get("/rest/get_discount_list",{
            params:{
              restId: rest_id
            }
          }).then(
            function (response) {
              console.log(response.data);

              if(response.data === null || response.data === undefined || response.data.length === 0) {
                self.is_empty = true;
              }else {
                self.is_empty = false;
                for(let i = 0; i < response.data.length; i++){
                  let name = "满"+ response.data[i].fullMoney + "减" + response.data[i].disMoney;
                  let discount = {
                    name: name,
                    startDate: response.data[i].startDate,
                    endDate: response.data[i].endDate
                  }
                  self.discount_list.push(discount);
                }
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        }
      }
    }
</script>

<style scoped>

  .box{
    width: 550px;
    margin-bottom: 50px;
  }

</style>
