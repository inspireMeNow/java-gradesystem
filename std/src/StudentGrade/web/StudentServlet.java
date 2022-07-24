package StudentGrade.web;

import StudentGrade.pojo.*;
import StudentGrade.service.adminService;
import StudentGrade.service.impl.adminServiceImpl;
import StudentGrade.service.impl.studentServiceImpl;
import StudentGrade.service.studentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

public class StudentServlet extends BaseServlet{

    studentService studentService= new studentServiceImpl();
    adminService adservice=new adminServiceImpl();
    public void findBySno(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        student student=studentService.findBySno(req.getParameter("Sno"));
        String Coname=studentService.findConameByScode(student.getScode());
        req.setAttribute("student",student);
        req.setAttribute("Coname",Coname);
        req.getSession().setAttribute("student",student);
//        out.println(student.toString());
        req.getRequestDispatcher("/student/main1.jsp").forward(req,resp);
    }

    //查询所有的成绩
    public void findAllGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<grade> grade=studentService.findAllGrade(req.getParameter("Sno"));
        req.setAttribute("grade",grade);
        float average=studentService.averageGrade(req.getParameter("Sno"));
        req.setAttribute("average",String.valueOf(average));
        req.getRequestDispatcher("/student/main2.jsp").forward(req,resp);
    }

    //课程名查询成绩
    public void findGradeByCname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<grade> grade=studentService.findGradeByCname(req.getParameter("Sno"),req.getParameter("Cname"));
        req.setAttribute("grade",grade);
        req.getRequestDispatcher("/student/main2.jsp").forward(req,resp);
    }

    //课程性质查询
    public void findGradeByCpro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<grade> grade=studentService.findGradeByCpro(req.getParameter("Sno"),req.getParameter("Cproperty"));
        req.setAttribute("grade",grade);
        req.getRequestDispatcher("/student/main2.jsp").forward(req,resp);
    }

    //查询选课信息
    public void findCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<course> course=studentService.findCourse(req.getParameter("Sno"));
        int sum=studentService.addCredit(req.getParameter("Sno"));
        req.setAttribute("course",course);
        req.setAttribute("sum",sum);
        req.getRequestDispatcher("/student/main3.jsp").forward(req,resp);
    }

    // 修改账户信息
    protected void findUsrById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usr user=adservice.findUsrById(req.getParameter("Id"));
        req.setAttribute("user",user);
//        out.println(user);
        req.getRequestDispatcher("/student/main4_update.jsp").forward(req,resp);
    }

    protected void updateUsr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Email = req.getParameter("email");
        String Passwd = req.getParameter("passwd");
        int Urole=Integer.parseInt(req.getParameter("urole"));
        String Id=req.getParameter("id");
        usr user=new usr(Email,Passwd,Urole,Id);
//        req.setAttribute("user",user);
//        out.println(usr);
//        out.println(flag);
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("gb2312");
        if(req.getParameter("passwd").equals(req.getParameter("passwd_1"))){
            int flag=studentService.updateUsr(user);
            if(flag!=0){
                resp.getWriter().println("<script>alert('修改成功')</script>");
                resp.getWriter().println("<script>window.location.href='StudentServlet?action=findBySno&Sno="+Id+"'</script>");
            }
            else{
                resp.getWriter().println("<script>alert('修改失败')</script>");
                resp.getWriter().println("<script>window.location.href='StudentServlet?action=findUsrById&Id="+Id+"'</script>");
            }

        }
        else{
            resp.getWriter().println("<script>alert('两次输入密码不一致，请重新修改')</script>");
            resp.getWriter().println("<script>window.location.href='StudentServlet?action=findUsrById&Id="+Id+"'</script>");
        }

    }
}
