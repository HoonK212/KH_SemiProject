<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" type="text/css"  href="/resources/css/board/board.css" >   

<tr><th colspan="2">답변 내용</th></tr>
<tr><td colspan="2"><textarea cols="112" rows="10" disabled="disabled" style="padding: 20px;"><c:out value="${board.bdAnswer}" /></textarea></td></tr>


