public class Student implements Comparable {
    private String name;
    private int id;
    private double grade;

    // Constructor, gets all the values and initialize
    public Student(String _name, int _id, double _grade) {
        this.name = _name;
        this.id = _id;
        this.grade = _grade;
    }

    // Comparison by grade
    @Override
    public int compareTo(Object o) {
        Student s = (Student)o;
        return (int)(this.grade - s.grade);
    }

    // Print all the info of a student
    public String toString(){
        return "Student's info:\nName: "+this.name+"\nId: "+this.id+"\nGrade: "+this.grade;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public double getGrade() {
        return this.grade;
    }

    // Setters
    public void setName(String newName) {
        this.name = newName;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setGrade(double newGrade) {
        this.grade = newGrade;
    }
}
