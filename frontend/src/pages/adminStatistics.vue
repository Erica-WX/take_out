<template>
    <adminNavi paneltitle="统计信息">
      <div style="padding-top: 20px">
        <el-tabs v-model="index" @tab-click="handleClick">
          <el-tab-pane label="会员统计" name="1">
            <div class="title" style="margin-top: 20px">
              使用Yummy平台的会员共<span class="special">{{this.member_info.num}}</span>人
            </div>

            <div id="memberChart" style="width: 450px; height: 400px; margin-top: 20px">

            </div>
          </el-tab-pane>
          <el-tab-pane label="餐厅统计" name="2">

          </el-tab-pane>
          <el-tab-pane label="Yummy财务统计" name="3">

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
        this.drawLine();
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

          }
        }
      },
      methods: {
        handleClick(tab, event) {

        },

        drawLine() {
          console.log("draw");
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
