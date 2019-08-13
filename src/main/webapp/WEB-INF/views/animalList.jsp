<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>테스트 페이지</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style type="text/css">
        body{
            margin: 0 auto;
        }
        #header{
            height: 80px;
            color: white;
            background-color:black;
        }
        #selectBox{
            height:50px;
            color: white;
            background-color: blueviolet;
        }
        .selectBox{
            width: 30%;
        }
    </style>
</head>
<body>
<div id="header">  </i> header</div>
<div id="selectBox">
    <select name="sido" class="selectBox" id="sido">
        <c:forEach var="entry" items="${sido}">
            <option value="<c:out value="${entry.key}"/>"><c:out value="${entry.value}"/> </option>
        </c:forEach>
    </select>
    <select name="sigungu" class="selectBox" id="sigungu">

    </select>
    <select name="bohoso" class="selectBox" id="bohoso">
        <%--        <c:forEach var="i" items="${test1}">--%>
        <%--            <option value="<c:out value="${i[1]}"/>"><c:out value="${i[0]}"/></option>--%>
        <%--        </c:forEach>--%>
    </select>

</div>






</div>

<div id="petlist">list

</div>
<%--<div> 테스트 :<c:out value="${test2}"></c:out></div>--%>
<%--<c:out value="${}"/>--%>



<script>

    function getPetList(){
        $.ajax({
            url: "/listReturn",
            type: "get",
            dataType: "json",
            contentType:'application/json; charset=utf-8',
            data: { "sigungu": $('#sigungu').val(), "bohoso": $('#bohoso').val(), "upkind": "417000"},
            success: function(data) {
                $('#petlist').empty();
                $('#petlist').html('<c:out value="${petList}"/>');

            },
            error:function(error){
                alert("에러발생");

            }

        });
    }

    function getBohoso(){
        $.ajax({
            url: "/bohoso",
            type: "get",
            dataType: "json",
            contentType:'application/json; charset=utf-8',
            data: { "sido":  $('#sido').val() , "sigungu": $('#sigungu').val()},
            success: function(data) {
                $('#bohoso').empty();
                var all = document.createElement('option');
                all.setAttribute("value","");
                all.appendChild(document.createTextNode("전체"));
                $('#bohoso').append(all);
                for ( var key in data)
                {
                    var option = document.createElement('option');
                    option.setAttribute("value",key);
                    option.appendChild(document.createTextNode(data[key]));
                    console.log(option);
                    $('#bohoso').append(option);
                }

            },
            error:function(error){
                alert("에러발생");
            }

        });
    }

    function getSigungu(){
        $.ajax({
            url: "/sigungu",
            type: "get",
            dataType: "json",
            contentType:'application/json; charset=utf-8',
            data: { "sido":  $('#sido').val() },
            success: function(data) {
                $('#sigungu').empty();
                for ( var key in data)
                {
                    var option = document.createElement('option');
                    option.setAttribute("value",key);
                    option.appendChild(document.createTextNode(data[key]));
                    console.log(option);
                    $('#sigungu').append(option);
                }

                },
            error:function(error){
                alert("에러발생");

            }

        });
    }
    $(document).ready(function(){

        $('#sido').change(function(){
            getSigungu();
        })

        $('#sigungu').change(function(){
            getBohoso();
        })

        $('#bohoso').change(function(){
            getPetList();
        })


    })





</script>
</body>
</html>