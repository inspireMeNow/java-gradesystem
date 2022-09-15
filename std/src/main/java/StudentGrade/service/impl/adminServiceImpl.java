package StudentGrade.service.impl;

import StudentGrade.dao.adminDao;
import StudentGrade.dao.impl.adminDaoImpl;
import StudentGrade.pojo.*;
import StudentGrade.service.adminService;
import StudentGrade.tool.Encrypt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class adminServiceImpl implements adminService {
    private adminDao adminDao = new adminDaoImpl();
    private Encrypt encrypt=new Encrypt();
    public List<student> findAllStu() {
        return adminDao.findAllStu();
    }

    public int updateStuBySno(student student) {
        student.setCLcode(adminDao.findCLcodeByCLname(student.getCLname()));
        student.setScode(adminDao.findScodeBySname(student.getSpname()));
        if(adminDao.findSingleSpecialityByScode(student.getScode()).getScode()==null){
            return 0;
        }
        if (student.getSname() != null && student.getScode() != null)
            if (student.getScode().matches("[A-Z]{2}[0-9]{4}") && student.getSname().length() <= 20 && student.getCLcode().matches("[A-Z]{2}[0-9]{4}") && student.getSsex().matches("男|女")) {
                return adminDao.updateStuBySno(student);
            }
        return 0;
    }

    public int deleteStuBySno(String Sno) {
        String str = adminDao.findUsrById(Sno).getId();
        if (adminDao.deleteStuBySno(Sno) != 0) {
            if (str != null) {
                adminDao.deleteUsr(Sno);
            }
            return 1;
        }
        return 0;
    }

    public List<classes> findAllClass() {
        return adminDao.findAllClass();
    }


    public int deleteClassByCLcode(String CLcode) {
        return adminDao.deleteClassByCLcode(CLcode);
    }

    public List<speciality> findAllSpec() {
        return adminDao.findAllSpec();
    }

    public int updateSpec(speciality speciality, String Scode) {
        if(speciality.getScode()==null){
            return 0;
        }else {//CLcode不能为空
            if(adminDao.findSingleSpecialityByScode(speciality.getScode()).getScode()!=null&&!speciality.getScode().equals(Scode)){
                return 0;
            }//违背主码约束
            else{
                if(adminDao.findCollegeByCocode(speciality.getCocode()).getCocode()==null){
                    return 0;
                }
                if(speciality.getSname()!=null){
                    if(speciality.getSname().length()<=20){
                        if(!speciality.getScode().equals(Scode)){
                            List<classes> classes=adminDao.findClassByScode(Scode);
                            for(int i=0;i<classes.size();i++){
                                adminDao.deleteClassByCLcode(classes.get(i).getCLcode());
                            }
                        }
                        return adminDao.updateSpec(speciality,Scode);
                    }
                }
            }
        }
        return 0;
    }

    public int deleteSpecByScode(String Scode) {
        return adminDao.deleteSpecByScode(Scode);
    }

    public List<course> findAllCourse() {
        return adminDao.findAllCourse();
    }


    public int deleteCourseByCnoAndScode(String Cno, String Scode) {
        return adminDao.deleteCourseByCnoAndScode(Cno, Scode);
    }


    public List<college> findAllCollege() {
        return adminDao.findAllCollege();
    }


    public int deleteCollegeByCocode(String Cocode) {
        return adminDao.deleteCollegeByCocode(Cocode);
    }

    public int addUsr(usr usr) //添加用户
    {
        List<usr> u=adminDao.findAllUsr();
        for(int i=0;i<u.size();i++){
            if(u.get(i).getEmail().equals(usr.getEmail())){
                return 0;
            }
        }
        if (usr.getEmail() != null && usr.getPasswd() != null)
            if (usr.getId().matches("[0-9]{8}") && usr.getEmail().length() <= 20 && usr.getPasswd().length() <= 20) {
                if (usr.getUrole() == 1) {
                    if (new adminServiceImpl().findStudentBySno(usr.getId()).getSno() != null) {
                        usr.setPasswd(encrypt.md5(usr.getPasswd()));
                        return adminDao.addUsr(usr);//主码约束已在Dao处理
                    } else return 0;
                } else {
                    if (new adminServiceImpl().findTnameByTno(usr.getId()) != null) {
                        usr.setPasswd(encrypt.md5(usr.getPasswd()));
                        return adminDao.addUsr(usr);//主码约束已在Dao处理
                    } else return 0;
                }
            }
        return 0;
    }

    public int updateUsr(usr usr) //修改用户
    {
        if (usr.getEmail() != null && usr.getPasswd() != null)
            if (usr.getEmail().length() <= 20 && usr.getPasswd().length() <= 20){
                usr.setPasswd(encrypt.md5(usr.getPasswd()));
                return adminDao.updateUsr(usr);
            }
        return 0;
    }

    public int deleteUsr(String Id) //删除用户
    {
        return adminDao.deleteUsr(Id);
    }

    public List<usr> findAllUsr() //查询所有用户
    {
        return adminDao.findAllUsr();
    }

    public int updateClassByCLcode(classes classes, String CLcode) {
        if(classes.getCLcode()==null){
            return 0;
        }else {//CLcode不能为空
            if(adminDao.findClassByCLcode(classes.getCLcode()).getCLcode()!=null&&!classes.getCLcode().equals(CLcode)){
                return 0;
            }//违背主码约束
            else{
                if(adminDao.findSingleSpecialityByScode(classes.getScode()).getScode()==null){
                    return 0;
                }
                if(classes.getCLname()!=null){
                    if(classes.getCLname().length()<=20){
                        if(!classes.getCLcode().equals(CLcode)){
                            List<student> students=adminDao.findStudentByCLcode(CLcode);
                            for(int i=0;i<students.size();i++){
                                adminDao.deleteStuBySno(students.get(i).getSno());
                            }
                        }
                        return adminDao.updateClassByCLcode(classes,CLcode);
                    }
                }
            }
        }
        return 0;
    }


    public int updateCourseByCnoAndScode(course course, String Cno, String Scode) {
        if (adminDao.findCourseByCno(course.getCno()).get(0).getCno() != null) {
            return 0;
        }
        if (course.getCname() != null)
            if (course.getCname().length() <= 20 && course.getCredit() > 0 && course.getCredit() < 20) {
                return adminDao.updateCourseByCnoAndScode(course, Cno, Scode);
            }
        return 0;
    }

    public int updateCollegeByCocode(college college, String Cocode) {
        if(college.getCocode()==null){
            return 0;
        }else {//CLcode不能为空
            if(adminDao.findCollegeByCocode(college.getCocode()).getCocode()!=null&&!college.getCocode().equals(Cocode)){
                return 0;
            }//违背主码约束
            else{
                if(college.getConame()!=null){
                    if(college.getConame().length()<=20){
                        if(!college.getCocode().equals(Cocode)){

                            List<speciality> specialities=adminDao.findSpecialityByCocode(Cocode);
                            for(int i=0;i<specialities.size();i++){
                                adminDao.deleteSpecByScode(specialities.get(i).getScode());
                            }
                        }
                        return adminDao.updateCollegeByCocode(college,Cocode);
                    }
                }
            }
        }
        return 0;
    }

    public List<speciality> findSpecialityByCocode(String Cocode) {
        return adminDao.findSpecialityByCocode(Cocode);
    }

    public List<classes> findClassByScode(String Scode) {
        return adminDao.findClassByScode(Scode);
    }

    public List<student> findStudentByCLcode(String CLcode) {
        return adminDao.findStudentByCLcode(CLcode);
    }

    public List<edu> findEduByCno(String Cno) {
        return adminDao.findEduByCno(Cno);
    }

    public int updateEdu(edu edu, edu previousedu) {
        edu e = adminDao.findSingleEdu(edu.getCno(), edu.getTno(), edu.getCLcode());
        if(adminDao.findClassByCLcode(edu.getCLcode()).getCLcode()==null||adminDao.findTnameByTno(edu.getTno())==null||adminDao.findCourseByCno(edu.getCno()).get(0).getCno()==null){
            return 0;
        }
        if (e.getCno() != null||e.getTno()!=null||e.getCLcode()!=null) {
            return 0;
        }
        return adminDao.updateEdu(edu, previousedu);
    }

    public int deleteEdu(edu edu) {
        return adminDao.deleteEdu(edu);
    }

    public student findStudentBySno(String Sno) {
        return adminDao.findStudentBySno(Sno);
    }

    //根据id查用户
    public usr findUsrById(String id) {
        return adminDao.findUsrById(id);
    }

    //根据班级代码查班
    public classes findClassByCLcode(String CLcode) {
        return adminDao.findClassByCLcode(CLcode);
    }

    //根据专业代码查专业
    public speciality findSingleSpecialityByScode(String Scode) {
        return adminDao.findSingleSpecialityByScode(Scode);
    }

    //根据课程代码查课程
    public course findCourseByCnoAndScode(String Cno, String Scode) {
        return adminDao.findCourseByCnoAndScode(Cno, Scode);
    }

    public List<course> findCourseByCno(String Cno) {
        return adminDao.findCourseByCno(Cno);
    }

    //根据学院代码查学院
    public college findCollegeByCocode(String Cocode) {
        return adminDao.findCollegeByCocode(Cocode);
    }

    public List<edu> findEduByCnoTno(String Cno, String Tno) {
        return adminDao.findEduByCnoTno(Cno, Tno);
    }

    public edu findSingleEdu(String Cno, String Tno, String CLcode) {
        return adminDao.findSingleEdu(Cno, Tno, CLcode);
    }

    public String findScodeBySname(String Sname) {
        return adminDao.findScodeBySname(Sname);
    }

    public String findCLcodeByCLname(String CLname) {
        return adminDao.findCLcodeByCLname(CLname);
    }

    public String findCocodeByConame(String Coname) {
        return adminDao.findCocodeByConame(Coname);
    }

    public String findTnameByTno(String Tno) {
        return adminDao.findTnameByTno(Tno);
    }

    public int addStudent(student student) {
        student.setCLcode(adminDao.findCLcodeByCLname(student.getCLname()));
        student.setScode(adminDao.findScodeBySname(student.getSpname()));
        String CLcode = student.getCLcode();
        String Scode = student.getScode();
        if (student.getSname() != null && student.getScode() != null && student.getCLcode() != null)
            if (CLcode.matches("[A-Z]{2}[0-9]{4}") && Scode.matches("[A-Z]{2}[0-9]{4}") && student.getSsex().matches("男|女") && student.getSname().length() <= 20 && student.getSno().matches("[0-9]{8}")) {
                if (adminDao.findStudentBySno(student.getSno()).getSno() == null) {//主码约束
                    //外码约束
                    if (adminDao.findClassByCLcode(CLcode).getCLcode() != null && adminDao.findSingleSpecialityByScode(Scode).getScode() != null) {
                        return adminDao.addStudent(student);
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        return 0;
    }

    public int addTeacher(teacher teacher) {
        //Cocode暂时不做处理
        if (teacher.getTname() != null)
            if (teacher.getTno().matches("[0-9]{8}") && teacher.getTname().length() <= 20 && teacher.getTsex().matches("男|女") && teacher.getCocode().matches("[A-Z][0-9][0-9][0-9][0-9][A-Z]"))
                if (adminDao.findTnameByTno(teacher.getTno()) != null) {//主码约束
                    return adminDao.addTeacher(teacher);
                } else {
                    return 0;
                }
        return 0;
    }

    public int addCourse(course course) {
        String Scode = course.getScode();
        if (course.getCname().length() <= 20 && course.getCno().matches("[A-Z]{2}[0-9]{4}") && course.getCredit() > 0 && course.getCredit() < 20) {
            if (adminDao.findCourseByCno(course.getCno()).size() == 0) {
                if (adminDao.findSingleSpecialityByScode(Scode).getScode() != null) {
                    return adminDao.addCourse(course);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    public int addClass(classes classes) {
        String Scode = classes.getScode();
        if (classes.getCLname().length() <= 20 && classes.getCLcode().matches("[A-Z]{2}[0-9]{4}") && classes.getScode().matches("[A-Z]{2}[0-9]{4}"))
            if (adminDao.findClassByCLcode(classes.getCLcode()).getCLcode() == null) {
                if (adminDao.findSingleSpecialityByScode(Scode).getScode() != null) {
                    return adminDao.addClass(classes);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        return 0;
    }

    public int addSpeciality(speciality speciality) {
        String Cocode = speciality.getCocode();
        if (speciality.getSname().length() <= 20 && speciality.getScode().matches("[A-Z]{2}[0-9]{4}") && speciality.getCocode().matches("[A-Z][0-9][0-9][0-9][0-9][A-Z]"))
            if (adminDao.findSingleSpecialityByScode(speciality.getScode()).getScode() == null) {
                if (adminDao.findCollegeByCocode(Cocode).getCocode() != null) {
                    return adminDao.addSpeciality(speciality);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        return 0;
    }

    public int addCollege(college college) {
        if (college.getConame().length() <= 20 && college.getCocode().matches("[A-Z][0-9][0-9][0-9][0-9][A-Z]"))
            if (adminDao.findCollegeByCocode(college.getCocode()).getCocode() == null) {
                return adminDao.addCollege(college);
            } else {
                return 0;
            }
        return 0;
    }

    public int addEdu(edu edu) {
        String Cno = edu.getCno();
        String Tno = edu.getTno();
        String CLcode = adminDao.findCLcodeByCLname(edu.getCLname());
        edu.setCLcode(CLcode);
        edu e = adminDao.findSingleEdu(edu.getCno(), edu.getTno(), edu.getCLcode());
        if (e.getCno() != null) {
            return 0;
        }
        if (Cno != null && Tno != null && edu.getCLcode() != null) {
            if (edu.getCno().matches("[A-Z]{2}[0-9]{4}") && edu.getTno().matches("[0-9]{8}") && edu.getCLcode().matches("[A-Z]{2}[0-9]{4}")) {
                if (adminDao.findCourseByCno(Cno).size() != 0 && adminDao.findTnameByTno(Tno) != null && adminDao.findClassByCLcode(CLcode).getCLcode() != null) {
                    System.out.println("if1");
                    return adminDao.addEdu(edu);
                } else {
                    System.out.println("if2");
                    return 0;
                }
            } else {
                System.out.println("if3");
                return 0;
            }
        }
        return 0;
    }
}
