package StudentGrade.test;

import StudentGrade.pojo.usr;
import StudentGrade.service.impl.studentServiceImpl;
import StudentGrade.service.studentService;
import org.junit.Test;

public class studentServiceImplTest {
    private studentService studentService=new studentServiceImpl();
    @Test
    public void findBySno() {
        System.out.println(studentService.findBySno("20220156"));
    }

    @Test
    public void findAllGrade() {
        System.out.println(studentService.findAllGrade("20220125"));
    }

    @Test
    public void findGradeByCname() {
        System.out.println(studentService.findGradeByCname("20220025","通信工程"));
    }

    @Test
    public void findGradeByCpro() {
        System.out.println(studentService.findGradeByCpro("20220026","必修"));
    }

    @Test
    public void findCourse() {
        System.out.println(studentService.findCourse("20220027"));
    }

    @Test
    public void testUpdateUsr() {
        System.out.println(studentService.updateUsr(new usr("12345@123.com","20220019",3,"20220019")));
    }
}