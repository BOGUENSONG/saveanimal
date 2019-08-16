<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>테스트 페이지</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <link href="/resources/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Gamja+Flower&display=swap" rel="stylesheet">
    <style type="text/css">
        #titleWrap{
            height:50%;
        }
        #title{
            font-family: 'Gamja Flower', cursive;
            text-align: center;
            padding: 100px 0px;
            font-size: 30px;
            color: white;
            text-shadow: 0 0 10px black;
        }
        #btnWrap{
            text-align: center;
        }


        body{

            background-image: url('/resources/img/main.jpg');
            background-size: cover;
        }

        button{
            border-radius: 10px;
            box-shadow: 2px 2px 2px 2px lightgray;
            background: deepskyblue;
            color:#fff;
            border:none;
            margin: 10px;
            width: 70vw;
            position:relative;
            font-family: 'Gamja Flower', cursive;
            height: 50px;
            font-size:1.6em;
            padding:0 2em;
            cursor:pointer;
            transition:800ms ease all;
            outline:none;
        }
        button:hover{
            background:#fff;
            color: deepskyblue;
        }
        button:before,button:after{
            content:'';
            position:absolute;
            top:0;
            right:0;
            height:2px;
            width:0;
            background: deepskyblue;
            transition:400ms ease all;
        }
        button:after{
            right:inherit;
            top:inherit;
            left:0;
            bottom:0;
        }
        button:hover:before,button:hover:after{
            width:100%;
            transition:800ms ease all;
        }
    </style>
</head>
<body>
<div id="titleWrap">
    <div id="title">전국 유기동물 보호현황</div> </div>
<div id="btnWrap">
    <button type="button" value="강아지"  onclick="test1()">강아지</button>
    <button type="button" value="고양이"  onclick="test2()">고양이</button>
    <button type="button" value="기타동물"  onclick="test3()">기타동물</button>
</div>
<script>
function test1(){
    location.href = "/dogs";
}
function test2() {
    location.href = "/cats";
}
function test3() {
    location.href = "/others";
}
</script>
</body>
</html>