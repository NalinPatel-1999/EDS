<%@page import="com.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<! DOCTYPE HTML>
<html>
<head lang="en">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="vendors/css/normalize.css">
<link rel="stylesheet" type="text/css" href="vendors/css/Grid.css">
<link rel="stylesheet" type="text/css" href="vendors/css/animate.css">
<link rel="stylesheet" type="text/css"
	href="vendors/css/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/css/queries.css">

<link
	href="https://fonts.googleapis.com/css2?family=Lato:ital,wght@0,100;0,300;0,400;1,300&display=swap"
	rel="stylesheet">
<title>Welcome to EDS</title>

<!--  Favicon -->
<script type="text/javascript" src="js/validate.js"></script>
<link rel="apple-touch-icon" sizes="57x57"
	href="resources/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="resources/favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="resources/favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="resources/favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="resources/favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="resources/favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="resources/favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="resources/favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180"
	href="resources/favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"
	href="resources/favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="resources/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="resources/favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="resources/favicon/favicon-16x16.png">
<link rel="manifest" href="resources/favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="resources/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

<style>
header {
	background-size: 20% 10%;
	background-repeat: no-repeat;
	/*background-image:-webkit-gradient(linear, left top, left bottom, from(rgba(0, 0, 0, 0.7)), to(rgba(0, 0, 0, 0.7))),url(img/hero5.png);*/
	/* background-image:linear-gradient(rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8)),url(img/hero5.png);*/
	background-size: cover;
	background-position: center;
	height: 50vh;
	background-attachment: fixed;
}

.hero-text-box {
	position: absolute;
	width: 1140px;
	top: 30%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
</style>
</head>

<body>
	<header>
		<nav>
			<div class="row">
				<img src="resources/img/logo-white.png" alt="EDS logo" class="logo">
				<img src="resources/img/logo.png" alt="EDS logo" class="logo-black">

				<ul class="main-nav js--main-nav">
					<li><a href="#login">Encrypt My Text</a></li>
					<li><a href="#login">Decrypt My Text</a></li>

				</ul>
				<a class="mobile-nav-icon js--nav-icon"><i
					class="ion-navicon-round"></i></a>
			</div>
		</nav>
		<div class="hero-text-box">
			<h1>
				Secure Your Data With US.<br>Send it Confidentially.
			</h1>
		</div>
	</header>

	<!-- Login form  -->

	<%
session=request.getSession(false);

if(session.getAttribute("bean")!=null){
	UserBean bean=(UserBean)session.getAttribute("bean");

%>

	<section class="section-form">
		<div class="row">
			<h2>
				Welcome
				<%=" "+bean.getFirstName()+" " %>What You wanna Do Choose One
			</h2>
			<div class="row">
				<form method="get" action="welcomeCtl" class="contact-form"">

					<div class="row">


						<div class="row">
							<div class="col span-1-of-6">
								<input type="submit" name="operation" id="operation"
									value="Encrypt My Text">
							</div>

							<div class="col span-1-of-6">
								<label>&nbsp;</label>
							</div>

							<div class="col span-1-of-6">
								<label>&nbsp;</label>
							</div>

							<div class="col span-1-of-6">
								<label>&nbsp;</label>
							</div>

							<div class="col span-1-of-6">
								<input type="submit" name="operation" id="operation"
									value="Decrypt My Text">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>

	<%}else{
	
	response.sendRedirect("index.jsp");
	
} %>

	<!-- Footer   -->

	<footer>
		<div class="row">
			<div class="col span-1-of-2">
				<ul class="footer-nav">
					<li><a href="other-pages/about-us.html">About Us</a></li>
					<li><a href="#">Blog</a></li>
					<li><a href="#">Press</a></li>
					<li><a href="#">iOS App</a></li>
					<li><a href="#">Android App</a></li>
				</ul>

			</div>
			<div class="col span-1-of-2">
				<ul class="social-links">
					<li><a href="#"><i class="ion-social-facebook"></i></a></li>
					<li><a href="#"><i class="ion-social-twitter"></i></a></li>
					<li><a href="#"><i class="ion-social-googleplus"></i></a></li>
					<li><a href="#"><i class="ion-social-instagram"></i></a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<p>Copyright &copy; 2021 Encryption &mdash; Decryption System.
				All rights reserved.</p>
		</div>
	</footer>


	<!--   Script section   -->

	<script src="//cdn.jsdelivr.net/respond/1.4.2/respond.min.js"></script>
	<script src="//cdn.jsdelivr.net/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="//cdn.jsdelivr.net/selectivizr/1.0.3b/selectivizr.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="vendors/js/jquery.waypoints.min.js"></script>
	<script src="resources/js/script.js"></script>

</body>



</html>