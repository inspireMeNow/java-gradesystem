package StudentGrade.dao.test;

import StudentGrade.dao.impl.studentDaoImpl;
import StudentGrade.dao.studentDao;
import StudentGrade.pojo.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class studentDaoTest {
    studentDao studentDao=new studentDaoImpl();
    @Test
    public void findBySno() {
        System.out.println(new studentDaoImpl().findBySno("20220001"));
    }

    @Test
    public void findAllGrade() {
        System.out.println(new studentDaoImpl().findAllGrade("20220001"));
        System.out.println(new studentDaoImpl().findAllGrade("20220001").size());
    }

    @Test
    public void findGradeByCname() {
        System.out.println(new studentDaoImpl().findGradeByCname("20220001","高等数学"));
    }

    @Test
    public void findGradeByCpro() {
        System.out.println(new studentDaoImpl().findGradeByCpro("20220001","必修"));
    }

    @Test
    public void findCourse() {
        System.out.println(new studentDaoImpl().findCourse("20220001"));
    }

    @Test
    public void updateUsr() {
        System.out.println(new studentDaoImpl().updateUsr(new usr("111222@stdu.edu.cn","111111",1,"20230001")));
    }
    @Test
    public void findConameByScode(){
        System.out.println(studentDao.findConameByScode("AS1000"));
    }
}