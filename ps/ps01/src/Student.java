/**
 * @author Aaron Lack, alack, C343 FA20. Last Edited: 9/2/20.
 * Student Problem 2 for Problem set 2
 * I am making my own student class and for simplicity, I will have 3 instance variabls:
 * String name, String major, and int StudentID
 * Generate constructor, getters and setters, and toString() methods.
 * Generate a main method and make instances of the student class, 3-5 students.
 * Put these students into a single array and print some stuff, add results in README file
 */

public class Student {
    String name;
    String major;
    int StudentID;

    public Student(String name, String major, int studentID) {
        this.name = name;
        this.major = major;
        StudentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    //So a memory location will not print
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", StudentID=" + StudentID +
                '}';
    }

    public static void main(String[] args) {
        //Making some student objects.
        Student firstStudent = new Student("Aaron", "Informatics", 504064);
        Student secondStudent = new Student("Adeel", "Computer Science", 504069);

        //Printing 1 student
        System.out.println(firstStudent);
        //Printing a space
        System.out.println();

        //Making a students list called undergrads.
        //Assigning the students to an index position in undergrads.
        //Also making new students within the undergrads array.
        Student[] undergrads = new Student[5];
        undergrads[0] = firstStudent;
        undergrads[1] = secondStudent;
        undergrads[2] = new Student("Erik", "Math", 405784);
        undergrads[3] = new Student("Taylor", "Healthcare Management and Policy", 678123);
        undergrads[4] = new Student("Ellie", "Political Science", 555555);

        //A simple for loop to print all the students names
        for(int i = 0; i<5; i++) {
            System.out.println(undergrads[i]);
        }
        System.out.println();
        //Testing indexing from array
        System.out.println(undergrads[3]);

    }
}
