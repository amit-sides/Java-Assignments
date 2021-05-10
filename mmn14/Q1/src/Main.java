import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SortedGroup<Student> students = new SortedGroup<Student>();
        Student s1 = new Student("S1", "111111111", 43);
        Student s2 = new Student("S2", "222222222", 77);
        Student s3 = new Student("S3", "333333333", 21);
        Student s4 = new Student("S4", "444444444", 54);
        Student s5 = new Student("S5", "555555555", 60);
        Student s6 = new Student("S6", "666666666", 66);
        Student s7 = new Student("S7", "777777777", 77);
        Student s8 = new Student("S8", "888888888", 100);

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(s8);

        System.out.println("Students at start:");
        for(Iterator<Student> i = students.iterator(); i.hasNext();){
            Student s = i.next();
            System.out.println(s);
        }
        System.out.println("\n========================================================\n");

        // Remove s1 and s3
        students.remove(s1);
        students.remove(s3);

        System.out.println("Students after removal:");
        for(Iterator<Student> i = students.iterator(); i.hasNext();){
            Student s = i.next();
            System.out.println(s);
        }
        System.out.println("\n========================================================\n");

        // Filter failed students
        Student failureGradeStudent = new Student("", "", 60);
        SortedGroup<Student> passedStudents = Util.reduce(students, failureGradeStudent);

        System.out.println("Successful students:");
        for(Iterator<Student> i = passedStudents.iterator(); i.hasNext();){
            Student s = i.next();
            System.out.println(s);
        }
        System.out.println("\n========================================================\n");
    }
}
