public class Main {
    public static void main(String[] args) {
        SortedGroup checkArray = new SortedGroup();
        SortedGroup checkSingles = new SortedGroup();
        SortedGroup StudentsGroup = new SortedGroup();
        Reduce reduceCheck = new Reduce();

//        // From array
//        int [] arr = {10, 20, 30, 1, 7, 12, 0, 1, 1, 1, 7, 11, 12331};
//        for(int i = 0; i < arr.length; i++){
//            checkArray.add(arr[i]);
//        }
//        System.out.println("All the elements: "+checkArray);
//        System.out.println("After reduce by 10: "+reduceCheck.reduce(checkArray, 10));
//        for(int i = 0; i <arr.length; i++){
//            if(arr[i] % 2 == 0){
//                checkArray.remove(arr[i]);
//            }
//        }
//        System.out.println("After removing odd numbers: "+checkArray);
//        System.out.println("After reduce by 10: "+reduceCheck.reduce(checkArray, 10));
//
//        // one element each time
//        checkSingles.add(1123);
//        checkSingles.add(44);
//        checkSingles.add(12);
//        //check.add("Hey"); // expected Error
//        //check.add(122.2); // expected Error
//        checkSingles.add(1);
//        System.out.println("All the elements: "+checkSingles);
//        System.out.println("After reduce by 10: "+reduceCheck.reduce(checkSingles, 10));
//        checkSingles.remove(1123);
//        checkSingles.remove(11); // in case the object doesnt exist - does nothing special
//        System.out.println("After removing: "+checkSingles);
//        System.out.println("After reduce by 10: "+reduceCheck.reduce(checkSingles, 10));
        // Students list
        // Students list
        Student s1 = new Student("Adam", 0101, 100);
        Student s2 = new Student("Omer", 1111, 96);
        Student s3 = new Student("Yair", 0001, 55);
        Student s4 = new Student("Sarah", 0000, 18);
        Student s5 = new Student("Amit", 1011, 99);
        Student s6 = new Student("Igal", 1000, 99);
        Student s7 = new Student("Ron", 1100, 48);
        Student s8 = new Student("Liran", 1001, 12);
        Student s9 = new Student("Eve", 0010, 12);
        Student s10 = new Student("Tomer", 1011, 60);

        // Students Group creation
        StudentsGroup.add(s1);
        StudentsGroup.add(s2);
        StudentsGroup.add(s3);
        StudentsGroup.add(s4);
        StudentsGroup.add(s5);
        StudentsGroup.add(s6);
        StudentsGroup.add(s7);
        StudentsGroup.add(s8);
        StudentsGroup.add(s9);
        StudentsGroup.add(s10);

        // Print all Students
        System.out.println("All the students are:\n");
        for(int i = 0; i < StudentsGroup.getSortedArray().size(); i++){
            System.out.println(StudentsGroup.getSortedArray().get(i));
            System.out.println("----------\n");
        }

        // Remove Adam and Eve
        StudentsGroup.remove(s1);
        StudentsGroup.remove(s9);

        System.out.println("All the students after Adam and Eve were banished, are:\n");
        for(int i = 0; i < StudentsGroup.getSortedArray().size(); i++){
            System.out.println(StudentsGroup.getSortedArray().get(i));
            System.out.println("----------\n");
        }

        // Reduce (lower than 60)
        StudentsGroup = reduceCheck.reduce(StudentsGroup, s10); // Tomer(s10) as reference to grade 60

        System.out.println("All the students with 60+ grade, are:\n"); // getting rid of: Yair, Sarah, Ron, Liran, Tomer
        for(int i = 0; i < StudentsGroup.getSortedArray().size(); i++){
            System.out.println(StudentsGroup.getSortedArray().get(i));
            System.out.println("----------\n");
        }
    }

}

