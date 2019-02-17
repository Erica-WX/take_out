<template>
  <foodInfo></foodInfo>
</template>

<script>

    import foodInfo from '../components/foodInfo'
    export default {
      name: "rest-info",
      components: {foodInfo},
      mounted:function () {
        this.id = this.$route.params.id;
        this.name = this.$route.params.name;
        console.log(this.id);
        console.log(this.name);
        this.get_food_list();
      },
      data() {
        return {
          id:'',
          name:'',
          food_info:{
            name:'',
            type:'',
            price:0.0,
            image:''
          }
        }
      },
      methods: {
        get_food_list() {
          let id = this.id;
          this.$axios.get('/rest/get_food_list',{
            params:{
              id:id
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
