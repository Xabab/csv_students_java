import java.util.ArrayList;
import java.util.List;

public class StudentsStats {
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
}
