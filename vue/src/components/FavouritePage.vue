<template>
<div class="container">
<h1>Favourite</h1>
<el-card class="box-card">
  <h3 v-for="(item,index) in historyList" :key="index" class="text item">
    {{"Article"+item["id"]+" "+item["source"]+" "+item["text"]+" "+item["title"]}}

  </h3>
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

  .box-card {
    width: 100%;
    height: 100%;
  }
  .container{
      width: 100%;
    height: 100%;
  }

</style>