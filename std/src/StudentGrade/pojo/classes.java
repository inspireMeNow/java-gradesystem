package StudentGrade.pojo;

public class classes {
    private String CLcode;
    private String CLname;
    private String Scode;

    public classes(){

    }

    public classes(String CLcode, String CLname, String scode) {
        this.CLcode = CLcode;
        this.CLname = CLname;
        this.Scode = scode;
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

    public String getScode() {
        return Scode;
    }

    public void setScode(String scode) {
        this.Scode = scode;
    }

    @Override
    public String toString() {
        return "classes{" +
                "CLcode='" + CLcode + '\'' +
                ", CLname='" + CLname + '\'' +
                ", Scode='" + Scode + '\'' +
                '}';
    }
}
