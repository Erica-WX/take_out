<template>
  <adminNavi paneltitle="审核信息">
    <div style="margin-top: 20px; margin-left: 85%">
      <router-link :to="{name:'adminPage'}" style="color: #409EFF">>>>返回审核列表</router-link>
    </div>
    <div style="margin-top: 20px; display: flex">

      <div class="box" >
        <el-card>
          <div slot="header" class="clearfix">
            <span>修改前</span>
          </div>
          <div align="center">
            <div class="title">餐厅名称：{{this.before_info.name}}</div>
            <div class="title">所在区县：{{this.before_info.district}}</div>
            <div class="title">详细地址：{{this.before_info.address}}</div>
            <div class="title">餐厅类型：{{this.before_info.type}}</div>
          </div>
        </el-card>
      </div>
      <div class="box" style="margin-left: 50px">
        <el-card >
          <div slot="header" class="clearfix">
            <span>修改后</span>
          </div>
          <div align="center">
            <div class="title">餐厅名称：{{this.after_info.restName}}</div>
            <div class="title">所在区县：{{this.after_info.district}}</div>
            <div class="title">详细地址：{{this.after_info.address}}</div>
            <div class="title">餐厅类型：{{this.after_info.type}}</div>
          </div>
        </el-card>
      </div>
    </div>
    <div style="margin-top: 60px; margin-left: 38%">
      <el-button type="primary" v-on:click="approve">同意</el-button>
      <el-button type="danger" v-on:click="reject">驳回</el-button>
    </div>
  </adminNavi>

</template>

<script>
    import adminNavi from '../components/adminNavi'
    export default {
      name: "approve-modify-info",
      components: {adminNavi},
      mounted: function () {
        this.mid = this.$route.params.mid;
        this.get_info();
      },
      data() {
        return {
          mid: 0,
          before_info: {},
          after_info: {},
        }
      },
      methods: {
        get_info() {
          let mid = this.mid;
          let self = this;
          this.$axios.get('/admin/get_modify_info', {
            params: {
              mid: mid
            }
          }).then(
            function (response) {

              self.after_info = response.data;
              let restId = response.data.restId;

              let self2 = self;
              self.$axios.get('/rest/login', {
                params: {
                  id: restId
                }
              }).then(
                function (response) {

                  self2.before_info = response.data;
                }
              ).catch(function (error) {
                console.log(error);
              })
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        approve() {
          let mid = this.mid;
          let self = this;
          this.$axios.get('/admin/approve_modify' ,{
            params: {
              mid: mid
            }
          }).then(
            function (response) {
              let self2 = self;
              self.$alert('审核成功！', '', {
                confirmButtonText: '确定',
                callback: action => {
                  self2.$router.push({name: 'adminPage'})
              }
              })
            }
          ).catch(function (error) {
            console.log(error);
          })
        },

        reject() {
          let mid = this.mid;

          let self = this;
          this.$axios.get('/admin/reject_modify', {
            params: {
              mid: mid
            }
          }).then(
            function (response) {

              let self2 = self;
              self.$alert('驳回成功！', '' ,{
                confirmButtonText: '确定',
                callback: action => {
                  self2.$router.push({name: 'adminPage'});
                }
              })
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
    width: 370px;
  }

  .title {
    color: #7e7e7e;
    font-size: 20px;
    margin-bottom: 20px;
  }
</style>
