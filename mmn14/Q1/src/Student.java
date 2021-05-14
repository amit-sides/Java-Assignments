public class Student implements Comparable<Student>{
    private String name;
    private String id;
    private int grade;

    public Student(String name, String id, int grade)
    {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public String getName()
    {
        return this.name;
    }

    public String getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.grade - o.grade;
    }
}
