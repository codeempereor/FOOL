<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>宿舍管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
    <div class="container">
        <h1>宿舍管理系统</h1>
        <div class="dashboard">
            <div class="card">
                <h3>宿舍楼管理</h3>
                <div class="count">${buildingCount}</div>
                <a href="${pageContext.request.contextPath}/building?action=list">查看列表</a>
            </div>
            <div class="card">
                <h3>宿舍房间管理</h3>
                <div class="count">${roomCount}</div>
                <a href="${pageContext.request.contextPath}/room?action=list">查看列表</a>
            </div>
            <div class="card">
                <h3>学生管理</h3>
                <div class="count">${studentCount}</div>
                <a href="${pageContext.request.contextPath}/student?action=list">查看列表</a>
            </div>
        </div>
    </div>
</body>
</html>