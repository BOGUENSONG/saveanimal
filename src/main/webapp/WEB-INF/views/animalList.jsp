<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>테스트 페이지</title>

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
    </style>
</head>
<body>
<div id="header">  </i> header</div>
<div id="selectBox">selectBox
    <select name="sido">
<%--        <c:forEach var="i" items="${test1}">--%>
<%--            <option value="<c:out value="${i[1]}"/>"><c:out value="${i[0]}"/></option>--%>
<%--        </c:forEach>--%>
    </select></div>
<div id="petlist">list</div>
<%--<div> 테스트 :<c:out value="${test2}"></c:out></div>--%>
<%--<c:out value="${}"/>--%>




</body>
</html>