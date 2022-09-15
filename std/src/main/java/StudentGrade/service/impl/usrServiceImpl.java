package StudentGrade.service.impl;

import StudentGrade.dao.impl.usrDaoImpl;
import StudentGrade.dao.usrDao;
import StudentGrade.pojo.usr;
import StudentGrade.service.usrService;
import StudentGrade.tool.Encrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

public class usrServiceImpl implements usrService {
    private usrDao usrDao = new usrDaoImpl();
    private Encrypt encrypt=new Encrypt();
    public usr login(String Id, String Passwd) {
        usr usr=usrDao.login(Id,encrypt.md5(Passwd));
        if (usr.getPasswd()==null){
            return null;
        }
        return usr;
       /*usr usr=new usr(null,"20220019",3,"20220019");
        return usr;*/
    }
    public int register(usr usr) {
        if(usr.getUrole()==1){
            if(new adminServiceImpl().findStudentBySno(usr.getId()).getSno()!=null) {
                usr.setPasswd(encrypt.md5(usr.getPasswd()));
                return usrDao.register(usr);
            }
            else return 0;
        }else {
            if(new adminServiceImpl().findTnameByTno(usr.getId())!=null){
                usr.setPasswd(encrypt.md5(usr.getPasswd()));
                return usrDao.register(usr);
            }
            else return 0;
        }

    }
    public boolean exists(String Id){
        usr user=usrDao.findUserById(Id);
        if(user.getId()!=null){
            return true;
        }
        return false;
    } //验证账户是否存在

}
