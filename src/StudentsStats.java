import java.util.ArrayList;
import java.util.List;

public class StudentsStats {
    private List<List<Student>> groups = new ArrayList<>();
    private List<List<Student>> caf = new ArrayList<>();

    private List<Student> Students = new ArrayList<>();

    public List<Student> getStudents() {
        return Students;
    }

    public void addStudent(Student s){
        Students.add(s);
    }

    public void removeStudent(Student s){
        Students.remove(s);
    }

    private void calculateStats(){

    }

    public void printStats(){

    }
}
