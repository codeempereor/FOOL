package servlet;

import model.Course;
import dao.CourseDAO;
import dao.CourseDAOImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CourseServlet extends HttpServlet {
    private CourseDAO courseDAO;
    
    @Override
    public void init() throws ServletException {
        courseDAO = new CourseDAOImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        
        if ("delete".equals(action)) {
            handleDelete(request, response);
            return;
        } else if ("edit".equals(action)) {
            handleShowEdit(request, response);
            return;
        } else if ("list".equals(action) || action == null) {
            // 显示课程列表
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        
        // 默认显示课程列表
        List<Course> courses = courseDAO.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getParameter("action");
        
        if (action != null) {
            switch (action) {
                case "add":
                case "添加":
                    handleAdd(request, response);
                    return;
                case "delete":
                case "删除":
                    handleDelete(request, response);
                    return;
                case "update":
                case "修改":
                    handleUpdate(request, response);
                    return;
                case "find":
                case "查询":
                    handleFind(request, response);
                    return;
            }
        }
        
        // 默认跳转到课程列表
        response.sendRedirect(request.getContextPath() + "/course?action=list");
    }
    
    private void handleAdd(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String courseName = request.getParameter("courseName");
        String courseType = request.getParameter("courseType");
        String creditsStr = request.getParameter("credits");
        String teacher = request.getParameter("teacher");
        String hoursStr = request.getParameter("hours");
        
        try {
            // 检查课程编号是否已存在
            if (courseDAO.findCourse(courseId) != null) {
                request.setAttribute("message", "添加失败：课程编号 " + courseId + " 已存在");
                request.getRequestDispatcher("/add-course.jsp").forward(request, response);
                return;
            }
            
            int credits = Integer.parseInt(creditsStr);
            int hours = Integer.parseInt(hoursStr);
            
            if (credits <= 0) {
                request.setAttribute("message", "添加失败：学分必须大于0");
                request.getRequestDispatcher("/add-course.jsp").forward(request, response);
                return;
            }
            
            if (hours <= 0) {
                request.setAttribute("message", "添加失败：学时必须大于0");
                request.getRequestDispatcher("/add-course.jsp").forward(request, response);
                return;
            }
            
            Course course = new Course(courseId, courseName, courseType, credits, teacher, hours);
            courseDAO.addCourse(course);
            
            request.setAttribute("message", "添加成功：课程编号 " + courseId);
            
            // 跳转到主页并显示成功消息
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("message", "添加失败：学分和学时必须是数字");
            request.getRequestDispatcher("/add-course.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "添加失败：" + e.getMessage());
            request.getRequestDispatcher("/add-course.jsp").forward(request, response);
        }
    }
    
    private void handleDelete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        
        try {
            // 检查课程是否存在
            Course existingCourse = courseDAO.findCourse(courseId);
            if (existingCourse == null) {
                request.setAttribute("message", "删除失败：课程编号 " + courseId + " 不存在");
            } else {
                courseDAO.deleteCourse(courseId);
                request.setAttribute("message", "删除成功：课程编号 " + courseId);
            }
            
            // 跳转到主页并显示结果消息
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("message", "删除失败：" + e.getMessage());
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String courseName = request.getParameter("courseName");
        String courseType = request.getParameter("courseType");
        String creditsStr = request.getParameter("credits");
        String teacher = request.getParameter("teacher");
        String hoursStr = request.getParameter("hours");
        
        try {
            // 检查课程是否存在
            if (courseDAO.findCourse(courseId) == null) {
                request.setAttribute("message", "修改失败：课程编号 " + courseId + " 不存在");
                request.getRequestDispatcher("/edit-course.jsp?courseId=" + courseId).forward(request, response);
                return;
            }
            
            int credits = Integer.parseInt(creditsStr);
            int hours = Integer.parseInt(hoursStr);
            
            if (credits <= 0) {
                request.setAttribute("message", "修改失败：学分必须大于0");
                request.getRequestDispatcher("/edit-course.jsp?courseId=" + courseId).forward(request, response);
                return;
            }
            
            if (hours <= 0) {
                request.setAttribute("message", "修改失败：学时必须大于0");
                request.getRequestDispatcher("/edit-course.jsp?courseId=" + courseId).forward(request, response);
                return;
            }
            
            Course course = new Course(courseId, courseName, courseType, credits, teacher, hours);
            courseDAO.updateCourse(course);
            
            request.setAttribute("message", "修改成功：课程编号 " + courseId);
            
            // 跳转到主页并显示成功消息
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("message", "修改失败：学分和学时必须是数字");
            request.getRequestDispatcher("/edit-course.jsp?courseId=" + courseId).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "修改失败：" + e.getMessage());
            request.getRequestDispatcher("/edit-course.jsp?courseId=" + courseId).forward(request, response);
        }
    }
    
    private void handleFind(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        
        try {
            Course course = courseDAO.findCourse(courseId);
            
            if (course != null) {
                request.setAttribute("foundCourse", course);
                request.setAttribute("message", "查找成功：找到课程编号 " + courseId);
            } else {
                request.setAttribute("message", "查找失败：课程编号 " + courseId + " 不存在");
            }
            
            // 同时显示所有课程列表
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("message", "查找失败：" + e.getMessage());
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
    
    private void handleShowEdit(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        
        try {
            Course course = courseDAO.findCourse(courseId);
            if (course != null) {
                request.setAttribute("course", course);
                request.getRequestDispatcher("/edit-course.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "课程编号 " + courseId + " 不存在");
                List<Course> courses = courseDAO.getAllCourses();
                request.setAttribute("courses", courses);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", "获取课程信息失败：" + e.getMessage());
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
} 