package StudentGrade.pojo;

public class college {
    private String Cocode;
    private String Coname;
    public college(){

    }

    public college(String cocode, String coname) {
        Cocode = cocode;
        Coname = coname;
    }

    public String getCocode() {
        return Cocode;
    }

    public String getConame() {
        return Coname;
    }

    public void setCocode(String cocode) {
        Cocode = cocode;
    }

    public void setConame(String coname) {
        Coname = coname;
    }

    @Override
    public String toString() {
        return "college{" +
                "Cocode='" + Cocode + '\'' +
                ", Coname='" + Coname + '\'' +
                '}';
    }
}
