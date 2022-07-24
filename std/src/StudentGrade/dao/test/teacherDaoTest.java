package StudentGrade.dao.test;

import StudentGrade.dao.impl.studentDaoImpl;
import StudentGrade.dao.impl.teacherDaoImpl;
import StudentGrade.dao.teacherDao;
import org.junit.Test;

import StudentGrade.pojo.*;
import StudentGrade.dao.impl.usrDaoImpl;
import org.junit.Test;
import static org.junit.Assert.*;

public class teacherDaoTest {
    teacherDao teaDao=new teacherDaoImpl();

    @Test
    public void addOrUpdateGrade() {
        String Remark = "正常";
        System.out.println(new teacherDaoImpl().addOrUpdateGrade("20220001","CS1000",99,Remark));
    }

    @Test
    public void deleteGrade() {
        System.out.println(new teacherDaoImpl().deleteGrade("20220001","CS1000"));
    }

    @Test
    public void findCourse() {
        System.out.println(new teacherDaoImpl().findCourse("10000001"));
    }

    @Test
    public void findClassByCno() {
        System.out.println(new teacherDaoImpl().findClassByCno("10000001","CS1000"));
    }

    @Test
    public void findStudentByCLcode() {
        System.out.println(new teacherDaoImpl().findStudentByCLcode("AS0001"));
    }

    @Test
    public void findGradeBySnoAndCLcode() {
        System.out.println(new teacherDaoImpl().findGradeBySnoAndCLcode("20220001","CS1000"));
    }

    @Test
    public void updateUsr() {
        System.out.println(new studentDaoImpl().updateUsr(new usr("222222@stdu.edu.cn","2222222",2,"20230001")));
    }
    @Test
    public void findCourseByCno(){
        System.out.println(teaDao.findCourseByCno("CS1000"));
    }
    @Test
    public void findCourseByCnoAndCourse(){
        System.out.println(teaDao.findCourseByCnoAndScode("CS1000","AS1000"));
    }
    @Test
    public void findEduByTno(){
        System.out.println(new teacherDaoImpl().findEduByTno("10000001"));
    }

}