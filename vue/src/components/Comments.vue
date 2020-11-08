<template>
<transition name="fade">
    <div class="dialog-mask" v-show="showDialog">
        <div class="c-window">
        <div class="c-header">
            <span class="c-title">Comments</span>
            <span class="c-author">{{currentComment.authorName}}</span>
        </div>
        <div class="c-container">
            <div class="c-box">
                <button id="button-previous-comment" @click="prevComment">＜</button>
                <div class="c-content">
                    <p>{{currentComment.content}}</p>
                </div>
                <button id="button-next-comment" @click="nextComment">＞</button>
            </div>
            <img id="button-up-vote" src="https://www.flaticon.com/svg/static/icons/svg/1152/1152935.svg" />
        </div>
        <button style="font-size: xx-large; color: white;" @click="$emit('close-comments')">×</button>
        </div>
    </div>
</transition>
</template>

<script>
module.exports = {
    data: function () {
        return {
            currentComment: {id:0, authorName:'Author 1', content:'comment 1'},
            commentList:[
                {id:0, authorName:'Author 1', content:'comment 1'},
                {id:1, authorName:'Author 2', content:'comment 2'},
                {id:2, authorName:'Author 3', content:'comment 3'},
            ],
        }
    },
    props: {
        showDialog: Boolean,
    },
    methods: {
        prevComment: function(){
            if(this.currentComment.id==0){
                alert("You've reached the first comment");
            } else {
                this.currentComment = this.commentList[this.currentComment.id-1];
            }
        },
        nextComment: function(){
            if(this.currentComment.id==this.commentList.length-1){
                alert("You've reached the end");
            } else {
                this.currentComment = this.commentList[this.currentComment.id+1];
            }
        },
    },
}
</script>

<style scoped>
.c-title, .c-author {
   color: white;
}
.c-title {
    position: relative;
    left: -120px;
}
.c-author{
    font-size: medium;
}

.dialog-mask {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 1110;
    text-align: center;
    opacity: 0.95;
    transform: scale(1);
    background: rgba(0, 0, 0, 0.6);
}

.c-window {
    width: 600px;
    height: 400px;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -200px;
    margin-left: -300px;
}

.c-header {
    display: flex;
    justify-content: space-between;
    align-items: baseline;
    margin-bottom: -10px;
}

.c-container {
    border: 2px solid red;
    width: 100%;
    opacity: 1;
    padding-bottom: 10px;
}

.c-box {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.c-content {
    height: 200px;
    width: 85%;
    text-align: center;
    overflow: scroll;
    margin-top: 30px;
    margin-bottom: 10px;
}

.c-content p {
    font-family: 'Courier New', Courier, monospace;
    word-break: break-all;
}

button {
    background-color: transparent;
    border: none;
    border-radius: 10px;
    outline: transparent;
    margin: 10px;
}

button:hover {
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}

#button-up-vote {
    width: 8%;
    display: block;
    margin-left: auto;
    margin-right: auto;
    padding: 4px;
}
#button-up-vote:hover {
    border-radius: 10px;
    box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}

#button-previous-comment, #button-next-comment {
    height: 200px;
}

.fade-enter-active, .fade-leave-active{
    transition: opacity .3s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8*/ {
    opacity: 0;
}
</style>