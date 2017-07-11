<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <jsp:include page="commonjsp.jsp"/>
    <script type="text/javascript" src="/static/js/file/ajaxfileupload.js"></script>
    <script type="text/javascript">
        $(function () {
            $(":button").click(function () {
                ajaxFileUpload();
            })
        })
        function ajaxFileUpload() {
            $.ajaxFileUpload
            ({
                        type: "POST",
                        url: '/file/up', //用于文件上传的服务器端请求地址
                        secureuri: false, //是否需要安全协议，一般设置为false
                        fileElementId: 'file1', //文件上传域的ID
                        dataType: 'JSON', //返回值类型 一般设置为json
                        success: function (data, status)  //服务器成功响应处理函数
                        {
                            $("#img1").attr("src", data.imgurl);
                            if (typeof (data.error) != 'undefined') {
                                if (data.error != '') {
                                    alert(data.error);
                                } else {
                                    alert(data.msg);
                                }
                            }
                        },
                        error: function (data, status, e)//服务器响应失败处理函数
                        {
                            alert(e);
                        }
                    }
            )
            return false;
        }
    </script>
    <title>文件</title>
</head>
<body >
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
        <h1>文件</h1>
        <p><input type="file" id="file1" name="file1" /></p>
        <input type="button" value="上传" />
    </div>

</div>


</body>
</html>