<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<style type="text/css">
    .menu a{cursor:pointer;}
    .menu .hide{display:none;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $(".menu>a").click(function(){
            $(this).next("ul").toggleClass("hide");
        });
    });
</script>
</head>
<body>
<div>
    <ul>
        <li class="menu">
            <a><img src="" alt="상위메뉴이미지1"/></a>
            <ul class="hide">
                <li>메뉴1-1</li>
                <li>메뉴1-2</li>
                <li>메뉴1-3</li>
                <li>메뉴1-4</li>
                <li>메뉴1-5</li>
                <li>메뉴1-6</li>
            </ul>
        </li>
 
        <li class="menu">
            <a><img src="" alt="상위메뉴이미지2"/></a>
            <ul class="hide">
                <li>메뉴2-1</li>
                <li>메뉴2-2</li>
                <li>메뉴2-3</li>
                <li>메뉴2-4</li>
                <li>메뉴2-5</li>
                <li>메뉴2-6</li>
            </ul>
        </li>
    </ul>
</div>


</body>
</html>