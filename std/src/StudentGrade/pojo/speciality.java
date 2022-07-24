package StudentGrade.pojo;

public class speciality {
    private String Scode;
    private String Sname;
    private String Cocode;
    public speciality(){

    }

    public speciality(String scode, String sname, String cocode) {
        Scode = scode;
        Sname = sname;
        Cocode = cocode;
    }

    public String getScode() {
        return Scode;
    }

    public void setScode(String scode) {
        Scode = scode;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getCocode() {
        return Cocode;
    }

    public void setCocode(String cocode) {
        Cocode = cocode;
    }

    @Override
    public String toString() {
        return "speciality{" +
                "Scode='" + Scode + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Cocode='" + Cocode + '\'' +
                '}';
    }
}
