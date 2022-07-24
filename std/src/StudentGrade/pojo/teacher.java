package StudentGrade.pojo;

public class teacher {
    private String Tno;
    private String Tname;
    private String Tsex;
    private String Cocode;

    public teacher() {
    }

    public teacher(String tno, String tname, String tsex, String cocode) {
        Tno = tno;
        Tname = tname;
        Tsex = tsex;
        Cocode = cocode;
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

    public String getTsex() {
        return Tsex;
    }

    public void setTsex(String tsex) {
        Tsex = tsex;
    }

    public String getCocode() {
        return Cocode;
    }

    public void setCocode(String CLcode) {
        this.Cocode = CLcode;
    }

    @Override
    public String toString() {
        return "teacher{" +
                "Tno='" + Tno + '\'' +
                ", Tname='" + Tname + '\'' +
                ", Tsex='" + Tsex + '\'' +
                ", CLcode='" + Cocode + '\'' +
                '}';
    }
}
