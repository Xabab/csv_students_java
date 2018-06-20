import java.util.ArrayList;
import java.util.List;

public class Group {
    String index = "-1";
    List<Student> students = new ArrayList<>();

    public Group(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
