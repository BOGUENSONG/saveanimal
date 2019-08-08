<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>테스트 페이지</title>
    <link href="/resources/css/base.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        #titleWrap{
            height:50%;
        }
        #title{
            text-align: center;
            padding: 100px 0px;
            font-size: 20px;
        }
        #btnWrap{
            text-align: center;
        }
        .button1{
            width:150px;
            height: 100px;
            margin: 0px 10px;
        }
        .button2{
            margin-top: 10px;
            width:325px;
            height: 50px;
        }


    </style>
</head>
<body>
<div id="titleWrap">
    <div id="title">전국 유기동물 보호현황</div> </div>
<div id="btnWrap">
    <input type="button" value="강아지" class="button1" onclick="test1()">
    <input type="button" value="고양이" class="button1">
    <br>
    <input type="button" value="기타동물" class="button2">
    <br>
    <input type="button" value="즐겨찾기" class="button2">
</div>
<script>
function test1(){
    location.href = "/dogs";
}
</script>
</body>
</html>