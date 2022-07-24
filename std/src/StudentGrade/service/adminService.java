package StudentGrade.service;

import StudentGrade.pojo.*;

import java.util.List;

public interface adminService {
    public abstract List<student> findAllStu();
    //修改学生信息,学号不进行修改
    public abstract int updateStuBySno(student student);
    //删除学生信息，并没有删除其注册账号
    public abstract int deleteStuBySno(String Sno) ;
    //查询全部班级信息
    public abstract List<classes> findAllClass();
    //修改班级信息
    public abstract int updateClassByCLcode(classes classes,String CLcode);
    //删除班级信息
    public abstract int deleteClassByCLcode(String CLcode);
    //查询全部专业信息
    public abstract List<speciality> findAllSpec();
    //修改专业信息
    public abstract int updateSpec(speciality speciality, String Scode);
    //删除专业信息
    public abstract int deleteSpecByScode(String Scode);
    //查询全部课程
    public abstract List<course> findAllCourse();
    //修改课程信息
    public abstract int updateCourseByCnoAndScode(course course, String Cno,String Scode);
    //删除课程信息
    public abstract int deleteCourseByCnoAndScode(String Cno,String Scode);
    //查询全部学院
    public abstract List<college> findAllCollege();
    //修改学院信息,目前并未启用教师的Cocode字段
    public abstract int updateCollegeByCocode(college college,String Cocode);
    //删除学院信息，目前并未启用教师的Cocode字段
    public abstract int deleteCollegeByCocode(String Cocode);
    //添加用户
    public abstract int addUsr(usr usr);
    //修改用户,角色不会变，Id不会变
    public abstract int updateUsr(usr usr);
    //删除用户，并不删除用户自身信息
    public abstract int deleteUsr(String Id);
    //查询所有用户
    public abstract List<usr> findAllUsr();
    //根据学院代码查专业
    public abstract List<speciality> findSpecialityByCocode(String Cocode);
    //根据专业代码查班级
    public abstract List<classes> findClassByScode(String Scode);
    //根据班级查学生
    public abstract List<student> findStudentByCLcode(String CLcode);
    //根据班级查教学信息
    public abstract List<edu> findEduByCno(String Cno);
    //修改老师教学信息,Cno不变
    public abstract int updateEdu(edu edu,edu previousedu);
    //删除教学信息
    public abstract int deleteEdu(edu edu);
    //根据学号查学生
    public abstract student findStudentBySno(String Sno);
    //根据id查用户
    public abstract usr findUsrById(String id);
    //根据班级代码查班
    public abstract classes findClassByCLcode(String CLcode);
    //根据专业代码查专业
    public abstract speciality findSingleSpecialityByScode(String Scode);
    //根据专业代码和课程号查课程
    public abstract course findCourseByCnoAndScode(String Cno,String Scode);
    //根据课程号查课程
    public abstract List<course> findCourseByCno(String Cno);
    //根据学院代码查学院
    public abstract college findCollegeByCocode(String Cocode);
    public abstract List<edu> findEduByCnoTno(String Cno,String Tno);
    public abstract edu findSingleEdu(String Cno,String Tno,String CLcode);
    public abstract String findScodeBySname(String Sname);
    public abstract String findCLcodeByCLname(String CLname);
    public abstract String findCocodeByConame(String Coname);
    public abstract String findTnameByTno(String Tno);
    public abstract int addStudent(student student);
    public abstract int addTeacher(teacher teacher);
    public abstract int addCourse(course course);
    public abstract int addClass(classes classes);
    public abstract int addSpeciality(speciality speciality);
    public abstract int addCollege(college college);
    public abstract int addEdu(edu edu);
}
