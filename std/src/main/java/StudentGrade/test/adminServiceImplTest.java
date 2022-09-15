package StudentGrade.test;

import StudentGrade.dao.impl.adminDaoImpl;
import StudentGrade.pojo.*;
import StudentGrade.service.adminService;
import StudentGrade.service.impl.adminServiceImpl;
import org.junit.Test;

public class adminServiceImplTest  {

    private adminService adminService=new adminServiceImpl();
    @Test
    public void testFindAllStu() {
        System.out.println(adminService.findAllStu());
    }

    @Test
    public void testUpdateStuBySno() {
        System.out.println(adminService.updateStuBySno(new student("20220019", "黄进军", "男", "AS0002", "土2001-2", "AS1000", "土木工程")));
    }

    @Test
    public void testDeleteStuBySno() {
        System.out.println(adminService.deleteStuBySno("20220019"));
    }

    @Test
    public void testFindAllClass() {
        System.out.println(adminService.findAllClass());
    }

    @Test
    public void testDeleteClassByCLcode() {
        System.out.println(adminService.deleteClassByCLcode("AS0001"));
    }

    @Test
    public void testFindAllSpec() {
        System.out.println(adminService.findAllSpec());
    }

    @Test
    public void testUpdateSpec() {
        System.out.println(adminService.updateSpec(new speciality("AS1000","土木工程","土木工程学院"),"AS1000"));
    }

    @Test
    public void testDeleteSpecByScode() {
        System.out.println(adminService.deleteSpecByScode("AS1000"));
    }

    @Test
    public void testFindAllCourse() {
        System.out.println(adminService.findAllCourse());
    }

    @Test
    public void testDeleteCourseByCnoAndScode() {
        System.out.println(adminService.deleteCourseByCnoAndScode("CS1000","AS1000"));
    }

    @Test
    public void testFindAllCollege() {
        System.out.println(adminService.findAllCollege());
    }

    @Test
    public void testDeleteCollegeByCocode() {
        System.out.println(adminService.deleteCollegeByCocode("A1000S"));
    }

    @Test
    public void testAddUsr() {
        System.out.println(adminService.addUsr(new usr("123456@123.com","20220019",3,"20220019")));
    }

    @Test
    public void testUpdateUsr() {
        System.out.println(adminService.updateUsr(new usr("123564@123.com","20220019",3,"20220019")));
    }

    @Test
    public void testDeleteUsr() {
        System.out.println(adminService.deleteUsr("20220019"));
    }

    @Test
    public void testFindAllUsr() {
        System.out.println(adminService.findAllUsr());
    }

    @Test
    public void testUpdateClassByCLcode() {
        System.out.println(adminService.updateClassByCLcode(new classes("AS0021","土2003-1","AS1002"),"AS0021"));
    }

    @Test
    public void testUpdateCourseByCnoAndScode() {
        System.out.println(adminService.updateCourseByCnoAndScode(new course("CS1015","AS1012","通信工程","通信原理","必修",5,1,1),"CS1015","AS1012"));
    }

    @Test
    public void testUpdateCollegeByCocode() {
        System.out.println(adminService.updateCollegeByCocode(new college("A1000S","土木工程学院"),"AS1000S"));
    }

    @Test
    public void testFindSpecialityByCocode() {
        System.out.println(adminService.findSpecialityByCocode("B1002S"));
    }

    @Test
    public void testFindClassByScode() {
        System.out.println(adminService.findClassByScode("AS1001"));
    }

    @Test
    public void testFindStudentByCLcode() {
        System.out.println(adminService.findStudentByCLcode("BS0001"));
    }

    @Test
    public void testFindEduByCno() {
        System.out.println(adminService.findEduByCno("CS1001"));
    }

    @Test
    public void testUpdateEdu() {
        System.out.println(adminService.updateEdu(new edu("1000000","祝潇","CS1010","计算机网络","CM0021","信2003-1"),new edu("1000000","祝潇","CS1010","计算机网络","CM0021","信2003-1")));
    }

    @Test
    public void testDeleteEdu() {
        System.out.println(adminService.deleteEdu(new edu("1000000","祝潇","CS1010","计算机网络","CM0021","信2003-1")));
    }

    @Test
    public void testFindStudentBySno() {
        System.out.println(adminService.findStudentBySno("20220020"));
    }

    @Test
    public void testFindUsrById() {
        System.out.println(adminService.findUsrById("20220016"));
    }

    @Test
    public void testFindClassByCLcode() {
        System.out.println(adminService.findClassByCLcode("CM0021"));
    }

    @Test
    public void testFindSingleSpecialityByScode() {
        System.out.println(adminService.findSingleSpecialityByScode("AS1001"));
    }

    @Test
    public void testFindCourseByCnoAndScode() {
        System.out.println(adminService.findCourseByCnoAndScode("CS1000","AS1000"));
    }
    @Test
    public void testFindCourseByCno(){
        System.out.println(adminService.findCourseByCno("CS1000"));
    }
    @Test
    public void testFindCollegeByCocode() {
        System.out.println(adminService.findCollegeByCocode("D1007Z"));
    }

    @Test
    public void testFindEduByCnoTno() {
        System.out.println(adminService.findEduByCnoTno("CS1015","10000015"));
    }

    @Test
    public void testFindSingleEdu() {
        System.out.println(adminService.findSingleEdu("CS1015","10000015","AS1002"));
    }

    @Test
    public void testFindScodeBySname() {
        System.out.println(adminService.findScodeBySname("祝潇"));
    }

    @Test
    public void testFindCLcodeByCLname() {
        System.out.println(adminService.findCLcodeByCLname("信2001-1"));
    }

    @Test
    public void testFindCocodeByConame() {
        System.out.println(adminService.findCocodeByConame("土木工程学院"));
    }
    @Test
    public void testAddStudent(){
        System.out.println(new adminServiceImpl().addStudent(new student("20220501","李四","男",null,"土2001-2",null,"土木工程")));
    }
    @Test
    public void testAddTeacher(){
        System.out.println(new adminServiceImpl().addTeacher(new teacher("10000020","小张","女",null)));
    }
    @Test
    public void testAddCourse(){
        System.out.println(new adminServiceImpl().addCourse(new course("CC1020","AA1012","无线电技术","通信工程","选修",2,2,2)));
    }
    @Test
    public void testAddClass(){
        System.out.println(new adminServiceImpl().addClass(new classes("DZ0022","电2003-2","Aa1012")));
    }
    @Test
    public void testAddSpeciality(){
        System.out.println(new adminServiceImpl().addSpeciality(new speciality("AS1013","微电子工程","D1007Z")));
    }
    @Test
    public void testAddCollege(){
        System.out.println(new adminServiceImpl().addCollege(new college("D1008D","外语学院")));
    }
    @Test
    public void testAddEdu(){
        System.out.println(new adminServiceImpl().addEdu(new edu("10000017","钟谦飚","CS1014","通信原理","DZ0010","")));
    }
}