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

    public String toString(){
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
