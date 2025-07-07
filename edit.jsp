<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑学生信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>编辑学生信息</h1>
        <form action="student?action=update" method="post">
            <input type="hidden" name="id" value="${student.studentId}">
            
            <div class="form-group">
                <label for="name">姓名:</label>
                <input type="text" id="name" name="name" value="${student.name}" required>
            </div>
            
            <div class="form-group">
                <label for="gender">性别:</label>
                <select id="gender" name="gender" required>
                    <option value="男" ${student.gender == '男' ? 'selected' : ''}>男</option>
                    <option value="女" ${student.gender == '女' ? 'selected' : ''}>女</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="student_number">学号:</label>
                <input type="text" id="student_number" name="student_number" 
                       value="${student.studentNumber}" required>
            </div>
            
            <div class="form-group">
                <label for="phone">电话:</label>
                <input type="text" id="phone" name="phone" value="${student.phone}">
            </div>
            
            <div class="form-group">
                <label for="room_id">房间号:</label>
                <input type="number" id="room_id" name="room_id" min="1"
                       value="${student.roomId > 0 ? student.roomId : ''}">
            </div>
            
            <div class="form-group">
                <label for="check_in_date">入住日期:</label>
                <input type="date" id="check_in_date" name="check_in_date"
                       <c:if test="${not empty student.checkInDate}">
                           value="<fmt:formatDate value="${student.checkInDate}" pattern="yyyy-MM-dd"/>"
                       </c:if>>
            </div>
            
            <button type="submit" class="btn">更新</button>
            取消
        </form>
    </div>
</body>
</html>