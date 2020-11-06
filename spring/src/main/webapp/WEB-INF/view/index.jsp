<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Login</title>
  <link href="index.css" rel="stylesheet" type="text/css">
</head>

<body>
<video autoplay loop muted>
  <source src="https://i.imgur.com/2ttGtCs.mp4" type="video/mp4"/>
</video>
<section class="cardReg">
  <header>
    <h1>New here? <a href="#">Create Account</a></h1>
  </header>
</section>
<section class="cardTitle">
  <div class="bg"></div>
  <header class="loginTitle">
    <h1>Login</h1>
  </header>
  <section class="cardLayout">
    <header class="cardRegister">
    </header>
    <section class="loginPanel">
      <form action="Login" method="post">
        用户名：<input type="text" name="username"/><br/>
        密码：<input type="password" name="password"/><br/>
        <input type="submit" value="登录">
      </form>
    </section>
    <section class="loginbutton">
      <div></div>
      <button> <span>Continue</span></button>
    </section>
  </section>
</section>
</body>
</html>