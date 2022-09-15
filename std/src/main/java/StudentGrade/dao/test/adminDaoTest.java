package StudentGrade.dao.test;

import StudentGrade.dao.impl.adminDaoImpl;
import StudentGrade.pojo.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class adminDaoTest {

    @Test
    public void findAllStu() {
        System.out.println(new adminDaoImpl().findAllStu());
        System.out.println(new adminDaoImpl().findAllStu().size());
    }

    @Test
    public void updateStuBySno() {
        System.out.println(new adminDaoImpl().updateStuBySno(new student("20220001","虞子晶","女","AS0001","土2001-1","AS1000","土木工程")));
    }

    @Test
    public void deleteStuBySno() {
        System.out.println(new adminDaoImpl().deleteStuBySno("20220496"));
    }

    @Test
    public void findAllClass() {
        System.out.println(new adminDaoImpl().findAllClass());
    }

    @Test
    public void updateClassByCLcode() {
        System.out.println(new adminDaoImpl().updateClassByCLcode(new classes("AS0002","土2001-3","AS1003"),"AS0002"));
    }

    @Test
    public void deleteClassByCLcode() {
        System.out.println(new adminDaoImpl().deleteClassByCLcode("DZ0021"));
    }

    @Test
    public void findAllSpec() {
        System.out.println(new adminDaoImpl().findAllSpec());
    }

    @Test
    public void updateSpec() {
        System.out.println(new adminDaoImpl().updateSpec(new speciality("AS1013","通信工程","D1007Z"),"AS1012"));
    }

    @Test
    public void deleteSpecByScode() {
        System.out.println(new adminDaoImpl().deleteSpecByScode("AS1011"));
    }

    @Test
    public void findAllCourse() {
        System.out.println(new adminDaoImpl().findAllCourse());
    }

    @Test
    public void updateCourseByCnoAndScode() {
        System.out.println(new adminDaoImpl().updateCourseByCnoAndScode(new course("CS1002","AS1000","马克思主义","土木工程","必修",3,1,2),"CS1002","AS1000"));
    }

    @Test
    public void deleteCourseByCnoAndScode() {
        System.out.println(new adminDaoImpl().deleteCourseByCnoAndScode("CS1002","AS1000"));
    }

    @Test
    public void findAllCollege() {
        System.out.println(new adminDaoImpl().findAllCollege());
    }

    @Test
    public void updateCollegeByCocode() {
        System.out.println(new adminDaoImpl().updateCollegeByCocode(new college("A1000S","土木学院"),"A1000S"));
    }

    @Test
    public void deleteCollegeByCocode() {
        System.out.println(new adminDaoImpl().deleteCollegeByCocode("A1000S"));
    }

    @Test
    public void addUsr() {
        System.out.println(new adminDaoImpl().addUsr(new usr("333334@stdu.edu.cn","123456",1,"20230002")));
    }

    @Test
    public void updateUsr() {
        System.out.println(new adminDaoImpl().updateUsr(new usr("444444@stdu.edu.cn","123456",1,"20230001")));
    }

    @Test
    public void deleteUsr() {
        System.out.println(new adminDaoImpl().deleteUsr("20230001"));
    }

    @Test
    public void findAllUsr() {
        System.out.println(new adminDaoImpl().findAllUsr());
    }

    @Test
    public void findSpecialityByCocode() {
        System.out.println(new adminDaoImpl().findSpecialityByCocode("C1004M"));
    }

    @Test
    public void findClassByScode() {
        System.out.println(new adminDaoImpl().findClassByScode("AS1006"));
    }

    @Test
    public void findStudentByCLcode() {
        System.out.println(new adminDaoImpl().findStudentByCLcode("CM0001"));
    }

    @Test
    public void findEduByCno() {
        System.out.println(new adminDaoImpl().findEduByCno("CS1009"));
    }

    @Test
    public void updateEdu() {
        edu edu = new edu("10000016","经俐珠","CS1012","信号与系统","CM0021","信2003-1");
        edu previousedu = new edu("10000016","经俐珠","CS1013","单片机设计","DZ0002","电2001-2");
        System.out.println(new adminDaoImpl().updateEdu(edu,previousedu));
    }

    @Test
    public void deleteEdu() {
        System.out.println(new adminDaoImpl().deleteEdu(new edu("10000017","钟谦飚","CS1014","单片机设计","DZ0010","电2003-1")));
    }
    @Test
    public void findStudentBySno() {
        System.out.println(new adminDaoImpl().findStudentBySno("20220001"));
    }

    @Test
    public void findUsrById() {
        System.out.println(new adminDaoImpl().findUsrById("10000000"));
    }

    @Test
    public void findClassByCLcode() {
        System.out.println(new adminDaoImpl().findClassByCLcode("AS0001"));
    }

    @Test
    public void findSingleSpecialityByScode() {
        System.out.println(new adminDaoImpl().findSingleSpecialityByScode("AS1000"));
    }
    @Test
    public void findCourseByCnoAndScode() {
        System.out.println(new adminDaoImpl().findCourseByCnoAndScode("CS1000","AS1000"));
    }
    @Test
    public void findCourseByCno() {
        System.out.println(new adminDaoImpl().findCourseByCno("CS1000"));
    }
    @Test
    public void findCollegeByCocode() {
        System.out.println(new adminDaoImpl().findCollegeByCocode("C1004M"));
    }
    @Test
    public void findEduByCnoTno() {
        System.out.println(new adminDaoImpl().findEduByCnoTno("CS1000","10000001"));
    }

    @Test
    public void findSingleEdu() {
        System.out.println(new adminDaoImpl().findSingleEdu("CS1000","10000001","AS0001"));
    }

    @Test
    public void findScodeBySname() {
        System.out.println(new adminDaoImpl().findScodeBySname("土木工程"));
    }

    @Test
    public void findCLcodeByCLname() {
        System.out.println(new adminDaoImpl().findCLcodeByCLname("信2001-1"));
    }

    @Test
    public void findCocodeByConame() {
        System.out.println(new adminDaoImpl().findCocodeByConame("信息科学与技术学院"));
    }
    @Test
    public void addStudent(){
        System.out.println(new adminDaoImpl().addStudent(new student("20220500","小王","男","CM0032","土2001-1","AS1000","土木工程")));
    }
    @Test
    public void addTeacher(){
        System.out.println(new adminDaoImpl().addTeacher(new teacher("10000020","小张","女",null)));
    }
    @Test
    public void addCourse(){
        System.out.println(new adminDaoImpl().addCourse(new course("CS2000","AS1008","无线电技术","通信工程","选修",2,2,2)));
    }
    @Test
    public void addClass(){
        System.out.println(new adminDaoImpl().addClass(new classes("DZ0022","电2003-2","AS1012")));
    }
    @Test
    public void addSpeciality(){
        System.out.println(new adminDaoImpl().addSpeciality(new speciality("AS1013","微电子工程","D1007Z")));
    }
    @Test
    public void addCollege(){
        System.out.println(new adminDaoImpl().addCollege(new college("D1008D","外语学院")));
    }
    @Test
    public void addEdu(){
        System.out.println(new adminDaoImpl().addEdu(new edu("10000017","钟谦飚","CS1015","通信原理","DZ0002","电2001-2")));
    }
}