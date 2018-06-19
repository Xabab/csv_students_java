import java.util.ArrayList;
import java.util.List;

public class StudentsStats {
    private List<Group> groups = new ArrayList<>();
    private List<Caf> cafs = new ArrayList<>();

    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student s){
        students.add(s);
    }

    public void removeStudent(Student s){
        students.remove(s);
    }

    private void calculateStats(){
        cafs.add(new Caf(students.get(0).getCaf()));
        cafs.get(0).students.add(students.get(0));
        Label: for(Student s: students){
            for(Caf c: cafs){
                if (c.getNumber() == s.getCaf()){
                    c.getStudents().add(s);
                    continue Label;
                }
            }
            cafs.add(new Caf(s.getCaf()));
            cafs.get(cafs.size() - 1).getStudents().add(s);
        }
    }

    public void printStats(){

    }
}
