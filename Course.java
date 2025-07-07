package model;

public class Course {
    private String courseId;     // 课程编号
    private String courseName;   // 课程名称
    private String courseType;   // 课程类型
    private int credits;         // 学分
    private String teacher;      // 授课教师
    private int hours;           // 学时
    
    // 默认构造函数
    public Course() {
    }
    
    // 带参数的构造函数
    public Course(String courseId, String courseName, String courseType, int credits, String teacher, int hours) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseType = courseType;
        this.credits = credits;
        this.teacher = teacher;
        this.hours = hours;
    }
    
    // Getter和Setter方法
    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getCourseType() {
        return courseType;
    }
    
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    public String getTeacher() {
        return teacher;
    }
    
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    
    public int getHours() {
        return hours;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }
    
    // toString方法，用于文件存储（制表符分隔）
    @Override
    public String toString() {
        return courseId + "\t" + courseName + "\t" + courseType + "\t" + credits + "\t" + teacher + "\t" + hours;
    }
    
    // equals方法，用于比较
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return courseId != null ? courseId.equals(course.courseId) : course.courseId == null;
    }
    
    // hashCode方法
    @Override
    public int hashCode() {
        return courseId != null ? courseId.hashCode() : 0;
    }
} 