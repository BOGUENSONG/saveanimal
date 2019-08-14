<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>테스트 페이지</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style type="text/css">
        .dateform{
            min-width:450px;
        }
        #back{
            margin-left: 10px;
            margin-top: 10px;
        }
        #back:hover{
            cursor: pointer;
        }
        .petTitle{
            margin-top:10px;
            margin-left: 40%;
            color: black;
            width: 40px;
        }
        body{
            margin: 0 auto;
        }
        #header{
            height: 60px;
            color: white;
            background: linear-gradient( to bottom, skyblue, white );

        }
        #selectBox{
            height:50px;
            color: white;
            background-color: deepskyblue;
        }
        .selectBox{
            width: 30%;
        }
        .petWrap,.kindCd,.age,.sexCd,.noticeSdt{
            display:inline-block;
            width: 140px;
        }
        .petWrap{
            border: 3px double deepskyblue;
            border-radius: 5px;
            padding: 5px;
            width: 310px;
        }
        .filename{
            width:150px;
            display:inline-block;;
        }
        .fileInfo{
            border-left: 2px solid black;
            width:130px;
            display:inline-block;
            height: 130px;
            float: right;
            padding: 10px;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.8);
        }

        .modal-content {
            color: black;
            text-align: center;
            background-color: #fefefe;
            margin : auto;
            margin-top : 375px;
            width: 1000px;
            height: 1000px;
            border: 1px solid #888;
        }
        .modal-content img{
            height: 500px;
            width: 500px;
        }

    </style>
</head>
<body>
<div id="header"> <img src="/resources/img/arrow-alt-circle-left-solid.svg" width="40px" id="back">
    <img src="/resources/img/<c:out value="${pet}"/>-solid.svg" class="petTitle"> </div>

<div id="selectBox">
    <select name="sido" class="selectBox" id="sido">
        <option>==>선택<==</option>
        <c:forEach var="entry" items="${sido}">
            <option value="<c:out value="${entry.
            key}"/>"><c:out value="${entry.value}"/> </option>
        </c:forEach>
    </select>
    <select name="sigungu" class="selectBox" id="sigungu">
    </select>
    <select name="bohoso" class="selectBox" id="bohoso">
    </select>
    <div class="dateform">
        시작날짜: <input type="date" id="startDate"> 끝날짜: <input type="date" id="endDate"> <input type="button" value="조회" onclick="getPetList()">
    </div>
    <div id="Modal1" class="modal">
        <div id = "Modal2" class="modal-content">
            <button type="button" onclick="modalOff()">종료</button>
        </div>
    </div>
</div>

<div id="petlist">
</div>
    <script>
        var modal1 = document.getElementById('Modal1');
        var modal2 = document.getElementById('Modal2');

        function addList(selector, value , clss) {
            if(clss == "filename") {
                var all = document.createElement('div');
                all.setAttribute("class",clss);
                var pic = document.createElement('img');
                pic.setAttribute("src",value);
                pic.setAttribute("style","width:150px; height:140;");
                all.append(pic);
                selector.append(all);
            }
            else {
                var all = document.createElement('div');
                all.setAttribute("class",clss);
                all.appendChild(document.createTextNode(value));
                selector.append(all);
            }

        }
        function getPetList() {
            $.ajax({
                url: "/listReturn",
                type: "get",
                dataType: "json",
                contentType:'application/json; charset=utf-8',
                data: { "sigungu": $('#sigungu').val(), "bohoso": $('#bohoso').val(), "upkind": "<c:out value="${petkind}"/>", "bgnde": $('#startDate').val(), "endde":$("#endDate").val() },
                success: function(data) {
                    $('#petlist').empty();
                    for (i = 0 ; i < data.length; i++) {
                        var listDiv = document.createElement('div');
                        var fileInfo = document.createElement('div');
                        listDiv.setAttribute("class","petWrap");
                        fileInfo.setAttribute("class","fileInfo");
                        listDiv.setAttribute("onclick","modalOn('"+data[i].popfile+"', '"+data[i].noticeNo+"','"+data[i].kindCd+"', '"+data[i].age+ "', '"+data[i].weight+"')");
                        addList(listDiv,data[i].filename,'filename');

                        addList(fileInfo,data[i].kindCd,'kindCd');
                        addList(fileInfo,data[i].age,'age');
                        addList(fileInfo,data[i].sexCd,'sexCd');
                        addList(fileInfo,data[i].noticeSdt,'noticeSdt');
                        listDiv.append(fileInfo);
                        $('#petlist').append(listDiv);

                    }
                },
                error:function(error) {
                    alert("에러발생");
                }
            });
        }

        function modalOn(popfile, noticeNo, kindCd, age, weight) {
            var nn = document.createElement("p"); //공고번호
            nn.setAttribute("id","mnoticeNo");
            nn.innerText = noticeNo;
            modal2.appendChild(nn);
            var pf = document.createElement("img"); //이미지
            pf.setAttribute("id","mpopfile");
            pf.src = popfile;
            modal2.appendChild(pf);
            var kc = document.createElement("p"); //품종
            kc.setAttribute("id","mkindCd");
            kc.innerText = kindCd;
            modal2.appendChild(kc);
            var ag = document.createElement("p"); //나이
            ag.setAttribute("id","mage");
            ag.innerText = age;
            modal2.appendChild(ag);
            var wg = document.createElement("p"); //무게
            wg.setAttribute("id","mweight");
            wg.innerText = weight;
            modal2.appendChild(wg);
            modal1.style.display = "block";
        }

        function modalOff() {
            var pf = document.getElementById("mpopfile");
            modal2.removeChild(pf);
            var nn = document.getElementById("mnoticeNo");
            modal2.removeChild(nn);
            var kc = document.getElementById("mkindCd");
            modal2.removeChild(kc);
            var ag = document.getElementById("mage");
            modal2.removeChild(ag);
            var wg = document.getElementById("mweight");
            modal2.removeChild(wg);
            modal1.style.display = "none";
        }

        function getBohoso() {
            $.ajax({
                url: "/bohoso",
                type: "get",
                dataType: "json",
                contentType:'application/json; charset=utf-8',
                data: { "sido":  $('#sido').val() , "sigungu": $('#sigungu').val()},
                success: function(data) {
                    $('#bohoso').empty();
                    var all = document.createElement('option');
                    all.appendChild(document.createTextNode("==>선택<=="));
                    $('#bohoso').append(all);
                    var all = document.createElement('option');
                    all.setAttribute("value","");
                    all.appendChild(document.createTextNode("전체"));
                    $('#bohoso').append(all);
                    for ( var key in data) {
                        var option = document.createElement('option');
                        option.setAttribute("value",key);
                        option.appendChild(document.createTextNode(data[key]));
                        $('#bohoso').append(option);
                    }
                },
                error:function(error) {
                    alert("에러발생");
                }
            });
        }

        function getSigungu() {
            $.ajax({
                url: "/sigungu",
                type: "get",
                dataType: "json",
                contentType:'application/json; charset=utf-8',
                data: { "sido":  $('#sido').val() },
                success: function(data) {
                    $('#sigungu').empty();
                    var all = document.createElement('option');
                    all.appendChild(document.createTextNode("==>선택<=="));
                    $('#sigungu').append(all);
                    for ( var key in data) {
                        var option = document.createElement('option');
                        option.setAttribute("value",key);
                        option.appendChild(document.createTextNode(data[key]));
                        $('#sigungu').append(option);
                    }
                },
                error:function(error) {
                    alert("에러발생");
                }
            });
        }

        $('#back').on('click',function(){
            history.back();
        })
        $(document).ready(function() {
            var today = new Date();
            today.setMonth(today.getMonth()-1);
            document.getElementById('endDate').valueAsDate = new Date();
            document.getElementById('startDate').valueAsDate = today;
            $('#sido').change(function(){
                if($('#sido').val() != "==>선택<==") {
                    getSigungu();
                }
            })
            $('#sigungu').change(function(){
                if($('#sigungu').val() != "==>선택<==") {
                    getBohoso();
                }
            })
            $('#bohoso').change(function() {
                if($('#bohoso').val() != "==>선택<==") {
                    getPetList();
                }
            })
        })
    </script>
</body>

</html>