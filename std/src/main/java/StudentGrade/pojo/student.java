package StudentGrade.pojo;

public class student {
    private String Sno;
    private String Sname;
    private String Ssex;
    private String CLcode;
    private String CLname;
    private String Scode;
    private String Spname;

    public student() {
    }

    public student(String sno, String sname, String ssex, String CLcode, String CLname, String scode, String spname) {
        Sno = sno;
        Sname = sname;
        Ssex = ssex;
        this.CLcode = CLcode;
        this.CLname = CLname;
        Scode = scode;
        Spname = spname;
    }

    public String getCLname() {
        return CLname;
    }

    public void setCLname(String CLname) {
        this.CLname = CLname;
    }

    public String getSpname() {
        return Spname;
    }

    public void setSpname(String spname) {
        Spname = spname;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSsex() {
        return Ssex;
    }

    public void setSsex(String ssex) {
        Ssex = ssex;
    }

    public String getCLcode() {
        return CLcode;
    }

    public void setCLcode(String CLcode) {
        this.CLcode = CLcode;
    }

    public String getScode() {
        return Scode;
    }

    public void setScode(String Scode) {
        this.Scode = Scode;
    }

    @Override
    public String toString() {
        return "student{" +
                "Sno='" + Sno + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Ssex='" + Ssex + '\'' +
                ", CLcode='" + CLcode + '\'' +
                ", CLname='" + CLname + '\'' +
                ", Scode='" + Scode + '\'' +
                ", Spname='" + Spname + '\'' +
                '}';
    }
}
