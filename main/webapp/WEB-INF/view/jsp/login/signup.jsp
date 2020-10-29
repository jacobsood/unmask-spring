<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Form-v7 by Colorlib</title>
	<!-- Mobile Specific Metas -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- Font-->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/loginTheme/css/opensans-font.css" />">
	<!-- Main Style Css -->
    <link rel="stylesheet" href="<c:url value="/resources/loginTheme/css/style.css" />"/>
</head>
<body class="form-v7">
	<div class="page-content">
		<div class="form-v7-content">
			<div class="form-left">
				<img src="<c:url value="/resources/loginTheme/images/form-v7.jpg" />" alt="form">
				<p class="text-1">Sign Up</p>
				<p class="text-2">Privaci policy & Term of service</p>
			</div>
			<form class="form-detail" action="registration" method="post" id="myform">
				<div class="form-row">
					<label for="username">USERNAME</label>
					<input type="text"  name="username" id="username" class="input-text">
				</div>
				<div class="form-row">
					<label for="yourEmail">E-MAIL</label>
					<input type="text" name="yourEmail" id="yourEmail" class="input-text" required pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}">
				</div>
				<div class="form-row">
					<label for="password">PASSWORD</label>
					<input type="password" name="password" id="password" class="input-text" required>
				</div>
				<div class="form-row">
					<label for="confirmPassword">CONFIRM PASSWORD</label>
					<input type="password" name="confirmPassword" id="confirmPassword" class="input-text" required>
				</div>
				<div class="form-row-last">
					<input type="submit" name="register" class="register" value="Register">
					<!--<p>Or<a href="#">Sign in</a></p>-->
				</div>
			</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
	<script src="<c:url value="/resources/loginTheme/js/utils.js" />"></script>

</body>
</html>
