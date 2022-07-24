package StudentGrade.service.impl;

import StudentGrade.dao.impl.studentDaoImpl;
import StudentGrade.dao.studentDao;
import StudentGrade.pojo.course;
import StudentGrade.pojo.grade;
import StudentGrade.pojo.student;
import StudentGrade.pojo.usr;
import StudentGrade.service.studentService;
import StudentGrade.tool.Encrypt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class studentServiceImpl implements studentService {
    private studentDao studentDao = new studentDaoImpl();
    private Encrypt encrypt=new Encrypt();
    public student findBySno(String Sno) {

        return studentDao.findBySno(Sno);
//        return new student("20220019","黄进军","男","AS0002","土2001-2","AS1000","土木工程");
    }

    public List<grade> findAllGrade(String Sno) //查询该学生的所有成绩
    {
        return studentDao.findAllGrade(Sno);
        /*List<grade> grade=new ArrayList<grade>();
        grade.add(new grade("CS1015","AS1012","通信工程","通信原理","必修",1,1,5,85,""));
        return grade;*/
    }

    public List<grade> findGradeByCname(String Sno, String Cname) //课程名查询成绩
    {
        return studentDao.findGradeByCname(Sno,Cname);
        /*List<grade> grade=new ArrayList<grade>();
        grade.add(new grade("CS1015","AS1012","通信工程","通信原理","必修",1,1,5,85,""));
        return grade;*/
    }

    public List<grade> findGradeByCpro(String Sno, String Cproperty) //课程性质查询
    {
        return studentDao.findGradeByCpro(Sno,Cproperty);
        /*List<grade> grade=new ArrayList<grade>();
        grade.add(new grade("CS1015","AS1012","通信工程","通信原理","必修",1,1,5,85,""));
        return grade;*/
    }

    public List<course> findCourse(String Sno) //查询选课信息
    {
        return studentDao.findCourse(Sno);
        /*List<course> list=new ArrayList<course>();
        list.add(new course("CS1015","AS1012","通信工程","通信原理","必修",5,1,1));
        return list;*/
    }

    public int updateUsr(usr usr) // 修改账户信息
    {
        if(usr.getPasswd()!=null&&usr.getEmail()!=null){
            if(usr.getPasswd().length()<=20&&usr.getEmail().length()<=20){
                usr.setPasswd(encrypt.md5(usr.getPasswd()));
                return studentDao.updateUsr(usr);
            }
        }
        return 0;
        /*if(usr.getId()=="20220019")
            return 0;
        return 1;*/
    }
    public  String findConameByScode(String Scode){
        return studentDao.findConameByScode(Scode);
    }

    public int addCredit(String Sno) {
        studentDao studentDao1=new studentDaoImpl();
        int sum=0;
        List<grade> grade=studentDao1.findAllGrade(Sno);
        int i=0;
        while(i<grade.size()){
            if(grade.get(i).getGrade()>=60)
            sum=sum+grade.get(i).getCredit();
            i++;
        }
        return sum;
    }
    public float averageGrade(String Sno){
        List<grade> grade=studentDao.findAllGrade(Sno);
        int sum=0;
        int i=0;
        while(i<grade.size()){
            sum=sum+grade.get(i).getGrade();
            i++;
        }
        return (float)sum/(float)grade.size();
    }
}
