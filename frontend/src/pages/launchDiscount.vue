<template>
    <restNavi paneltitle="发布信息 > 发布优惠">
      <div>
        <el-card>

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

      },
      data() {

      },

      methods:{
        get_discount_list() {
          let rest_id = localStorage.rest_id;
          this.$axios.get("",{
            params:{
              restId: rest_id
            }
          }).then(
            function (response) {
              console.log(response.data);
            }
          ).catch(function (error) {
            console.log(error);
          })
        }
      }
    }
</script>

<style scoped>

</style>
