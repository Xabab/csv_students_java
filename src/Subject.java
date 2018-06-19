public class Subject {
    private String name = "-1";
    private String lecturerName = "-1";
    private String lecturerSurname = "-1";
    private int caf = -1;

    public Subject(String name, String lecturerName, String lecturerSurname, int caf) {
        this.name = name;
        this.lecturerName = lecturerName;
        this.lecturerSurname = lecturerSurname;
        this.caf = caf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getLecturerSurname() {
        return lecturerSurname;
    }

    public void setLecturerSurname(String lecturerSurname) {
        this.lecturerSurname = lecturerSurname;
    }

    public int getCaf() {
        return caf;
    }

    public void setCaf(int caf) {
        this.caf = caf;
    }



}
