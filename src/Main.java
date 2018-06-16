import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) {

        String filename;
        if (args.length == 0){
            filename = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().concat("STUDENT_MARKS.csv");
        }
        else{
            filename = args[0];
        }

        try {

            BufferedReader fp = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "CP1251"));

            String[] header = fp.readLine().split(",\"");

            if(header != null) {
                String out = "";
                for (int i = 0; i < header.length; i++) {
                    header[i] = header[i].replaceAll("\"", "");
                    out = out.concat(header[i]);
                    out = out.concat("\t\t");
                }
                System.out.println(out);
            }

            String[] cols;
            String out = "";

            while(fp.ready()){
                cols = fp.readLine().split(",\"");

                for(int i = 0; i < cols.length; i++) {
                    if (i != 0) out += "\t";
                    cols[i] = cols[i].replaceAll("\"", "");
                    out += header[i] + ":" + "\t" + cols[i] + "\n";
                }
                System.out.println(out);

                out = "";
                cols = null;
            }
            fp.close();
            fp  = null;
            header = null;
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
