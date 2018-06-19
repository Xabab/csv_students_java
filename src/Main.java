import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) {

        String filepath;
        if (args.length == 0){
            filepath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().concat("STUDENT_MARKS.csv");
        }
        else{
            filepath = args[0];
        }

        StudentsStats students = new StudentsStats();

        CvsToStudStats.parse(filepath, students);

        for(Student s: students.getStudents()){
            System.out.println(s);
            System.out.println();
        }
    }
}

