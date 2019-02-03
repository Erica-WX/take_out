<template>
    <div>
      <topBar></topBar>
      <div class="back">
        <div class="main">
          <div>
            <h1 class="title">欢迎来到我们的外卖平台Take Out！</h1>
          </div>
          <div class="enter">
            <el-button>点击进入</el-button>
          </div>
        </div>

      </div>
    </div>
</template>
<script>
    import topBar from '../components/topBar'
    export default {
      name: "index",
      components: {topBar},
      methods: {
        test(){
          console.log("in test");
          this.$axios.post('/test/str').then(
            function (response) {
              console.log(response.data);
            }
          );
        },
      }
    }


</script>

<style scoped>
  .back{
    height: 700px;
    background-image: url("/static/index-back.jpg");
    background-size:100% 100%;
    border: 1px solid black;
    /*opacity: 0.35;*/
  }

  .main{
    margin-top: 10%;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    width: 50%;
    height: 45%;
    border: 1px solid #ce9b5f;
    background-color: dodgerblue;
    opacity: 0.8;
  }

  .title{
    margin-top: 10%;
    color: white;
  }

  .enter{
    margin-top: 100px;
  }

</style>
