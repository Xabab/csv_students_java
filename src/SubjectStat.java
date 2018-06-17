public class SubjectStat extends Subject{
    private int mark100;
    private int mark5;

    public SubjectStat(String name, String lecturerName, String lecturerSurname, int auditory, int mark100) {
        super(name, lecturerName, lecturerSurname, auditory);
        this.mark100 = mark100;

        if (mark100 < 0) mark5 = -1;
        else if (mark100 == 0) mark5 = 0;
        else if (mark100 < 60) mark5 = 2;
        else if (mark100 < 75) mark5 = 3;
        else if (mark100 < 90) mark5 = 4;
        else mark5 = 5;
    }

    public int getMark100() {
        return mark100;
    }

    public void setMark100(int mark100) {
        this.mark100 = mark100;
    }

    public int getMark5() {
        return mark5;
    }

    public void setMark5(int mark5) {
        this.mark5 = mark5;
    }
}
