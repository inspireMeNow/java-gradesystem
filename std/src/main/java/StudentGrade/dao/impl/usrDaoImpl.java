package StudentGrade.dao.impl;

import StudentGrade.dao.usrDao;
import StudentGrade.pojo.*;
import StudentGrade.utils.JDBCUtils;
import org.junit.Test;

import java.sql.*;

public class usrDaoImpl implements usrDao {

    @Override
    public usr login(String Id, String Passwd) {
        usr data = new usr();
        Connection con=JDBCUtils.getConnection();
        String str="select * from usr where Id = '"+Id+"' and Passwd = '"+Passwd+"'";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setEmail(res.getString("Email"));
                data.setPasswd(res.getString("Passwd"));
                data.setId(res.getString("Id"));
                data.setUrole(res.getInt("Urole"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public int register(usr usr) {
        Connection con= JDBCUtils.getConnection();
        String str = "insert into usr values('"+usr.getEmail()+"','"+usr.getPasswd()+"',"+usr.getUrole()+",'"+usr.getId()+"')";
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
    public  usr findUserById(String Id){
        usr data=new usr();
        Connection con=JDBCUtils.getConnection();
        String str="select * from usr where Id="+Id;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(str);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                data.setEmail(res.getString("Email"));
                data.setPasswd(res.getString("Passwd"));
                data.setId(res.getString("Id"));
                data.setUrole(res.getInt("Urole"));
            }
            JDBCUtils.close(con,preparedStatement,res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
