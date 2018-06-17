import java.util.ArrayList;
import java.util.List;

public class Student {
    private int ID;
    private String group;
    private String studyForm;
    private String studentSurname;
    private String studentName;
    private int auditory;
    private List<SubjectStat> marks = new ArrayList<>();

    public Student(int ID,
                   String group,
                   String studyForm,
                   String studentSurname,
                   String studentName,
                   int auditory) {
        this.ID = ID;
        this.studentSurname = studentSurname;
        this.studentName = studentName;
        this.group = group;
        this.studyForm = studyForm;
        this.auditory = auditory;
    }

    public void addMark(SubjectStat s){
        marks.add(s);
    }

    public String toString(){
        return ""; //todo
    }

    public String toShortString(){
        return ""; //todo
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

    public int getAuditory() {
        return auditory;
    }

    public List<SubjectStat> getMarks() {
        return marks;
    }


}
