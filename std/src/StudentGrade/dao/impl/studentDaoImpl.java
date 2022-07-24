package StudentGrade.dao.impl;

import StudentGrade.dao.studentDao;
import StudentGrade.pojo.*;
import StudentGrade.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class studentDaoImpl implements studentDao {

    @Override
    public student findBySno(String Sno) {
        student data = new student();
        Connection con= JDBCUtils.getConnection();
        String str = "select * from studentview where Sno = '"+Sno+"'";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
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
    public List<grade> findAllGrade(String Sno) {
        List<grade> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select * from gradeview where Sno = '"+Sno+"'";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                grade tmp = new grade();
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
    public List<grade> findGradeByCname(String Sno, String Cname) {
        List<grade> data=new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select * from gradeview where Sno='"+Sno+"' and Cname like '"+Cname+"'";
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
    public List<grade> findGradeByCpro(String Sno, String Cproperty) {
        List<grade> data=new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select * from gradeview where Sno='"+Sno+"' and Cproperty like '"+Cproperty+"'";
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
    public List<course> findCourse(String Sno) {
        List<course> data = new ArrayList<>();
        Connection con= JDBCUtils.getConnection();
        String str="select * from courseview where Cno in (select Cno from grade where Sno='"+Sno+"' and (Remark is not null or grade is not null) " +
                " and Scode = (select Scode from student where Sno = '"+Sno+"'))";
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
    public  String findConameByScode(String Scode){
        Connection con= JDBCUtils.getConnection();
        String Coname=null;
        String sql="SELECT Coname FROM college WHERE Cocode=(SELECT Cocode FROM speciality WHERE Scode='"+Scode+"')";
        try {
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
               Coname= res.getString("Coname");
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Coname;
    }
}