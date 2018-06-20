import java.util.ArrayList;
import java.util.List;

public class Student {
    private int ID;
    private String group;
    private String studyForm;
    private String studentSurname;
    private String studentName;
    private int caf;
    private boolean active = true;

    public double[] getPercentage() {
        return percentage;
    }

    private double [] percentage = {0, 0, 0, 0};

    private List<SubjectStat> marks = new ArrayList<>();

    public Student(int ID,
                   String group,
                   String studyForm,
                   String studentSurname,
                   String studentName,
                   int caf) {
        this.ID = ID;
        this.studentSurname = studentSurname;
        this.studentName = studentName;
        this.group = group;
        this.studyForm = studyForm;
        this.caf = caf;
    }

    public void addMark(SubjectStat s){
        marks.add(s);
    }

    public String toString(){   //formatting student info for printing
        StringBuilder out = new StringBuilder();
        out.append(Integer.toString(ID) + " " + studentName + " " + studentSurname + ", " +
                "група " + group + ", " + studyForm + ", кафедра " + caf + "\n");

        if (active) {
            for (SubjectStat s : marks) {
                out.append("\t" + s.getName() + " (" + s.getCaf() + "), " +
                        s.getLecturerName() + " " + s.getLecturerSurname() + ": ");
                if (s.getMark100() == 0) out.append("не допущений(а)");
                else out.append(Integer.toString(s.getMark100()) + "/100 (" + Integer.toString(s.getMark5()) + "/5)");
                out.append("\n");
            }
        }
        else out.append("\t відрахований(а)");

        return out.toString();
    }

    public void calculateMarksPercentage(){  //calculate percentage of 2, 3, 4, 5 marks
        if(marks.size() == 0){
            return;             //why calculate if there is no marks?
        }

        int [] marks_count = {0, 0, 0, 0, 0}; //0, 2, 3, 4, 5

        for(SubjectStat s: marks){
            switch (s.getMark5()){
                case 0:
                    marks_count[0]++;
                    break;
                case 2:
                    marks_count[1]++;
                    break;
                case 3:
                    marks_count[2]++;
                    break;
                case 4:
                    marks_count[3]++;
                    break;
                case 5:
                    marks_count[4]++;
                    break;
                default:
                    return; //why count if he/she is отчислен?
            }
        }

        percentage[0] = (double)marks_count[1] / (double)marks.size();
        percentage[1] = (double)marks_count[2] / (double)marks.size();
        percentage[2] = (double)marks_count[3] / (double)marks.size();
        percentage[3] = (double)marks_count[4] / (double)marks.size();
    }


    public int getID() {
        return ID;
    }

    public String getGroup() {
        return group;
    }

    public String getStudyForm() {
        return studyForm;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getCaf() {
        return caf;
    }

    public List<SubjectStat> getMarks() {
        return marks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
