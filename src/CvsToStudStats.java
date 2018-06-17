import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CvsToStudStats {
    static void parse(String filepath, StudentsStats stats){
        /*
            o
           (<=.
          ()'()

        */

        try {
            BufferedReader fp = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "CP1251")); // CP1251 - file encoding

            System.out.println(filepath + "\n\n");


            String[] cols;


            fp.readLine();
            while(fp.ready()){
                cols = fp.readLine().split(",\"");  // using ," - hack to not divide cell's string, if it has a coma

                for(int i = 0; i < cols.length; i++) {
                    cols[i] = cols[i].replaceAll("\"", ""); // removing "s
                }


                /*
                0 ID
	            1    LAST_NAME_UKR
	            2    NAME_UKR
	            3    GROUP_NUMBER
	            4    SHORT_NAME
	            5    NAME
	            6    CHECK_FORM
	            7    NAME_1
	            8    LAST_NAME_UKR_1
	            9    NAME_UKR_1
	            10   CHAIR_NUMBER
	            11   CHAIR_NUMBER_1
                */

                int col5; //todo divide "н.д." and "зв."
                try{
                    col5 = Integer.parseInt(cols[5]);
                }
                catch(NumberFormatException e){
                    col5 = 0;
                }



                //todo fix students

                //Student s = new Student();  // not the most elegant way, but...

                //stats.addStudent(s);

            }
            fp.close();

            System.out.println(stats.getStudents().size());

            for (Student s : stats.getStudents()) {
                System.out.println(s.toShortString());
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
