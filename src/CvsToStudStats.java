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
            //open file
            BufferedReader fp = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "CP1251")); // CP1251 - file encoding

            System.out.println(filepath + "\n\n");


            String[] cols;

            //read line by line: read line
            cols = fp.readLine().split(",\""); //skipping header

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

            cols = fp.readLine().split(",\"");  // using ," - hack to not divide cell's string if it has a coma
            for(int i = 0; i < cols.length; i++) {
                cols[i] = cols[i].replaceAll("\"", ""); // removing "s
            }
            int col5;

            try{                                    //catching "не допущен" and "отчислен", cuz they throw am exception
                col5 = Integer.parseInt(cols[5]);   // when I try to parse them as int (well ,that was predictable)
            }
            catch(NumberFormatException e){
                if(cols[5].equals("зв.")) col5 = -2;
                else col5 = 0;
            }



            Student s = new Student(Integer.parseInt(cols[0]), cols[3], cols[7], cols[1], cols[2], Integer.parseInt(cols[10]));
            s.addMark(new SubjectStat(cols[4], cols[8], cols[9], Integer.parseInt(cols[11]), col5));
            if(col5 == 0) s.getMarks().get(0).setAllowedToPass(false);
            if(col5 == -2) s.setActive(false);

            fp.readLine();

            Lable: while(fp.ready()){
                cols = fp.readLine().split(",\"");  // same hack

                for(int i = 0; i < cols.length; i++) {
                    cols[i] = cols[i].replaceAll("\"", ""); // removing "s
                }

                try{
                    col5 = Integer.parseInt(cols[5]);
                }
                catch(NumberFormatException e){
                    if(cols[5].equals("зв.")) col5 = -2;
                    else col5 = 0;
                }


                if (s.getID() != Integer.parseInt(cols[0])){
                    stats.addStudent(s);
                    s = new Student(Integer.parseInt(cols[0]), cols[3], cols[7], cols[1], cols[2], Integer.parseInt(cols[10]));
                    s.addMark(new SubjectStat(cols[4], cols[8], cols[9], Integer.parseInt(cols[11]), col5));
                    if(col5 == 0) s.getMarks().get(0).setAllowedToPass(false);  //"не допущен"
                    if(col5 == -2) s.setActive(false);                          //"отчислен"
                }
                else{
                    for(SubjectStat stat: s.getMarks()){
                        if (stat.getName().equals(cols[4])){
                            continue Lable;                   //skipping, if student has mark dupes
                        }
                    }
                    s.addMark(new SubjectStat(cols[4], cols[8], cols[9], Integer.parseInt(cols[11]), col5));
                    if(col5 == 0) s.getMarks().get(0).setAllowedToPass(false);
                }
            }
            fp.close();

            System.out.println(stats.getStudents().size());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
