<template>
  <adminNavi paneltitle="审核信息">
    <div>
      <el-table
        :data="abstract_list"
        stripe
      >
        <el-table-column
          prop="restName"
          label="餐厅名称"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="requestTime"
          label="请求时间"
          align="center"
        >
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
        >
          <template slot-scope="scope">
            <el-button size="small" @click="get_info_detail(scope.row.mid)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </adminNavi>
</template>

<script>
    import adminNavi from '../components/adminNavi'
    export default {
      name: "admin-page",
      components: {adminNavi},
      mounted: function () {
        this.get_modify_info();
      },
      data() {
        return {
          abstract_list: []
        }
      },
      methods: {
        get_modify_info() {

          let self = this;
          this.$axios.post('/admin/get_abstract').then(
            function (response) {
              self.abstract_list = response.data;
              for(let i = 0; i < self.abstract_list.length; i++) {
                let time = self.abstract_list[i].requestTime + "";
                time = time.substring(0, 10) + " " + time.substring(11,20);
                self.abstract_list[i].requestTime = time;
              }
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        get_info_detail(mid) {
          this.$router.push({name: 'approveModifyInfo', params: {mid: mid}})
        }
      }
    }
</script>

<style scoped>

</style>
