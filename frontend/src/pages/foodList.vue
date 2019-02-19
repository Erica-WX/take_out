<template>
    <div>
      <memberNavi paneltitle="附近餐厅">
        <div class="main-body">
          <div class="address">
            当前位置：{{now_address}} <router-link class="change" to="/selectAddress">[切换地址]</router-link>
          </div>

          <div class="pane">
            <div class="title">附近的餐厅：</div>
            <el-table
              :data="rest_list"
              stripe
              style="width: 100%"
            >
              <el-table-column
                prop="name"
                label="餐厅名称"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="address"
                label="餐厅地址"
                align="center"
              >
              </el-table-column>
              <el-table-column
                prop="type"
                label="餐厅类型"
                align="center"
              >
              </el-table-column>
              <el-table-column
                label="操作"
                align="center"
              >
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="getRestDetail(scope.row.id, scope.row.name)">进入餐厅</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div></div>
        </div>
      </memberNavi>

    </div>
</template>

<script>

    import memberNavi from '../components/memberNavi'
    export default {
      name: "food-list",
      components: {memberNavi},
      mounted:function () {

        let district = localStorage.district;
        let address = localStorage.address;
        this.now_address = district + " - " + address;

        let self = this;
        this.$axios.get('/rest/get_rests',{
          params:{
            district:district
          }
        }).then(
          function (response) {
            console.log(response.data);
            self.rest_list = response.data;
          }
        ).catch(function (error) {
          console.log(error);
        });

      },
      data() {
        return {
          now_address:'南京市-鼓楼区-南京大学鼓楼校区',
          rest_list:[],
        }
      },
      methods:{
        getRestDetail(id, name) {
          this.$router.push({name:'restInfo',params:{id:id, name:name}});
        },
      }
    }
</script>

<style scoped>
  .main-body{
    width: 100%;
    height: 700px;
    /*background-color: #f0f0f0;*/
    /*padding-left: 60px;*/
  }

  .address{
    color: #969696;
    font-size: 14px;
    height: 50px;
    line-height: 50px;
  }

  .change{
    color: #409EFF;
    cursor: pointer;
  }

  .title{
    font-size: 27px;
    margin-top: 20px;
    margin-bottom: 20px;
  }

  .pane{
    /*border:1px solid black;*/
    width: 700px;
    height: 400px;
    /*margin-left: 300px;*/
  }

</style>

<style>
  /*.el-menu.el-menu--horizontal{
    border-bottom:solid 1px #409EFF
  }*/
</style>
