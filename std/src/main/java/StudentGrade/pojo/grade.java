package StudentGrade.pojo;

public class grade {
    private String Sno;
    private String Cno;
    private String Sname;
    private String Cname;
    private String Cproperty;
    private int Term;
    private int Syear;
    private int Credit;
    private int Grade;
    private String Remark;

    public grade() {
    }

    public grade(String sno, String cno, String sname, String cname, String cproperty, int term, int syear, int credit, int grade, String remark) {
        Sno = sno;
        Cno = cno;
        Sname = sname;
        Cname = cname;
        Cproperty = cproperty;
        Term = term;
        Syear = syear;
        Credit = credit;
        Grade = grade;
        Remark = remark;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
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

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int credit) {
        Credit = credit;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public String toString() {
        return "grade{" +
                "Sno='" + Sno + '\'' +
                ", Cno='" + Cno + '\'' +
                ", Sname='" + Sname + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Cproperty='" + Cproperty + '\'' +
                ", Term=" + Term +
                ", Syear=" + Syear +
                ", Credit=" + Credit +
                ", Grade=" + Grade +
                ", Remark='" + Remark + '\'' +
                '}';
    }
}
