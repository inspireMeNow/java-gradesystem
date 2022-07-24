package StudentGrade.dao.impl;

import StudentGrade.dao.adminDao;
import StudentGrade.pojo.*;
import StudentGrade.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class adminDaoImpl implements adminDao{

    @Override
    public List<student> findAllStu() {
        List<student> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str = "select * from studentview ";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                student tmp = new student();
                tmp.setSno(res.getString("Sno"));
                tmp.setSname(res.getString("Sname"));
                tmp.setSsex(res.getString("Ssex"));
                tmp.setCLcode(res.getString("CLcode"));
                tmp.setCLname(res.getString("CLname"));
                tmp.setScode(res.getString("Scode"));
                tmp.setSpname(res.getString("Spname"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public int updateStuBySno(student student) {
        Connection con= JDBCUtils.getConnection();
        String str = "update student set Sname = '"+student.getSname()+"', Ssex = '"+student.getSsex()+"', CLcode = '"+student.getCLcode()+"', Scode = '"+student.getScode()+"'" +
                "where Sno = '"+student.getSno()+"' and '"+student.getScode()+"' in (select Scode from speciality) and '"+student.getCLcode()+"' in (select CLcode from class)" +
                " and '"+student.getCLname()+"' = (select CLname from class where CLcode = '"+student.getCLcode()+"') " +
                " and '"+student.getSpname()+"' = (select Sname from speciality where Scode = '"+student.getScode()+"') and (Ssex = '男' or Ssex = '女')";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteStuBySno(String Sno) {
        Connection con= JDBCUtils.getConnection();
        String str1 = "delete from student where Sno = '"+Sno+"'";
        String str2 = "delete from grade where Sno = '"+Sno+"'";
        int result1 = 0;
        int result2 = 0;
        try {
            Statement statement = con.createStatement();
            result1 = statement.executeUpdate(str1);
            result2 = statement.executeUpdate(str2);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result1;//只返回第一个是因为那个是主表一定会变化，而其他表中虽有Sno，但是不一定有那一个Sno
    }

    @Override
    public List<classes> findAllClass() {
        List<classes> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str = "select * from class ";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                classes tmp = new classes();
                tmp.setCLcode(res.getString("CLcode"));
                tmp.setCLname(res.getString("CLname"));
                tmp.setScode(res.getString("Scode"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public int updateClassByCLcode(classes classes, String CLcode) {
        Connection con= JDBCUtils.getConnection();
        String str1 = "update class set CLcode = '"+classes.getCLcode()+"', CLname = '"+classes.getCLname()+"', Scode = '"+classes.getScode()+"' where CLcode = '"+CLcode+"' and Scode in (select Scode from speciality) and '"+classes.getCLcode()+"' like '[A-Z][A-Z][0-9][0-9][0-9][0-9]'";
        String str2="update student set CLcode = '"+classes.getCLcode()+"' where CLcode = '"+CLcode+"'";
        String str3="update edu set CLcode = '"+classes.getCLcode()+"' where CLcode = '"+CLcode+"'";
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        try {
            Statement statement = con.createStatement();
            result1 = statement.executeUpdate(str1);
            result2 = statement.executeUpdate(str2);
            result3 = statement.executeUpdate(str3);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result1;
    }

    @Override
    public int deleteClassByCLcode(String CLcode) {
        Connection con= JDBCUtils.getConnection();
        String str1 = "delete from class where CLcode = '"+CLcode+"'";
        String str2 = "delete from edu where CLcode = '"+CLcode+"'";
        String str3 = "delete from student where CLcode = '"+CLcode+"'";
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        try {
            Statement statement = con.createStatement();
            result1 = statement.executeUpdate(str1);
            result2 = statement.executeUpdate(str2);
            result3 = statement.executeUpdate(str3);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result1;
    }

    @Override
    public List<speciality> findAllSpec() {
        List<speciality> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str = "select * from speciality ";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                speciality tmp = new speciality();
                tmp.setScode(res.getString("Scode"));
                tmp.setSname(res.getString("Sname"));
                tmp.setCocode(res.getString("Cocode"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public int updateSpec(speciality speciality, String Scode) {
        Connection con= JDBCUtils.getConnection();
        String str1 = "update speciality set Scode = '"+speciality.getScode()+"', Sname = '"+speciality.getSname()+"', Cocode = '"+speciality.getCocode()+"' where Scode = '"+Scode+"' and Cocode in (select Cocode from college) and '"+speciality.getScode()+"' like '[A-Z][A-Z][0-9][0-9][0-9][0-9]'";
        String str3 = "update student set Scode = '"+speciality.getScode()+"' where Scode = '"+Scode+"'";
        int result1 = 0;
        int result3 = 0;
        try {
            Statement statement = con.createStatement();
            result1 = statement.executeUpdate(str1);
            result3 = statement.executeUpdate(str3);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result1;
    }

    @Override
    public int deleteSpecByScode(String Scode) {
        Connection con= JDBCUtils.getConnection();
        String str1 = "delete from speciality where Scode = '"+Scode+"'";
        String str3 = "delete from student where Scode = '"+Scode+"'";
        String str4 = "delete from class where Scode = '"+Scode+"'";
        int result1 = 0;
        int result3 = 0;
        int result4 = 0;
        try {
            Statement statement = con.createStatement();
            result1 = statement.executeUpdate(str1);
            result3 = statement.executeUpdate(str3);
            result4 = statement.executeUpdate(str4);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result1;
    }

    @Override
    public List<course> findAllCourse() {
        List<course> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select * from course ";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                course tmp = new course();
                tmp.setCno(res.getString("Cno"));
                tmp.setScode(null);
                tmp.setCname(res.getString("Cname"));
                tmp.setSpname(null);
                tmp.setCproperty(res.getString("Cproperty"));
                tmp.setCredit(res.getInt("Credit"));
                tmp.setTerm(0);
                tmp.setSyear(0);
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public int updateCourseByCnoAndScode(course course,String Cno,String Scode) {
        Connection con= JDBCUtils.getConnection();

        String str1 = "update course set Cno = '"+course.getCno()+"', Cname = '"+course.getCname()+"', Cproperty = '"+course.getCproperty()+"'," +
                " Credit = "+course.getCredit()+" where Cno = '"+Cno+"' and '"+course.getCno()+"' like '[A-Z][A-Z][0-9][0-9][0-9][0-9]' ";
        String str2 = "update edu set Cno = '"+course.getCno()+"' where Cno = '"+Cno+"'";
        String str3 = "update grade set Cno = '"+course.getCno()+"' where Cno = '"+Cno+"'";
        String str4 = "update time set Cno = '"+course.getCno()+"', Scode = '"+course.getScode()+"' where Cno = '"+Cno+"' and Scode  = '"+Scode+"' and '"+course.getScode()+"' in (select Scode from speciality)";
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        int result4 = 0;
        try {
            Statement statement = con.createStatement();
            result1 = statement.executeUpdate(str1);
            result2 = statement.executeUpdate(str2);
            result3 = statement.executeUpdate(str3);
            result4 = statement.executeUpdate(str4);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result1;

    }

    @Override
    public int deleteCourseByCnoAndScode(String Cno,String Scode) {
        Connection con= JDBCUtils.getConnection();
        String str1 = "delete from course where Cno = '"+Cno+"'";
        String str2 = "delete from edu where Cno = '"+Cno+"'";
        String str3 = "delete from grade where Cno = '"+Cno+"'";
        String str4 = "delete from time where Cno = '"+Cno+"' and Scode = '"+Scode+"'";
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        int result4 = 0;
        try {
            Statement statement = con.createStatement();
            result1 = statement.executeUpdate(str1);
            result2 = statement.executeUpdate(str2);
            result3 = statement.executeUpdate(str3);
            result4 = statement.executeUpdate(str4);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result1;
    }

    @Override
    public List<college> findAllCollege() {
        List<college> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str = "select * from college ";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                college tmp = new college();
                tmp.setCocode(res.getString("Cocode"));
                tmp.setConame(res.getString("Coname"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public int updateCollegeByCocode(college college, String Cocode) {
        Connection con= JDBCUtils.getConnection();
        String str = "update college set Cocode = '"+college.getCocode()+"', Coname = '"+college.getConame()+"' where Cocode = '"+Cocode+"' and '"+college.getCocode()+"' like '[A-Z][0-9][0-9][0-9][0-9][A-Z]'";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteCollegeByCocode(String Cocode) {//待完善
        Connection con= JDBCUtils.getConnection();
        List<String> CocodeList = new ArrayList<>();
        String str1 = "delete from college where Cocode = '"+Cocode+"'";
        //可以先将学院对应专业代号查出来，再调用函数处理关联表的数据
        String str2 = "select Scode from speciality where Cocode = '"+Cocode+"'";
        int result = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str2);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                CocodeList.add(res.getString("Scode"));
            }
            preparedStatement.close();
            res.close();
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str1);
            for (int i=0;i<CocodeList.size();i++){
                deleteSpecByScode(CocodeList.get(i));//删掉相关数据
            }
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public int addUsr(usr usr) {
        Connection con= JDBCUtils.getConnection();
        String str1 = "select Id from usr where Id = '"+usr.getId()+"'";//需要先判断是否已经存在账号
        String str2 = "insert into usr values('"+usr.getEmail()+"', '"+usr.getPasswd()+"', "+usr.getUrole()+", '"+usr.getId()+"')";
        String Id = null;//用来判断是否存在想要添加的Id
        int result = 0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str1);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                Id=res.getString("Id");
            }
            preparedStatement.close();
            res.close();
            if(Id == null){
                Statement statement = con.createStatement();
                result = statement.executeUpdate(str2);
                JDBCUtils.close(con,statement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int updateUsr(usr usr) {
        Connection con= JDBCUtils.getConnection();
        String str = "update usr set Email='"+usr.getEmail()+"',Passwd='"+usr.getPasswd()+"' where Id = '"+usr.getId()+"'";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteUsr(String Id) {
        Connection con= JDBCUtils.getConnection();
        String str = "delete from usr where Id = '"+Id+"'";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<usr> findAllUsr() {
        List<usr> data = new ArrayList<>();
        Connection con=JDBCUtils.getConnection();
        String str="select * from usr ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                usr tmp = new usr();
                tmp.setEmail(res.getString("Email"));
                tmp.setPasswd(res.getString("Passwd"));
                tmp.setId(res.getString("Id"));
                tmp.setUrole(res.getInt("Urole"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public List<speciality> findSpecialityByCocode(String Cocode) {
        List<speciality> data = new ArrayList<>();
        Connection con=JDBCUtils.getConnection();
        String str="select * from speciality where Cocode = '"+Cocode+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                speciality tmp = new speciality();
                tmp.setScode(res.getString("Scode"));
                tmp.setSname(res.getString("Sname"));
                tmp.setCocode(res.getString("Cocode"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public List<classes> findClassByScode(String Scode) {
        List<classes> data = new ArrayList<>();
        Connection con=JDBCUtils.getConnection();
        String str="select * from class where Scode = '"+Scode+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                classes tmp = new classes();
                tmp.setCLcode(res.getString("CLcode"));
                tmp.setCLname(res.getString("CLname"));
                tmp.setScode(res.getString("Scode"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public List<student> findStudentByCLcode(String CLcode) {
        List<student> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str = "select * from studentview where CLcode = '"+CLcode+"'";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                student tmp = new student();
                tmp.setSno(res.getString("Sno"));
                tmp.setSname(res.getString("Sname"));
                tmp.setSsex(res.getString("Ssex"));
                tmp.setCLcode(res.getString("CLcode"));
                tmp.setCLname(res.getString("CLname"));
                tmp.setScode(res.getString("Scode"));
                tmp.setSpname(res.getString("Spname"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public List<edu> findEduByCno(String Cno) {
        List<edu> data = new ArrayList<>();
        Connection con=JDBCUtils.getConnection();
        String str="select * from eduview where Cno = '"+Cno+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                edu tmp = new edu();
                tmp.setTno(res.getString("Tno"));
                tmp.setTname(res.getString("Tname"));
                tmp.setCno(res.getString("Cno"));
                tmp.setCname(res.getString("Cname"));
                tmp.setCLcode(res.getString("CLcode"));
                tmp.setCLname(res.getString("CLname"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public int updateEdu(edu edu,edu previousedu) {
        Connection con= JDBCUtils.getConnection();
        String str = "update edu set Tno='"+edu.getTno()+"', Cno = '"+edu.getCno()+"', CLcode = '"+edu.getCLcode()+"' " +
                "where '"+previousedu.getTno()+"' in (select Tno from teacher) and '"+previousedu.getCno()+"' in (select Cno from course) " +
                "and '"+previousedu.getCLcode()+"' in (select CLcode from class) " +
                "and Tno='"+previousedu.getTno()+"' and Cno = '"+previousedu.getCno()+"' and CLcode = '"+previousedu.getCLcode()+"'";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    @Override
    public int deleteEdu(edu edu) {
        Connection con= JDBCUtils.getConnection();
        System.out.println(edu);
        String str = "delete from edu where Tno = '"+edu.getTno()+"' and Cno = '"+edu.getCno()+"' and CLcode = '"+edu.getCLcode()+"'";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public student findStudentBySno(String Sno) {
        student data  = new student();
        Connection con = JDBCUtils.getConnection();
        String str="select * from studentview where Sno = '"+Sno+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setSno(res.getString("Sno"));
                data.setSname(res.getString("Sname"));
                data.setSsex(res.getString("Ssex"));
                data.setCLcode(res.getString("CLcode"));
                data.setCLname(res.getString("CLname"));
                data.setScode(res.getString("Scode"));
                data.setSpname(res.getString("Spname"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public usr findUsrById(String Id) {
        usr data  = new usr();
        Connection con = JDBCUtils.getConnection();
        String str="select * from usr where Id = '"+Id+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setEmail(res.getString("Email"));
                data.setPasswd(res.getString("Passwd"));
                data.setUrole(res.getInt("Urole"));
                data.setId(res.getString("Id"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public classes findClassByCLcode(String CLcode) {
        classes data  = new classes();
        Connection con = JDBCUtils.getConnection();
        String str="select * from class where CLcode = '"+CLcode+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setCLcode(res.getString("CLcode"));
                data.setCLname(res.getString("CLname"));
                data.setScode(res.getString("Scode"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public speciality findSingleSpecialityByScode(String Scode) {
        speciality data  = new speciality();
        Connection con = JDBCUtils.getConnection();
        String str="select * from speciality where Scode = '"+Scode+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setScode(res.getString("Scode"));
                data.setSname(res.getString("Sname"));
                data.setCocode(res.getString("Cocode"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public course findCourseByCnoAndScode(String Cno, String Scode) {
        course data  = new course();
        Connection con = JDBCUtils.getConnection();
        String str="select * from courseview where Scode = '"+Scode+"' and Cno = '"+Cno+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setCno(res.getString("Cno"));
                data.setScode(res.getString("Scode"));
                data.setCname(res.getString("Cname"));
                data.setSpname(res.getString("Spname"));
                data.setCproperty(res.getString("Cproperty"));
                data.setCredit(res.getInt("Credit"));
                data.setTerm(res.getInt("Term"));
                data.setSyear(res.getInt("Syear"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    @Override
    public List<course> findCourseByCno(String Cno){
        List<course> data  = new ArrayList<>();
        course tmp = new course();
        Connection con = JDBCUtils.getConnection();
        String str="select * from courseview where  Cno = '"+Cno+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                tmp.setCno(res.getString("Cno"));
                tmp.setScode(res.getString("Scode"));
                tmp.setCname(res.getString("Cname"));
                tmp.setSpname(res.getString("Spname"));
                tmp.setCproperty(res.getString("Cproperty"));
                tmp.setCredit(res.getInt("Credit"));
                tmp.setTerm(res.getInt("Term"));
                tmp.setSyear(res.getInt("Syear"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    @Override
    public college findCollegeByCocode(String Cocode) {
        college data  = new college();
        Connection con = JDBCUtils.getConnection();
        String str="select * from college where Cocode = '"+Cocode+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setCocode(res.getString("Cocode"));
                data.setConame(res.getString("Coname"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public List<edu> findEduByCnoTno(String Cno, String Tno) {
        List<edu> data = new ArrayList<>();
        Connection con=JDBCUtils.getConnection();
        String str="select * from eduview where Cno = '"+Cno+"' and Tno = '"+Tno+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                edu tmp = new edu();
                tmp.setTno(res.getString("Tno"));
                tmp.setTname(res.getString("Tname"));
                tmp.setCno(res.getString("Cno"));
                tmp.setCname(res.getString("Cname"));
                tmp.setCLcode(res.getString("CLcode"));
                tmp.setCLname(res.getString("CLname"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public edu findSingleEdu(String Cno, String Tno, String CLcode) {
        edu data = new edu();
        Connection con=JDBCUtils.getConnection();
        String str="select * from eduview where Cno = '"+Cno+"' and Tno = '"+Tno+"' and CLcode = '"+CLcode+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setTno(res.getString("Tno"));
                data.setTname(res.getString("Tname"));
                data.setCno(res.getString("Cno"));
                data.setCname(res.getString("Cname"));
                data.setCLcode(res.getString("CLcode"));
                data.setCLname(res.getString("CLname"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public String findScodeBySname(String Sname) {
        String data = null;
        Connection con =JDBCUtils.getConnection();
        String str="select Scode from speciality where Sname = '"+Sname+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data = res.getString("Scode");
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public String findCLcodeByCLname(String CLname) {
        String data = null;
        Connection con =JDBCUtils.getConnection();
        String str="select CLcode from class where CLname = '"+CLname+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data = res.getString("CLcode");
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public String findCocodeByConame(String Coname) {
        String data = null;
        Connection con =JDBCUtils.getConnection();
        String str="select Cocode from college where Coname = '"+Coname+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data = res.getString("Cocode");
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    public String findTnameByTno(String Tno){
        String data = null;
        Connection con =JDBCUtils.getConnection();
        String str="select Tname from teacher where Tno = '"+Tno+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data = res.getString("Tname");
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public int addStudent(student student) {
        Connection con= JDBCUtils.getConnection();
        String str = "insert into student values('"+student.getSno()+"','"+student.getSname()+"','"+student.getSsex()+"','"+student.getCLcode()+"','"+student.getScode()+"')";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int addTeacher(teacher teacher) {
        Connection con= JDBCUtils.getConnection();
        String str;
        if(teacher.getCocode()==null){
            str="insert into teacher values('"+teacher.getTno()+"','"+teacher.getTname()+"','"+teacher.getTsex()+"', NULL)";
        }else {
            str = "insert into teacher values('"+teacher.getTno()+"','"+teacher.getTname()+"','"+teacher.getTsex()+"', '"+teacher.getCocode()+"')";
        }
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int addCourse(course course) {
        Connection con= JDBCUtils.getConnection();
        String str1 = "insert into course values('"+course.getCno()+"','"+course.getCname()+"','"+course.getCproperty()+"','"+course.getCredit()+"')";
        String str2 ="insert into time values ('"+course.getCno()+"','"+course.getScode()+"','"+course.getTerm()+"','"+course.getSyear()+"')";
        int result1 = 0;
        int result2 = 0;
        try {
            Statement statement = con.createStatement();
            result1 = statement.executeUpdate(str1);
            result2 = statement.executeUpdate(str2);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result1;
    }

    @Override
    public int addClass(classes classes) {
        Connection con= JDBCUtils.getConnection();
        String str = "insert into class values('"+classes.getCLcode()+"','"+classes.getCLname()+"','"+classes.getScode()+"')";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int addSpeciality(speciality speciality) {
        Connection con= JDBCUtils.getConnection();
        String str = "insert into speciality values('"+speciality.getScode()+"','"+speciality.getSname()+"','"+speciality.getCocode()+"')";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int addCollege(college college) {
        Connection con= JDBCUtils.getConnection();
        String str = "insert into college values('"+college.getCocode()+"','"+college.getConame()+"')";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int addEdu(edu edu) {
        Connection con= JDBCUtils.getConnection();
        String str = "insert into edu values('"+edu.getTno()+"','"+edu.getCno()+"','"+edu.getCLcode()+"')";
        int result = 0;
        try {
            Statement statement = con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<edu> findAllEdu() {
        List<edu> data = new ArrayList<>();
        Connection con=JDBCUtils.getConnection();
        String str="select * from eduview ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                edu tmp = new edu();
                tmp.setTno(res.getString("Tno"));
                tmp.setTname(res.getString("Tname"));
                tmp.setCno(res.getString("Cno"));
                tmp.setCname(res.getString("Cname"));
                tmp.setCLcode(res.getString("CLcode"));
                tmp.setCLname(res.getString("CLname"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

}
