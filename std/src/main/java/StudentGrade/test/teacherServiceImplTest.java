package StudentGrade.test;

import StudentGrade.pojo.usr;
import StudentGrade.service.impl.teacherServiceImpl;
import StudentGrade.service.teacherService;
import org.junit.Test;

public class teacherServiceImplTest {

    private teacherService teacherService=new teacherServiceImpl();
    @Test
    public void addOrUpdateGrade() {
        System.out.println(teacherService.addOrUpdateGrade("20220027","AS1012",90,""));
    }

    @Test
    public void deleteGrade() {
        System.out.println(teacherService.deleteGrade("20220127","AS1012"));
    }

    @Test
    public void findCourse() {
        System.out.println(teacherService.findCourse("10000016"));
    }

    @Test
    public void findClassByCno() {
        System.out.println(teacherService.findClassByCno("10000016","CS0001"));
    }

    @Test
    public void updateUsr() {
        System.out.println(teacherService.updateUsr(new usr("123456@123.com","20220250",1,"20220250")));
    }

    @Test
    public void findGradeBySnoAndCLcode() {
        System.out.println(teacherService.findGradeBySnoAndCLcode("20220250","CS0001"));
    }

    @Test
    public void findStudentByCLcode() {
        System.out.println(teacherService.findStudentByCLcode("CM0002"));
    }

    @Test
    public void findGradeByCnoCLcode() {
        System.out.println(teacherService.findGradeByCnoCLcode("CS1009","CM0001"));
    }

    @Test
    public void findCourseByCno(){
        System.out.println(teacherService.findCourseByCno("CS1000"));
    }
    @Test
    public void findCourseByCnoAndScode(){
        System.out.println(teacherService.findCourseByCnoAndScode("CS1000","AS1000"));
    }
}