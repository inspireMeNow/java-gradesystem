package StudentGrade.service;


import StudentGrade.pojo.*;

import java.util.List;
import java.util.SplittableRandom;

public interface teacherService {
    public abstract int addOrUpdateGrade(String Sno,String Cno,int Grade,String Remark);
    //删除成绩(仅仅是将成绩清空即Grade赋值为null，Remark的值为“被删除”与未选课的情况区分出来)
    public abstract int deleteGrade(String Sno,String Cno);
    //查询所教课程
    public abstract List<course> findCourse(String Tno) ;
    //查询所教课程对应班级
    public abstract List<classes> findClassByCno(String Tno,String  Cno) ;
    //查询自己所教某一班级的学生
    public abstract List<student> findStudentByCLcode(String CLcode) ;
    //查询学生某门课的成绩
    public abstract List<grade> findGradeBySnoAndCLcode(String Sno, String Cno);
    //修改账户信息
    public abstract int updateUsr(usr usr);
    public abstract List<List<grade>> findGradeByCnoCLcode(String Cno, String CLcode);
    //根据工号查教师
    public abstract teacher findByTno(String Tno);
    //根据课程号和专业号查课程
    public abstract course findCourseByCnoAndScode(String Cno,String Scode);
    //根据课程号查课程
    public abstract List<course> findCourseByCno(String Cno);
    //根据工号查教学信息
    public abstract List<edu> findEduByTno(String Tno);
}
