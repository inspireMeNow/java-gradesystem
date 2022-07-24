package StudentGrade.dao.impl;

import StudentGrade.dao.teacherDao;
import StudentGrade.pojo.*;
import StudentGrade.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class teacherDaoImpl implements teacherDao {

    @Override
    public int addOrUpdateGrade(String Sno, String Cno, int Grade,String Remark) {
        Connection con = JDBCUtils.getConnection();
        String str;
        if(Remark==null){
            str="update grade set Grade = "+Grade+" , Remark = NULL where Sno = '"+Sno+"' and Cno = '"+Cno+"'";
        }else{
            str="update grade set Grade = "+Grade+" , Remark = '"+Remark+"' where Sno = '"+Sno+"' and Cno = '"+Cno+"'";
        }
        int result = 0;
        try {
            Statement statement=con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteGrade(String Sno, String Cno) {
        Connection con = JDBCUtils.getConnection();
        String str="update grade set grade = null where Sno = '"+Sno+"' and Cno = '"+Cno+"'";
        int result = 0;
        try {
            Statement statement=con.createStatement();
            result = statement.executeUpdate(str);
            JDBCUtils.close(con,statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<course> findCourse(String Tno) {
        List<course> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select  * from courseview where Cno in (select distinct Cno from edu where Tno = '"+Tno+"')";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                course tmp = new course();
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
    public List<classes> findClassByCno(String Tno, String Cno) {
        List<classes> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select * from class where CLcode in (select CLcode from edu where Tno='"+Tno+"' and Cno = '"+Cno+"')";
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
    public List<student> findStudentByCLcode(String CLcode) {
        List<student> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select * from studentview where CLcode = '"+CLcode+"'";
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
    public List<grade> findGradeBySnoAndCLcode(String Sno, String Cno) {
        List<grade> data=new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select * from gradeview where Sno= '"+Sno+"' and Cno = '"+Cno+"'";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                grade tmp=new grade();
                tmp.setSno(res.getString("Sno"));
                tmp.setCno(res.getString("Cno"));
                tmp.setSname(res.getString("Sname"));
                tmp.setCname(res.getString("Cname"));
                tmp.setCproperty(res.getString("Cproperty"));
                tmp.setTerm(res.getInt("Term"));
                tmp.setSyear(res.getInt("Syear"));
                tmp.setCredit(res.getInt("Credit"));
                tmp.setGrade(res.getInt("Grade"));
                tmp.setRemark(res.getString("Remark"));
                data.add(tmp);
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
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
    //根据工号查教师
    public  teacher findByTno(String Tno){
        teacher data = new teacher();
        Connection con= JDBCUtils.getConnection();
        String str = "select * from teacher where Tno = '"+Tno+"'";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setTno(res.getString("Tno"));
                data.setTname(res.getString("Tname"));
                data.setTsex(res.getString("Tsex"));
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
    public List<edu> findEduByTno(String Tno) {
        List<edu> data = new ArrayList<>();
        Connection con=JDBCUtils.getConnection();
        String str="select * from eduview where  Tno = '"+Tno+"'";
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
