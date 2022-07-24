package StudentGrade.service;

import StudentGrade.pojo.usr;

import java.util.List;

public interface usrService {
    public abstract usr login(String Id, String Passwd);
    public abstract int register(usr usr);
    public abstract boolean exists(String Id); //验证账户是否存在
//    public abstract String md5(String data);
}
