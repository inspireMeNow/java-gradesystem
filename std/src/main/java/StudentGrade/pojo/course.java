package StudentGrade.pojo;

public class course {
    private String Cno;
    private String Scode;
    private String Spname;
    private String Cname;
    private String Cproperty;
    private int Credit;
    private int Term;
    private int Syear;
    public course() {
    }

    public course(String cno, String scode, String cname, String sname, String cproperty, int credit, int term, int syear) {
        Cno = cno;
        Scode = scode;
        Cname = cname;
        Spname = sname;
        Cproperty = cproperty;
        Credit = credit;
        Term = term;
        Syear = syear;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getScode() {
        return Scode;
    }

    public void setScode(String scode) {
        Scode = scode;
    }

    public String getSpname() {
        return Spname;
    }

    public void setSpname(String spname) {
        Spname = spname;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCproperty() {
        return Cproperty;
    }

    public void setCproperty(String cproperty) {
        Cproperty = cproperty;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int credit) {
        Credit = credit;
    }

    public int getTerm() {
        return Term;
    }

    public void setTerm(int term) {
        Term = term;
    }

    public int getSyear() {
        return Syear;
    }

    public void setSyear(int syear) {
        Syear = syear;
    }

    @Override
    public String toString() {
        return "course{" +
                "Cno='" + Cno + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Cproperty='" + Cproperty + '\'' +
                ", Credit=" + Credit +
                ", Term=" + Term +
                ", Syear=" + Syear +
                '}';
    }
}
