<template>
<div class="container">
<h1>Favourite</h1>
<el-card class="box-card card-Container">
  <el-card v-for="(item,index) in historyList" :key="index" class="box-card">
    <div slot="header" class="clearfix">
      <h2 class="article-title">{{item["title"]}}</h2>
      <el-button style="float: right; padding: 3px 0" type="text"> <h2 style="float: right">{{item["id"]}}</h2></el-button>
    </div>
    <span>{{item["text"]}}</span>
      <span style="float: right; padding: 3px 0">Source: {{item["source"]}}</span>
  </el-card>
</el-card>
</div>
</template>

<script>

export default {
    
    name: 'FavouritePage',
    data: function() {
        return {
            historyList:null,
            randomData: 8,
        }
    },
    created:  async function(){
        this.historyList=await this.getHistory()
    }
    ,
    methods: {
        getHistory:async function(){
            var res = this.$axios.post("/api/detailedLikeList",
            ).then((response)=>{
                return response.data
          
            })
            return await res
        },
        
    },
    computed: {
        // values used in the template
        // functions here are reactive - whenever a value is changed, 
        // the template will catch it automatically and update
        // methods here must return something
        getRandomData: function() {
            return this.randomData;
        }
    }
}
</script>

<style scoped lang='scss'>
@import "~@/assets/scss/_typo.scss";
h1 {
  line-height: 1.8;
  font-size: 2em;
  font-family: CustomY78;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color:red; 
  margin-bottom:1%;
  letter-spacing: .2em;
  cursor: crosshair;
}
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    position: relative;
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .card-Container {
    width: 100%;
    height: 100%;
    font-family: CustomY78;
    letter-spacing: .2em;
  }
  .container{
      width: 100%;
    height: 100%;
  }
  .box-card{
    margin-bottom: 2%;
  }
  .article-title{
    float: left;
    color:red;
  }

</style>