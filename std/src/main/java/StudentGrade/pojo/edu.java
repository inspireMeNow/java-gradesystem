package StudentGrade.pojo;

public class edu {
    private String Tno;
    private String Tname;
    private String Cno;
    private String Cname;
    private String CLcode;
    private String CLname;

    public edu() {
    }

    public edu(String tno, String tname, String cno, String cname, String CLcode, String CLname) {
        Tno = tno;
        Tname = tname;
        Cno = cno;
        Cname = cname;
        this.CLcode = CLcode;
        this.CLname = CLname;
    }

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCLcode() {
        return CLcode;
    }

    public void setCLcode(String CLcode) {
        this.CLcode = CLcode;
    }

    public String getCLname() {
        return CLname;
    }

    public void setCLname(String CLname) {
        this.CLname = CLname;
    }

    @Override
    public String toString() {
        return "edu{" +
                "Tno='" + Tno + '\'' +
                ", Tname='" + Tname + '\'' +
                ", Cno='" + Cno + '\'' +
                ", Cname='" + Cname + '\'' +
                ", CLcode='" + CLcode + '\'' +
                ", CLname='" + CLname + '\'' +
                '}';
    }
}
