package StudentGrade.web;


import StudentGrade.pojo.student;
import StudentGrade.pojo.teacher;
import StudentGrade.pojo.usr;
import StudentGrade.service.impl.teacherServiceImpl;
import StudentGrade.service.impl.usrServiceImpl;
import StudentGrade.service.teacherService;
import StudentGrade.service.usrService;
import StudentGrade.utils.WebUtils;
import StudentGrade.tool.Encrypt;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private usrService userService = new usrServiceImpl();
    private teacherService teaservice = new teacherServiceImpl();
    private String confirmId;
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("ranstring");
        if (token != null && token.equalsIgnoreCase(code)) {
            //2.调用uerService.login()登录处理业务
            //如果等于null,说明登录失败
            usr user = userService.login(username, password);
//        System.out.println(user);
//        usr user=new usr("1234","1234",1,"123214");
            if (user == null) {
                //把错误信息和回显的表单项信息保存到Request域中
                req.setAttribute("msg", "用户名或密码错误");
                req.setAttribute("username", username);
                //跳回登录页面
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                //登录成功
                //保存用户登录的信息
                //跳到成功页面Login_success.html
                if (user.getUrole() == 1) {
                    student stu = new student();
                    req.getSession().setAttribute("stu", stu);
                    resp.sendRedirect(req.getContextPath() + "/StudentServlet?action=findBySno&Sno=" + user.getId());
                } else if (user.getUrole() == 2) {
                    teacher tea = teaservice.findByTno(user.getId());
                    req.getSession().setAttribute("tea", tea);
                    resp.sendRedirect(req.getContextPath() + "/TeacherServlet?action=findEdu&nature=1&Tno=" + user.getId());
                } else {
                    req.getRequestDispatcher("/adminServlet?action=findAllCollege&nature=1").forward(req, resp);
                }
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            //跳回登录页面
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
    protected void sendEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String universityId=req.getParameter("universityId");
        String Urole=req.getParameter("Urole");
        System.out.println(Urole);
        Encrypt encrypt = new Encrypt();
        String decryptStr = encrypt.xor("\u0383\u038DΑυϜϟϑϛϞϙϙϝϙ\u038BϘϐΌϝϝΌ\u038DΊϜΎΎϞΎΉ\u038DΎϘϟϝϘϑϛ",1000);
        String generateId = encrypt.generateId();
        confirmId=generateId;
        String post = new String("欢迎使用STD成绩管理系统，您本次注册的邮箱验证码为："+generateId+"，请在15分钟内填写，过期将会失效！");
        String subject = new String("邮箱验证码");
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(decryptStr)
                .createApi(MailgunMessagesApi.class);

        Message message = Message.builder()
                .from("STD成绩管理系统 <postmaster@duan-dky.me>")
                .to(email)
                .subject(subject)
                .text(post)
                .build();
        MessageResponse messageResponse= mailgunMessagesApi.sendMessage("duan-dky.me", message);
        req.setAttribute("username",universityId);
        req.setAttribute("email", email);
        req.setAttribute("Urole",Urole);
        if(messageResponse == null){
            req.setAttribute("sendSuccess","-1");
        }
        else{
            req.setAttribute("sendSuccess","0");
        }
        req.getRequestDispatcher("/regist.jsp").forward(req, resp);
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("ranstring");
//        1.获取请求的参数
        String username = req.getParameter("universityId");
        String password = req.getParameter("newPassword");
        String email = req.getParameter("email");
        String confirmId1 = req.getParameter("confirmId");
        int urole = WebUtils.parseInt(req.getParameter("Urole"),1);
        //2.检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)&&confirmId1.equals(confirmId)) {
//                3.用户名是否可用
            if (userService.exists(username)) {
                req.setAttribute("ms", "用户名已存在！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                //不可用
                req.getRequestDispatcher("/regist.jsp").forward(req, resp);
            } else {
                //可用
                //调用Service保存到数据库
//                    userService.register(new usr(email,password,urole,username));
                usr user = new usr(email, password, urole, username);
//                System.out.println(user);
                int flag = userService.register(user);
                if (flag != 0) {
                    req.setAttribute("username", username);
                    resp.getWriter().println("<script>alert('注册成功')</script>");
                    resp.getWriter().println("<script>window.location.href='index.jsp'</script>");
//                        req.getRequestDispatcher("/index.jsp").forward(req, resp);
                } else {
                    req.setAttribute("ms", "信息填写有误，请重新输入！");
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);
                    //不正确，跳回注册界面
                    req.getRequestDispatcher("/regist.jsp").forward(req, resp);
                }
            }

        } else {
            req.setAttribute("ms", "图形验证码或邮箱验证码错误！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            //不正确，跳回注册界面
            req.getRequestDispatcher("/regist.jsp").forward(req, resp);
        }
    }

    protected void loginout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.摧毁session中用户登录的信息（或者销毁session）
        req.getSession().invalidate();
        //2.重新定向到首页（或者登录页面）
        resp.sendRedirect(req.getContextPath());
    }
}
