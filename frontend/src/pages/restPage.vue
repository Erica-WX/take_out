<template>
    <restNavi paneltitle="我的餐厅 > 基本信息">
      <div>
        <div>
          餐厅名：{{this.rest_info.name}}
        </div>
        <div>
          餐厅识别码：{{this.rest_info.id}}
        </div>
        <div>
          餐厅地址：{{this.rest_info.address}}
        </div>
        <div>
          餐厅类型：{{this.rest_info.type}}
        </div>
      </div>
    </restNavi>
</template>

<script>

    import restNavi from '../components/restNavi'
    export default {
      name: "rest-page",
      components:{restNavi},
      mounted:function () {
        let rest = this.$route.params.rest;
        this.rest_info = rest;
      },
      data() {
        return {
          rest_info: {
            id:'',
            name:'',
            address:'',
            type:''
          }
        }
      },
      methods: {

      }
    }
</script>

<style scoped>

</style>

<style>

</style>
