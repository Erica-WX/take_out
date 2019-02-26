<template>
  <memberNavi>
    <div  style="display: flex">
      <div>
        <foodInfo :food_info="item" v-for="item in food_list" :key="item.name" v-on:getFoodEvent="add_basket"></foodInfo>
      </div>
      <div>
        <el-button v-on:click="get_basket">查看购物车</el-button>
      </div>
    </div>
  </memberNavi>

</template>

<script>

    import foodInfo from '../components/foodInfo'
    import memberNavi from '../components/memberNavi'
    export default {
      name: "rest-info",
      components: {foodInfo, memberNavi},
      mounted:function () {
        this.id = this.$route.params.id;
        this.name = this.$route.params.name;
        console.log(this.id);
        console.log(this.name);
        this.get_food_list();

        let basket = this.$route.params.basket;
        console.log(basket);
        if(basket === undefined){
          basket = [];
        }

        this.basket = basket;
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
          },
          food_list:[
            {
              name:'精品冒菜',
              type:'主食',
              image:'http://localhost:8000/images/upload/1.jpg',
              price:20,
              amount:1000,
              num:1
            },
            {
              name:'里脊肉',
              type:'小食',
              image:'http://localhost:8000/images/upload/1.jpg',
              price:4,
              amount:1000,
              num:1
            }
          ],
          basket:[]
        }
      },
      methods: {
        get_food_list() {
          let id = this.id;
          let self = this;
          this.$axios.get('/rest/get_food_list',{
            params:{
              id:id
            }
          }).then(
            function (response) {
              console.log(response.data);
              self.food_list = response.data;
            }
          ).catch(function (error) {
            console.log(error);
          })
        },
        add_basket:function(food) {
          if(food.num > food.amount){
            alert("数量不足！");
          }else {
            let f = {
              id: food.id,
              name: food.name,
              price: food.price * food.num,
              cost: food.price,
              num: food.num,
              amount: food.amount,
            };
            this.check_basket(f);
          }
        },
        check_basket(f) {

          if(this.basket.length === 0){
            this.basket.push(f);
          }else {
            let isNew = true;
            for(let i = 0; i < this.basket.length; i++) {

              if(this.basket[i].name === f.name) {
                isNew = false;
                let sum = this.basket[i].num + f.num;
                if(sum <= f.amount){
                  this.basket[i].num = sum;
                  this.basket[i].price += f.price;
                  break;
                }else{
                  alert("数量不足！");
                  break;
                }
              }else {
                isNew = true;
              }
            }

            if(isNew) {
              this.basket.push(f);
            }
          }
          /*console.log(this.basket);*/
        },
        get_basket() {
          this.$router.push({name: 'basket', params:{basket: this.basket, id: this.id}});
        }
      }
    }
</script>

<style scoped>

</style>
