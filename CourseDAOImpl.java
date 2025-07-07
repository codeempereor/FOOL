package dao;

import model.Course;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class CourseDAOImpl implements CourseDAO {
    private static String FILE_PATH = "data/courses.txt";
    
    // 静态初始化块，设置正确的文件路径
    static {
        // 尝试获取当前工作目录下的data目录
        String currentDir = System.getProperty("user.dir");
        if (currentDir != null && currentDir.contains("bin")) {
            // 如果当前目录在bin下，向上查找webapps目录
            FILE_PATH = "../webapps/web/data/courses.txt";
        } else {
            // 默认使用相对路径
            FILE_PATH = "data/courses.txt";
        }
        
        // 确保目录存在
        try {
            File dataDir = new File(FILE_PATH).getParentFile();
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            // 确保文件存在
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 从文件读取所有课程信息
     */
    private List<Course> readFromFile() {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split("\t");
                    if (parts.length == 6) {
                        Course course = new Course();
                        course.setCourseId(parts[0]);
                        course.setCourseName(parts[1]);
                        course.setCourseType(parts[2]);
                        course.setCredits(Integer.parseInt(parts[3]));
                        course.setTeacher(parts[4]);
                        course.setHours(Integer.parseInt(parts[5]));
                        courses.add(course);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    /**
     * 将课程列表写入文件
     */
    private boolean writeToFile(List<Course> courses) {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8))) {
            for (Course course : courses) {
                writer.println(course.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean addCourse(Course course) {
        List<Course> courses = readFromFile();
        // 检查课程编号是否重复
        for (Course c : courses) {
            if (c.getCourseId().equals(course.getCourseId())) {
                return false; // 课程编号重复
            }
        }
        courses.add(course);
        return writeToFile(courses);
    }
    
    @Override
    public boolean deleteCourse(String courseId) {
        List<Course> courses = readFromFile();
        boolean removed = courses.removeIf(c -> c.getCourseId().equals(courseId));
        if (removed) {
            return writeToFile(courses);
        }
        return false;
    }
    
    @Override
    public boolean updateCourse(Course course) {
        List<Course> courses = readFromFile();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(course.getCourseId())) {
                courses.set(i, course);
                return writeToFile(courses);
            }
        }
        return false;
    }
    
    @Override
    public Course findCourse(String courseId) {
        List<Course> courses = readFromFile();
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
    
    @Override
    public List<Course> getAllCourses() {
        return readFromFile();
    }
} 