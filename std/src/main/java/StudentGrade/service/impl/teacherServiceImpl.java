package StudentGrade.service.impl;

import StudentGrade.dao.impl.teacherDaoImpl;
import StudentGrade.dao.teacherDao;
import StudentGrade.pojo.*;
import StudentGrade.service.teacherService;
import StudentGrade.tool.Encrypt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class teacherServiceImpl implements teacherService {
    private teacherDao teacherDao = new teacherDaoImpl();
    private Encrypt encrypt=new Encrypt();
    public int addOrUpdateGrade(String Sno, String Cno, int grade,String Remark) //添加某一学生成绩
    {
        if(grade>=0&&grade<=100)
            return teacherDao.addOrUpdateGrade(Sno,Cno,grade,Remark);
        return 0;
    }

    public int deleteGrade(String Sno, String Cno) //删除某一学生成绩
    {
        return teacherDao.deleteGrade(Sno,Cno);
    }

    public List<course> findCourse(String Tno) //查询所教课程
    {
        return teacherDao.findCourse(Tno);
    }

    public List<classes> findClassByCno(String Tno, String Cno) //查询所教课程对应班级
    {
        return teacherDao.findClassByCno(Tno, Cno);
    }

    public int updateUsr(usr usr)// 修改账户信息
    {
        if(usr.getPasswd()!=null&&usr.getEmail()!=null){
            if(usr.getPasswd().length()<=20&&usr.getEmail().length()<=20){
                usr.setPasswd(encrypt.md5(usr.getPasswd()));
                return teacherDao.updateUsr(usr);
            }
        }
        return 0;
    }

    @Override
    public List<grade> findGradeBySnoAndCLcode(String Sno, String Cno) {

        return teacherDao.findGradeBySnoAndCLcode(Sno,Cno);
        /*List<grade> grade=new ArrayList<grade>();
        grade.add(new grade("CS1015","AS1012","通信工程","通信原理","必修",1,1,5,85,""));
        return grade;*/
    }

    @Override
    public List<student> findStudentByCLcode(String CLcode) {

        return teacherDao.findStudentByCLcode(CLcode);
       /* List<student> student=new ArrayList<student>();
        student.add(new student("20220019","黄进军","男","AS0002","土2001-2","AS1000","土木工程"));
        return student;*/
    }
    @Override
    public List<List<grade>> findGradeByCnoCLcode(String Cno, String CLcode){
        List<List<grade>> grade=new ArrayList<>();
        Iterator<student> iter=teacherDao.findStudentByCLcode(CLcode).iterator();
        while(iter.hasNext()){
            grade.add(teacherDao.findGradeBySnoAndCLcode(iter.next().getSno(),Cno));
        }
        return grade;
    }
    @Override
    public  teacher findByTno(String Tno){
        return teacherDao.findByTno(Tno);
    }

    @Override
    public course findCourseByCnoAndScode(String Cno, String Scode) {
        return teacherDao.findCourseByCnoAndScode(Cno,Scode);
    }
    @Override
    public  List<course> findCourseByCno(String Cno){
        return teacherDao.findCourseByCno(Cno);
    }
    //根据工号查教学信息
    public  List<edu> findEduByTno(String Tno){
        return teacherDao.findEduByTno(Tno);
    }
}
