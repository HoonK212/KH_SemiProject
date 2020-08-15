<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modal Test</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<style type="text/css">
.mo_button {
	all:unset;
	padding: 5px 20px;
	border-radius: 5px;
	position: absolute;
	top: 25px;
	right: 50px;
	color: white;
	background-color: steelblue;
	cursor: pointer;
}
.modal {
	position: fixed;
	top:0;
	left: 0;
	width: 100%;
	height: 100%;
 	min-width: 600px;
 	min-height: 700px;
	display: flex;
	justify-content: center;
	align-items: center;
}
.mo_overlay {
	position: absolute;
	width: 100%;
	height: 100%;
	background-color: rgba(0,0,0,0.6);
}
.mo_content {
	padding: 50px 100px;
	border-radius: 10px;
	position: relative;
	width: 60%;
	height: 70%;
 	min-width: 600px; 
  	min-height: 700px; 
	text-align: center;
	background-color: #DDD;
	box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
}
.mo_hidden {
	display: none;
}
</style>

</head>
<body>

<button class="mo_button" id="mo_open">Modal Test</button>

<div class="modal mo_hidden" id="mo_wrapper">
	<div class="mo_overlay" id="mo_overlay"></div>
		<div class="mo_content">
			<h1 style="margin: 0;">Modal Test</h1>
			<button class="mo_button" id="mo_close">X</button>
		</div>
</div>

<script type="text/javascript">

$('#mo_open').on("click", function() {
	$('#mo_wrapper').removeClass("mo_hidden");
});

$('#mo_close').on("click", function() {
	$('#mo_wrapper').addClass("mo_hidden");
});

$('#mo_overlay').on("click", function() {
	$('#mo_wrapper').addClass("mo_hidden");
});

</script>

</body>
</html>