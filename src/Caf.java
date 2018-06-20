import java.util.ArrayList;
import java.util.List;

public class Caf {
    int number = -1;

    public Caf(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Student> getStudents() {
        return students;
    }


    List<Student> students = new ArrayList<>();
}