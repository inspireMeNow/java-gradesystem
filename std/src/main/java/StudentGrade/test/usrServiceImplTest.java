package StudentGrade.test;

import StudentGrade.pojo.usr;
import StudentGrade.service.impl.usrServiceImpl;
import StudentGrade.service.usrService;
import org.junit.Test;

public class usrServiceImplTest {

    private usrService usrService=new usrServiceImpl();
    @Test
    public void login() {
        System.out.println(usrService.login("10000000","123456"));
    }

    @Test
    public void register() {
        System.out.println(usrService.register(new usr("123456@123.com","20220001",1,"20220001")));
    }

    @Test
    public void exists() {
        System.out.println(usrService.exists(null));
    }
}