package StudentGrade.pojo;

public class usr {
    private String Email;
    private String Passwd;
    private int Urole;
    private String Id;

    public usr() {
    }

    public usr(String email, String password, int urole, String id) {
        Email = email;
        Passwd = password;
        Urole = urole;
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String password) {
        Passwd = password;
    }

    public int getUrole() {
        return Urole;
    }

    public void setUrole(int urole) {
        Urole = urole;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "usr{" +
                "Email='" + Email + '\'' +
                ", Passwd='" + Passwd + '\'' +
                ", Urole='" + Urole + '\'' +
                ", Id='" + Id + '\'' +
                '}';
    }
}
