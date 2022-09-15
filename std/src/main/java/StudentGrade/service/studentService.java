package StudentGrade.service;

import StudentGrade.pojo.course;
import StudentGrade.pojo.grade;
import StudentGrade.pojo.student;
import StudentGrade.pojo.usr;

import java.util.List;

public interface studentService{
    //查询个人信息
    public abstract student findBySno(String Sno);
    //查询所有的成绩
    public abstract List<grade> findAllGrade(String sno);
    //课程名查询成绩
    public abstract List<grade> findGradeByCname(String Sno, String Cname);
    //课程性质查询
    public abstract List<grade> findGradeByCpro(String Sno,String Cproperty);
    //查询选课信息
    public abstract List<course> findCourse(String Sno);
    // 修改账户信息
    public abstract int updateUsr(usr usr);
    //根据专业号查学院名
    public abstract String findConameByScode(String Scode);
    //算学分
    public abstract int addCredit(String Sno) ;
    public abstract float averageGrade(String Sno);
}
