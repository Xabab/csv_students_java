public class Subject {
    private String name = "-1";
    private String lecturerName = "-1";
    private String lecturerSurname = "-1";
    private int auditory = -1;

    public Subject(String name, String lecturerName, String lecturerSurname, int auditory) {
        this.name = name;
        this.lecturerName = lecturerName;
        this.lecturerSurname = lecturerSurname;
        this.auditory = auditory;
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

    public int getAuditory() {
        return auditory;
    }

    public void setAuditory(int auditory) {
        this.auditory = auditory;
    }



}
