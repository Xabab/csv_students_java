import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
*
* @author Daniel Ashkar 2018
*
*
*/


public class Main {
    public static void main(String[] args) {

        String filepath;
        if (args.length != 0){
            filepath = args[0];
            // we try to take first argument as absolute path to file
        }
        else{
            filepath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().concat("STUDENT_MARKS.csv");
            // if there is none, we try to find a "STUDENT_MARKS.csv" in one folder with us
        }

        StudentsStats students = new StudentsStats();

        CvsToStudStats.parse(filepath, students);  // parsing file with hand made "library"

        for(Student s: students.getStudents()){    // printing what we just parsed, just in case
            System.out.println(s + "\n");
        }

        students.printStats();
        students.writeStats();
    }
}

