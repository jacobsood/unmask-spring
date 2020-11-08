<template>
  <div class="login-container">
    <div>
      <el-form ref="loginForm" :model="form" :rules="rules"  class="login-box" :label-position="labelPosition" hide-required-asterisk>
      <h2 class="login-title">WELCOME</h2>
      <el-form-item label="" prop="username" >
        <span class="form-tiem-title">USERNAME</span>
        <el-input type="text" class="loginInput" placeholder="" v-model="form.username"/>
      </el-form-item>
      <el-form-item label="" prop="password"  >
        <span class="form-tiem-title">PASSWORD</span>
        <el-input type="password" class="loginInput" placeholder="" v-model="form.password"/>
      </el-form-item>
    </el-form>
    </div>
    <div>
    <el-button type="text" v-on:click="onSubmit('loginForm')" >LOGIN</el-button>
    </div>
    <div>
    <el-button type="text" v-on:click="signup">SIGN UP</el-button>
    </div>
      <div>
    <el-dialog
      title="ERROR"
      :visible.sync="dialogVisible"
      width="30%"
      custom-class="dialog"
      >
      <span>{{status}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="text" @click="dialogVisible = false">OK</el-button>
      </span>
    </el-dialog>
      </div>
  <div>
    <el-dialog
      title="WELCOME"
      :visible.sync="loginVisible"
      width="30%"
      customClass="dialog"
      >
      <span>Welcome back {{form.username}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="text" @click="backToHome">OK</el-button>
      </span>
    </el-dialog>
  </div>
  </div>
</template>

<script>
import qs from 'qs'

  export default {
    name: "LoginPage",
    data() {
      return {
        labelPosition: 'left',
        form: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            {required: true, message: 'Username cannot be empty', trigger: 'blur'}
          ],
          password: [
            {required: true, message: 'Password cannot be empty', trigger: 'blur'}
          ]
        },
        dialogVisible: false,
        loginVisible:false,
        status:null
      }
    },
    methods: {
        onSubmit(formName) {
            var params = {
                username: this.form.username,
                password: this.form.password
            }
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$axios.post("/api/login",
                    qs.stringify(params)
                    ).then((response)=>{
                        if (response.data["loginStatus"]=="101"){
                                this.status = "Wrong password"
                                this.dialogVisible = true;
                                return false;
                            }else if(response.data["loginStatus"]=="100"){
                                this.status = "User not exist"
                                this.dialogVisible = true;
                                return false;
                            }else{
                                this.loginVisible = true
                                this.$cookies.set("loginStatus", response.data["loginStatus"])
                                this.$cookies.set("userEmail", response.data["userEmail"])
                                this.$cookies.set("userID", response.data["userID"])
                                this.$cookies.set("username", response.data["username"])
                                const secounds = 3
                                let num = 0
                                const timer = setInterval(() => {
                                  if(num < secounds) {
                                    num++
                                  }else {
                                    clearInterval(timer)
                                    this.$router.push("/home");
                                  }
                                }, 1000)
                                //$cookies.get(keyName)  // return value         
                            }
                    })
                        } else {
                            this.dialogVisible = true;
                            this.status = "Username of password could not be empty."
                            return false;
                        }
                    });
        },
        signup(){
            window.location.href="http://unmask.hrithviksood.me/signup"
        },
        backToHome(){
            this.$router.push("/home");
        }
    }
  }
</script>

<style lang='scss' scoped>

@import "~@/assets/scss/_typo.scss";

.el-button{
    font-family: CustomY78;
    letter-spacing: .2em;
    width:100%;
    border-color: rgb(114, 0, 0);
    margin-bottom: 1rem;
}
.el-button:hover {
    letter-spacing: .4em;
    border-color: red;

}

.login-title{
  line-height: 1.8;
  font-size: 2em;
  font-family: CustomY78;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color:red; 
  margin-bottom:1%;
  letter-spacing: .3em;
  cursor: crosshair;
}

.form-tiem-title{
  font-size: 1em;
  letter-spacing: .3em;
  font-family: 'CustomY78';
}

</style>
<style lang='scss'>
.login-container{
border:1px solid #b30000;
padding:3rem 2rem;
}
.login-container:hover{
border:1px solid red;
padding:3.1rem 2.1rem;
}

.el-dialog{
  border:2px solid #b30000
}

.el-dialog__title{
  font-family: 'CustomY78';
  font-size: 2em;
  letter-spacing: .15em;
  color:red !important;
}
.el-dialog__body{
  font-family: 'CustomY78';
  font-size: 1em;
  letter-spacing: .15em;
}
</style>
