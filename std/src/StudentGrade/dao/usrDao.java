package StudentGrade.dao;

import StudentGrade.pojo.*;

import java.util.List;

public interface usrDao {
    //登录验证
    public abstract usr login(String Id,String Passwd);
    //注册账户
    public abstract int register(usr usr);
    //寻找用户
    public abstract usr findUserById(String Id);
}
