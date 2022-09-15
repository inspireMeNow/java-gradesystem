package StudentGrade.web;

import StudentGrade.pojo.*;
import StudentGrade.service.adminService;
import StudentGrade.service.impl.adminServiceImpl;
import StudentGrade.service.impl.teacherServiceImpl;
import StudentGrade.service.teacherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class TeacherServlet extends BaseServlet{
    private teacherService teacherService= new teacherServiceImpl();
    private adminService adservice=new adminServiceImpl();
    //成绩管理模块
    public void findGradeByCnoCLcode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<List<grade>>grades=teacherService.findGradeByCnoCLcode(req.getParameter("Cno"),req.getParameter("Clcode"));
        teacher teacher=teacherService.findByTno(req.getParameter("Tno"));
        classes cla =adservice.findClassByCLcode(req.getParameter("Clcode"));
        req.setAttribute("teacher",teacher);
        req.setAttribute("cla",cla);
        req.setAttribute("grades",grades);
        req.setAttribute("Cno_1",req.getParameter("Cno"));
//        System.out.println(grades);
//        System.out.println(teacher);
//        System.out.println(cla);
        req.getRequestDispatcher("/teacher/students.jsp").forward(req,resp);
    }
    public void addOrUpdateGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int flag=0;
        byte[]bytes=req.getParameter("Remark").getBytes("iso8859-1");
        String str=new String(bytes,"UTF-8");
        if(req.getParameter("grade")==""){
            deleteGrade(req,resp);
        }
        else {
            flag = teacherService.addOrUpdateGrade(req.getParameter("Sno"), req.getParameter("Cno"), Integer.parseInt(req.getParameter("grade")), str);

            if (flag != 0) { //修改成功
                resp.getWriter().println("<script>alert('修改成功')</script>");
                resp.getWriter().println("<script>window.location.href='TeacherServlet?action=findGradeByCnoCLcode&Cno=" + req.getParameter("Cno") + "&Tno=" + req.getParameter("Tno") + "&Clcode=" + req.getParameter("CLcode") + "'</script>");
            } else {
                resp.getWriter().println("<script>alert('修改失败')</script>");
                resp.getWriter().println("<script>window.location.href='TeacherServlet?action=findGradeByCnoCLcode&Cno=" + req.getParameter("Cno") + "&Tno=" + req.getParameter("Tno") + "&Clcode=" + req.getParameter("CLcode") + "'</script>");
            }
        }
    }

    public void deleteGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int flag=teacherService.deleteGrade(req.getParameter("Sno"),req.getParameter("Cno"));
        if(flag!=0){
            resp.getWriter().println("<script>alert('删除成功')</script>");
            resp.getWriter().println("<script>window.location.href='TeacherServlet?action=findGradeByCnoCLcode&Cno="+req.getParameter("Cno")+"&Tno="+req.getParameter("Tno")+"&Clcode="+req.getParameter("CLcode")+"'</script>");
        }
        else{
            resp.getWriter().println("<script>alert('删除失败')</script>");
            resp.getWriter().println("<script>window.location.href='TeacherServlet?action=findGradeByCnoCLcode&Cno="+req.getParameter("Cno")+"&Tno="+req.getParameter("Tno")+"&Clcode="+req.getParameter("CLcode")+"'</script>");
        }
    }
    //查看课程模块与成绩管理公用模块
    public void findEdu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<edu> edus=teacherService.findEduByTno(req.getParameter("Tno"));
        teacher teacher=teacherService.findByTno(req.getParameter("Tno"));
        req.setAttribute("teacher",teacher);
        if (req.getParameter("nature").equals("1")){
            req.setAttribute("title","1");
        }
        else {
            req.setAttribute("title","2");
        }
        req.setAttribute("edus",edus);
        req.getRequestDispatcher("/teacher/main1.jsp").forward(req,resp);
    }
    // 修改账户信息
    protected void findUsrById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usr user=adservice.findUsrById(req.getParameter("Id"));
        req.setAttribute("user",user);
//        out.println(user);
        req.getRequestDispatcher("/teacher/main4_update.jsp").forward(req,resp);
    }
    public void updateUsr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Email = req.getParameter("email");
        String Passwd = req.getParameter("passwd");
        int Urole=Integer.parseInt(req.getParameter("urole"));
        String Id=req.getParameter("id");
        usr usr=new usr(Email,Passwd,Urole,Id);
//        out.println(flag);
        if(req.getParameter("passwd").equals(req.getParameter("passwd_1"))){
            int flag=teacherService.updateUsr(usr);
            if(flag!=0){
                resp.getWriter().println("<script>alert('修改成功')</script>");
                resp.getWriter().println("<script>window.location.href='TeacherServlet?action=findEdu&Tno="+Id+"&nature=1'</script>");
            }
            else{
                resp.getWriter().println("<script>alert('修改失败')</script>");
                resp.getWriter().println("<script>window.location.href='TeacherServlet?action=findUsrById&Id="+Id+"&nature=1'</script>");
            }
        }
        else{
            resp.getWriter().println("<script>alert('两次输入密码不一致，请重新修改')</script>");
            resp.getWriter().println("<script>window.location.href='TeacherServlet?action=findUsrById&Id="+Id+"&nature=1'</script>");
        }
    }
}
