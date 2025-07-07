<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加学生</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/style.css">
</head>
<body>
    <div class="container">
        <h1>添加新学生</h1>
        <form action="student?action=add" method="post">
            <div class="form-group">
                <label for="name">姓名:</label>
                <input type="text" id="name" name="name" required>
            </div>
            
            <div class="form-group">
                <label for="gender">性别:</label>
                <select id="gender" name="gender" required>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="student_number">学号:</label>
                <input type="text" id="student_number" name="student_number" required>
            </div>
            
            <div class="form-group">
                <label for="phone">电话:</label>
                <input type="text" id="phone" name="phone">
            </div>
            
            <div class="form-group">
                <label for="room_id">房间号:</label>
                <input type="number" id="room_id" name="room_id" min="1">
            </div>
            
            <div class="form-group">
                <label for="check_in_date">入住日期:</label>
                <input type="date" id="check_in_date" name="check_in_date">
            </div>
            
            <button type="submit" class="btn">添加</button>
            <a href="student?action=list" class="btn btn-secondary">取消</a>
        </form>
    </div>
</body>
</html>