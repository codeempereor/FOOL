package dao;

import model.Course;
import java.util.List;

public interface CourseDAO {
    
    /**
     * 添加课程
     * @param course 课程对象
     * @return 添加成功返回true，失败返回false
     */
    boolean addCourse(Course course);
    
    /**
     * 删除课程
     * @param courseId 课程编号
     * @return 删除成功返回true，失败返回false
     */
    boolean deleteCourse(String courseId);
    
    /**
     * 更新课程信息
     * @param course 课程对象
     * @return 更新成功返回true，失败返回false
     */
    boolean updateCourse(Course course);
    
    /**
     * 根据课程编号查找课程
     * @param courseId 课程编号
     * @return 找到返回课程对象，未找到返回null
     */
    Course findCourse(String courseId);
    
    /**
     * 获取所有课程
     * @return 课程列表
     */
    List<Course> getAllCourses();
} 