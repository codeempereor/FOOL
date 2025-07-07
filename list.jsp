<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
    <div class="container">
        <h1>学生列表</h1>
        
        <div class="search-bar">
            <form action="student?action=search" method="get">
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyword" placeholder="输入姓名或学号..." 
                       value="${param.keyword}" class="search-input">
                <button type="submit" class="btn">搜索</button>
                <a href="<c:url value='/student?action=new' />" class="btn">添加学生</a>
            </form>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>学号</th>
                    <th>电话</th>
                    <th>房间号</th>
                    <th>入住日期</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.name}</td>
                        <td>${student.gender}</td>
                        <td>${student.studentNumber}</td>
                        <td>${student.phone}</td>
                        <td>
                            <c:choose>
                                <c:when test="${student.roomId > 0}">
                                    ${student.roomId}
                                </c:when>
                                <c:otherwise>
                                    未分配
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${not empty student.checkInDate}">
                                <fmt:formatDate value="${student.checkInDate}" pattern="yyyy-MM-dd"/>
                            </c:if>
                        </td>
                        <td class="action-links">
                            <a href="<c:url value='/student?action=edit&id=${student.studentId}' />">编辑</a>
                            <a href="student?action=delete&id=${student.studentId}" 
                               onclick="return confirm('确定删除此学生吗？')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div style="margin-top: 20px;">
            <a href="${pageContext.request.contextPath}/">返回首页</a>
        </div>
    </div>
</body>
</html>