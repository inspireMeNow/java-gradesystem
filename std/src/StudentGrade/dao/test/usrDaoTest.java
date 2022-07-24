package StudentGrade.dao.test;

import StudentGrade.pojo.*;
import StudentGrade.dao.impl.usrDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class usrDaoTest {

    @Test
    public void login() {
        System.out.println(new usrDaoImpl().login("10000000","123456"));
    }

    @Test
    public void register() {
        System.out.println(new usrDaoImpl().register(new usr("111111@stdu.edu.cn","123456",1,"20230001")));

    }
}