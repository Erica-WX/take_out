<template>
    <adminNavi paneltitle="统计信息">
      <div style="padding-top: 20px">
        <el-tabs v-model="index" @tab-click="handleClick">
          <el-tab-pane label="会员统计" name="1">
            <div class="title" style="margin-top: 20px">
              使用Yummy平台的会员共<span class="special">{{this.member_info.num}}</span>人
            </div>
            <div id="memberChart" style="width: 450px; height: 400px; margin-top: 30px">
            </div>
          </el-tab-pane>
          <el-tab-pane label="餐厅统计" name="2">
            <div class="title" style="margin-top: 20px">
              在Yummy平台运营的餐厅共<span class="special">{{this.rest_info.num}}</span>家
            </div>
            <div id="restChart" style="width: 450px; height: 400px; margin-top: 30px">
            </div>
          </el-tab-pane>
          <el-tab-pane label="Yummy财务统计" name="3">
            <div class="title" style="margin-top: 20px">
              平台运营累积收益达<span class="special">{{this.plat_info.earning}}</span>元
            </div>
            <div style="display: flex; margin-top: 50px;">
              <div style="font-size: 18px; color: #7e7e7e; margin-top: 3px; margin-right: 10px">年份</div>
              <el-select v-model="plat_info.year" size="small">
                <el-option
                  v-for="item in plat_info.year_list"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </div>
            <div id="platChart" style="width: 650px; height: 400px; margin-top: 30px">

            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </adminNavi>
</template>

<script>
    import adminNavi from '../components/adminNavi'

    let echarts = require('echarts/lib/echarts');
    // 引入柱状图组件
    require('echarts/lib/chart/bar');
    require('echarts/lib/chart/line');
    require('echarts/lib/chart/pie');
    // 引入提示框和title组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');
    //
    require('echarts/theme/macarons');

    export default {
      name: "admin-statistics",
      components: {adminNavi},
      mounted: function () {
        this.get_member_info();
      },
      watch: {
        'plat_info.year':{
          handler(){
            this.get_earnings();
          }
        },
        deep:true,
      },
      data() {
        return {
          index: '1',
          member_info: {
            num: 100,
            level_list: ['等级1', '等级2', '等级3', '等级4', '等级5'],
            member_list:[12,20,30,26,15]
          },
          rest_info: {
            num: 55,
            type_list: ['甜品饮品', '快餐便当', '小吃夜宵', '特色菜系'],
            rest_list: [10, 23, 13, 42]
          },
          plat_info: {
            earning: 3000,
            year: '2019',
            year_list: ['2019','2018','2017','2016','2015',],
            month_list: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
            earning_list: []
          }
        }
      },
      methods: {
        handleClick(tab, event) {
          if(this.index === '1') {
            this.get_member_info()
          }else if(this.index === '2') {
            this.get_rest_info();
          }else if(this.index === '3') {
            this.get_plat_info();
          }
        },

        get_member_info() {
          let self = this;
          this.$axios.post('/admin/get_member_info').then(
            function (response) {
              self.member_info.num = response.data.num;
              self.member_info.member_list = response.data.memberList;
              self.drawMember();
            }
          ).catch(function (error) {
            console.log(error)
          });

        },
        get_rest_info() {
          let self = this;
          this.$axios.post('/admin/get_rest_info').then(
            function (response) {
              self.rest_info.num = response.data.num;
              self.rest_info.rest_list = response.data.restList;
              self.drawRest();
            }
          ).catch(function (error) {
            console.log(error)
          });
        },

        get_plat_info() {
          let self = this;
          this.$axios.post('/admin/get_plat_earning').then(
            function (response) {
              self.plat_info.earning = response.data;
              self.get_earnings();
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        get_earnings() {
          let year = this.plat_info.year;
          let self = this;
          console.log("year:" + year);
          this.$axios.get('/admin/get_earning_list', {
            params: {
              year: year
            }
          }).then(
            function (response) {
              console.log(response.data);
              self.plat_info.earning_list = response.data;
              self.drawPlat();
              console.log(self.plat_info.earning_list);
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        drawMember() {
          // 基于准备好的dom，初始化echarts实例
          /*let myChart = echarts.init(document.getElementById('memberChart'));*/
          let myChart = echarts.init(document.getElementById('memberChart'),'macarons');
          // 绘制图表
          myChart.setOption({
            title : {
              text: '人数',
              /*subtext: '纯属虚构'*/
            },
            tooltip : {
              trigger: 'axis'
            },
            toolbox: {
              show : true,
              feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
              }
            },
           /* calculable : true,*/
            xAxis : [
              {
                type : 'category',
                /*boundaryGap : false,*/
                data : this.member_info.level_list
              }
            ],
            yAxis : [
              {
                type : 'value',
                axisLabel : {
                  formatter: '{value} '
                }
              }
            ],
            series : [
              {
                name:'人数',
                type:'bar',
                data:this.member_info.member_list,
                markPoint : {
                  data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                  ]
                },
                markLine : {
                  data : [
                    {type : 'average', name: '平均值'}
                  ]
                }
              },
            ]
          });
        },
        drawRest() {
          // 基于准备好的dom，初始化echarts实例
          let myChart = echarts.init(document.getElementById('restChart'),'macarons');
          // 绘制图表
          myChart.setOption({
            title : {
              text: '餐厅数',
              /*subtext: '纯属虚构'*/
            },
            tooltip : {
              trigger: 'axis'
            },
            toolbox: {
              show : true,
              feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
              }
            },
            /* calculable : true,*/
            xAxis : [
              {
                type : 'category',
                /*boundaryGap : false,*/
                data : this.rest_info.type_list
              }
            ],
            yAxis : [
              {
                type : 'value',
                axisLabel : {
                  formatter: '{value} '
                }
              }
            ],
            series : [
              {
                name:'餐厅数',
                type:'bar',
                data:this.rest_info.rest_list,
                markPoint : {
                  data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                  ]
                },
                markLine : {
                  data : [
                    {type : 'average', name: '平均值'}
                  ]
                }
              },
            ]
          });
        },
        drawPlat() {
          console.log("draw");
          let myChart = echarts.init(document.getElementById('platChart'),'macarons');
          // 绘制图表
          myChart.setOption({
            title : {
              text: '平台收益',
              /*subtext: '纯属虚构'*/
            },
            tooltip : {
              trigger: 'axis'
            },
            toolbox: {
              show : true,
              feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
              }
            },
            /* calculable : true,*/
            xAxis : [
              {
                type : 'category',
                /*boundaryGap : false,*/
                data : this.plat_info.month_list
              }
            ],
            yAxis : [
              {
                type : 'value',
                axisLabel : {
                  formatter: '{value} '
                }
              }
            ],
            series : [
              {
                name:'平台收益',
                type:'line',
                data:this.plat_info.earning_list,
                markPoint : {
                  data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                  ]
                },
                markLine : {
                  data : [
                    {type : 'average', name: '平均值'}
                  ]
                }
              },
            ]
          });
        }
      }
    }
</script>

<style scoped>

  .title{
    font-size: 30px;
    color: #7e7e7e;
  }

  .special{
    font-size: 38px;
    color: #409EFF;
  }

</style>

<style>
  .el-tabs__item{
    font-size: 18px !important;
  }
</style>
