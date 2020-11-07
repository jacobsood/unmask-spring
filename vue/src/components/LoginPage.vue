<template>
  <div>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="100px" class="login-box" :label-position="labelPosition">
      <h2 class="login-title">WELCOME</h2>
      <el-form-item label="USERNAME" prop="username" >
        <el-input type="text" class="loginInput" placeholder="" v-model="form.username"/>
      </el-form-item>
      <el-form-item label="PASSWORD" prop="password">
        <el-input type="password" class="loginInput" placeholder="" v-model="form.password"/>
      </el-form-item>
    </el-form>
    <div>
    <el-button type="text" v-on:click="onSubmit('loginForm')">LOGIN</el-button>
    </div>
    <div>
    <el-button type="text" v-on:click="signup">SIGN UP</el-button>
    </div>
    <el-dialog
      title="ERROR"
      :visible.sync="dialogVisible"
      width="30%"
      >
      <span>{{status}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="text" @click="dialogVisible = false">OK</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="WELCOME"
      :visible.sync="loginVisible"
      width="30%"
      >
      <span>WELCOME BACK {{form.username}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="text" @click="backToHome">OK</el-button>
      </span>
    </el-dialog>
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
            window.location.href="http://localhost:8080/signup"
        },
        backToHome(){
            this.$router.push("/home");
        }
    }
  }
</script>

<style scoped lang='scss'>

@import "~@/assets/scss/_typo.scss";
h2{
    position: relative;
    padding:2rem 0;
}
.el-button{
    width:100%;
    border-color: rgb(114, 0, 0);
    margin: 5px;
}
.el-button:hover {
    border-color: red;

}




// your style goes in here
// alternative you can create your own scss file and import it like above

</style>
