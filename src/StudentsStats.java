import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

public class StudentsStats {
    private List<Group> groups = new ArrayList<>();
    private List<Caf> cafs = new ArrayList<>();
    private List<Student> marks5 = new ArrayList<>();
    private List<Student> marks45 = new ArrayList<>();
    private double []universityMarksPercentage = {0, 0, 0, 0, 0};
    private boolean calculatedResults = false;

    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student s){
        calculatedResults = false;
        students.add(s);
    }

    public void removeStudent(Student s){
        calculatedResults = false;
        students.remove(s);
    }

    private void calculateStats(){
        cafs.add(new Caf(students.get(0).getCaf())); //counting students in cafs
        cafs.get(0).students.add(students.get(0));
        CafLabel: for(Student s: students){
            for(Caf c: cafs){
                if (c.getNumber() == s.getCaf()){
                    c.getStudents().add(s);
                    continue CafLabel;
                }
            }
            cafs.add(new Caf(s.getCaf()));
            cafs.get(cafs.size() - 1).getStudents().add(s);
        }

        groups.add(new Group(students.get(0).getGroup()));  //counting students in groups
        groups.get(0).students.add(students.get(0));
        GroupsLabel: for(Student s: students){
            for(Group c: groups){
                if (c.getIndex().equals(s.getGroup())){
                    c.getStudents().add(s);
                    continue GroupsLabel;
                }
            }
            groups.add(new Group(s.getGroup()));
            groups.get(groups.size() - 1).getStudents().add(s);
        }

        LabelMarks5: for (Student s: students) {     //counting students with "5" marks only
            for(SubjectStat stat: s.getMarks()){
                if(stat.getMark5() != 5) continue LabelMarks5;
            }
            marks5.add(s);
        }

        LabelMarks45: for (Student s: students) {    //counting students with "4" and "5" marks only
            for(SubjectStat stat: s.getMarks()){
                if(stat.getMark5() != 5 && stat.getMark5() != 4) continue LabelMarks45;
            }
            marks45.add(s);
        }

        for (Student s: students) {                 //calculate university general percentage of 0, 2, 3, 4, 5 marks
            s.calculateMarksPercentage();
        }

        int [] marks_count02345 = {0, 0, 0, 0, 0}; //0, 2, 3, 4, 5
        int marks_count = 0;

        for (Student s : students){
            for (SubjectStat stat : s.getMarks()) {
                switch (stat.getMark5()) {
                    case 0:
                        marks_count++;
                        marks_count02345[0]++;
                        break;
                    case 2:
                        marks_count++;
                        marks_count02345[1]++;
                        break;
                    case 3:
                        marks_count++;
                        marks_count02345[2]++;
                        break;
                    case 4:
                        marks_count++;
                        marks_count02345[3]++;
                        break;
                    case 5:
                        marks_count++;
                        marks_count02345[4]++;
                        break;
                    default:
                        marks_count++;
                        marks_count02345[0]++;   //if отчислен count as zero
                        break;
                }
            }
        }

        universityMarksPercentage[0] = (double)marks_count02345[0] / (double)marks_count;
        universityMarksPercentage[1] = (double)marks_count02345[1] / (double)marks_count;
        universityMarksPercentage[2] = (double)marks_count02345[2] / (double)marks_count;
        universityMarksPercentage[3] = (double)marks_count02345[3] / (double)marks_count;
        universityMarksPercentage[4] = (double)marks_count02345[4] / (double)marks_count;
    }

    public void printStats(){ //print calculated info
        if(!calculatedResults) calculateStats();
        System.out.println("\n");

        for(Student s: students){
            System.out.println("Student #" + Integer.toString(s.getID()) + " has:");
            System.out.println("\t" + "%2: " + String.format("%.0f", 100 * s.getPercentage()[0]) + "%");
            System.out.println("\t" + "%3: " + String.format("%.0f", 100 * s.getPercentage()[1]) + "%");
            System.out.println("\t" + "%4: " + String.format("%.0f", 100 * s.getPercentage()[2]) + "%");
            System.out.println("\t" + "%5: " + String.format("%.0f", 100 * s.getPercentage()[3]) + "%");
        }
        System.out.println("\n\n");

        System.out.println("\n");
        System.out.println("In total:");
        System.out.println("\t" + "%0: " + String.format("%.0f", 100 * universityMarksPercentage[0]) + "%");
        System.out.println("\t" + "%2: " + String.format("%.0f", 100 * universityMarksPercentage[1]) + "%");
        System.out.println("\t" + "%3: " + String.format("%.0f", 100 * universityMarksPercentage[2]) + "%");
        System.out.println("\t" + "%4: " + String.format("%.0f", 100 * universityMarksPercentage[3]) + "%");
        System.out.println("\t" + "%5: " + String.format("%.0f", 100 * universityMarksPercentage[4]) + "%");
        System.out.println("\n\n");

        System.out.println("\n");
        System.out.println("Students with \"4\" and \"5\" only: " + marks45.size());
        System.out.println("Students with \"5\" only: " + marks5.size());
        System.out.println("\n\n");

        System.out.println("\n");
        for(Group g: groups){
            System.out.println("Group " + g.getIndex() + ": " + Integer.toString(g.students.size()));
        }
        System.out.println("\n\n");


        System.out.println("\n");
        for(Caf c: cafs){
            System.out.println("Caf " + c.getNumber() + ": " + Integer.toString(c.students.size()));
        }
        System.out.println("\n\n");
    }

    public void writeStats(){    //write calculated info into files
        if(!calculatedResults) calculateStats();

        PrintWriter p = null;
        try {
            p = new PrintWriter("students.txt", "CP1251");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Student s: students){
            p.write(s.toString() + "\n");
        }

        p.close();
        p.flush();

        try {
            p = new PrintWriter("stats.txt", "CP1251");
        } catch (Exception e) {
            e.printStackTrace();
        }

        p.write("\n");

        for(Student s: students){
            p.write("Student #" + Integer.toString(s.getID()) + " has:");
            p.write("\t" + "%2: " + String.format("%.0f", 100 * s.getPercentage()[0]) + "%" + "\n");
            p.write("\t" + "%3: " + String.format("%.0f", 100 * s.getPercentage()[1]) + "%" + "\n");
            p.write("\t" + "%4: " + String.format("%.0f", 100 * s.getPercentage()[2]) + "%" + "\n");
            p.write("\t" + "%5: " + String.format("%.0f", 100 * s.getPercentage()[3]) + "%" + "\n");
        }
        p.write("\n\n");     //a little bit of repetitive code here and there... *pop* whops!

        p.write("\n");
        p.write("In total:" + "\n");
        p.write("\t" + "%0: " + String.format("%.0f", 100 * universityMarksPercentage[0]) + "%" + "\n");
        p.write("\t" + "%2: " + String.format("%.0f", 100 * universityMarksPercentage[1]) + "%" + "\n");
        p.write("\t" + "%3: " + String.format("%.0f", 100 * universityMarksPercentage[2]) + "%" + "\n");
        p.write("\t" + "%4: " + String.format("%.0f", 100 * universityMarksPercentage[3]) + "%" + "\n");
        p.write("\t" + "%5: " + String.format("%.0f", 100 * universityMarksPercentage[4]) + "%" + "\n");
        p.write("\n\n");

        p.write("\n");
        p.write("Students with \"4\" and \"5\" only: " + marks45.size() + "\n");
        p.write("Students with \"5\" only: " + marks5.size() + "\n");
        p.write("\n\n");

        p.write("\n");
        for(Group g: groups){
            p.write("Group " + g.getIndex() + ": " + Integer.toString(g.students.size()) + "\n");
        }
        p.write("\n\n");


        p.write("\n");
        for(Caf c: cafs){
            p.write("Caf " + c.getNumber() + ": " + Integer.toString(c.students.size()) + "\n");
        }
        p.write("\n\n");
        p.close();
    }
}
