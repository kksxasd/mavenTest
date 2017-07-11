<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/5
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>算法</title>
</head>
<link rel="stylesheet" href="/static/css/index/bootstrap.css">
<link rel="stylesheet" href="/static/css/index/style.css">
<body>

<div id="fh5co-page">
    <a href="#" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
    <aside id="fh5co-aside" role="complementary" class="border js-fullheight">

        <h1 id="fh5co-logo"><a href="/index">Marble</a></h1>
        <nav id="fh5co-main-menu" role="navigation">
            <ul>
                <li class="fh5co-active"><a href="/index">Home</a></li>
                <c:forEach items="${menuDtoList}" var="menu">
                    <li><a href="${menu.url}">${menu.name}</a></li>
                </c:forEach>

            </ul>
        </nav>

    </aside>

    <div id="fh5co-main">
        <div>
            <input type="text" id="arr"><br>
            <input type="button" id="quickSort" value="快速排序" style="width:5em">
            <input type="text" id="quickSortResult" disabled="true"><br>
            <input type="button" id="bubbleSort" value="冒泡排序" style="width:5em">
            <input type="text" id="bubbleSortResult" disabled="true"><br>
            <input type="button" id="selectSort" value="选择排序" style="width:5em">
            <input type="text" id="selectSortResult" disabled="true"><br>
            <input type="button" id="heapSort" value="堆排序" style="width:5em">
            <input type="text" id="heapSortResult" disabled="true"><br>
        </div>
    </div>

</div>

</body>

<script type="text/javascript" src="/static/js/common/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/algorithm/index.js"></script>
</html>
