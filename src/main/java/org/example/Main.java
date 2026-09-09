package org.example;
import java.util.ArrayList;
import java.util.List;

// 1. Person Class (Base Class / Encapsulation)
abstract class Person {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    // Polymorphism
    public abstract void displayRole();
}

// 2. Student Classes (Inheritance & Polymorphism)
class Student extends Person {
    private List<Double> grades;
    private int attendanceDays;

    public Student(int id, String name) {
        super(id, name);
        this.grades = new ArrayList<>();
        this.attendanceDays = 0;
    }

    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        }
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (double g : grades) {
            sum += g;
        }
        return sum / grades.size();
    }

    public void markAttendance() {
        this.attendanceDays++;
    }

    public int getAttendanceDays() {
        return attendanceDays;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Student | Name: " + getName() + " | Avg Grade: " + calculateAverageGrade());
    }
}

// Subclass for Graduate Student (Inheritance)
class GraduateStudent extends Student {
    private String researchTopic;

    public GraduateStudent(int id, String name, String researchTopic) {
        super(id, name);
        this.researchTopic = researchTopic;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Graduate Student | Name: " + getName() + " | Research: " + researchTopic);
    }
}

// 3. Instructor Class (Inheritance)
class Instructor extends Person {
    private String department;

    public Instructor(int id, String name, String department) {
        super(id, name);
        this.department = department;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Instructor | Name: " + getName() + " | Department: " + department);
    }
}

// 4. Course Class
class Course {
    private int courseId;
    private String courseName;

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public String getCourseName() { return courseName; }
}

// 5. System Manager Class (Student & Course Management)
class ManagementSystem {
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getName());
    }

    // Delete Student
    public void deleteStudent(int id) {
        students.removeIf(s -> s.getId() == id);
        System.out.println("Student with ID " + id + " removed.");
    }

    // Add Course
    public void addCourse(Course course) {
        courses.add(course);
        System.out.println("Course added: " + course.getCourseName());
    }

    // Display all students
    public void listStudents() {
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            s.displayRole();
        }
    }
}

// Main Execution
public class Main {
    public static void main(String[] args) {
        ManagementSystem system = new ManagementSystem();

        // Create Instructors
        Instructor inst = new Instructor(1, "Dr. Mina Younan", "Computer Science");
        inst.displayRole();

        // Add Courses
        Course javaCourse = new Course(101, "Java Programming");
        system.addCourse(javaCourse);

        // Add Students
        Student s1 = new Student(201, "Ahmed Mohamed");
        GraduateStudent s2 = new GraduateStudent(202, "Sara Ali", "AI Systems");

        system.addStudent(s1);
        system.addStudent(s2);

        // Record Grades & Attendance
        s1.addGrade(85.5);
        s1.addGrade(90.0);
        s1.markAttendance();

        s2.addGrade(95.0);

        // List Students
        system.listStudents();
    }
}
