package StudentGrade.web;

import StudentGrade.pojo.*;
import StudentGrade.service.adminService;
import StudentGrade.service.impl.adminServiceImpl;
import StudentGrade.utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
public class AdminServlet extends BaseServlet{
    adminService adservice=new adminServiceImpl();
    //公用模块
    protected void findAllCollege(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<college> colleges=adservice.findAllCollege();
        req.setAttribute("colleges",colleges);

        if (req.getParameter("nature").equals("3")){
            req.setAttribute("title","3");
        }
        else if (req.getParameter("nature").equals("4")){
            req.setAttribute("title","4");
        }
        else if (req.getParameter("nature").equals("6")){
            req.setAttribute("title","6");
        }
        else{
            req.setAttribute("title","1");
        }
        req.getRequestDispatcher("/admin/main1.jsp").forward(req,resp);
    }
    protected void findSpecialityByCocode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<speciality> specialities=adservice.findSpecialityByCocode(req.getParameter("Cocode"));
        college col=adservice.findCollegeByCocode(req.getParameter("Cocode"));
        req.setAttribute("Coname_1",col.getConame());
        req.setAttribute("specialities",specialities);
        if (req.getParameter("nature").equals("1")){
            req.setAttribute("title","1");
        }
        else if (req.getParameter("nature").equals("3")){
            req.setAttribute("title","3");
        }
        else{
            req.setAttribute("title","4");
        }
        req.getRequestDispatcher("/admin/speciality_2.jsp").forward(req,resp);
    }
    protected void findClassByScode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<classes> classes=adservice.findClassByScode(req.getParameter("Scode"));
        speciality spe=adservice.findSingleSpecialityByScode(req.getParameter("Scode"));
        req.setAttribute("Cocode_1",spe.getCocode());
        req.setAttribute("Sname_1",spe.getSname());
        req.setAttribute("classes",classes);
        if (req.getParameter("nature").equals("1")){
            req.setAttribute("title","1");
        }
        else {
            req.setAttribute("title","3");
        }
        req.getRequestDispatcher("/admin/class_1.jsp").forward(req,resp);
    }
    //学生信息模块
    protected void findStudentByCLcode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<student> students=adservice.findStudentByCLcode(req.getParameter("CLcode"));
        classes cla=adservice.findClassByCLcode(req.getParameter("CLcode"));
        req.setAttribute("CLname_1",cla.getCLname());
        req.setAttribute("Scode_1",cla.getScode());
        req.setAttribute("students",students);
        req.getRequestDispatcher("/admin/students.jsp").forward(req,resp);
    }
    protected void findStuBySno(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        student stu=adservice.findStudentBySno(req.getParameter("Sno"));
        req.setAttribute("student",stu);
        req.getRequestDispatcher("/admin/student_update.jsp").forward(req,resp);
    }
    protected void updateStuBySno(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        student stu=new student(req.getParameter("Sno"),req.getParameter("Sname"),req.getParameter("Ssex"),req.getParameter("Clcode"),req.getParameter("CLname"),req.getParameter("Scode"),req.getParameter("Spname_new"));
//        System.out.println(stu);
        System.out.println(stu.toString());
        int flag=adservice.updateStuBySno(stu);
        System.out.println(stu.toString());
        if(flag!=0){
            resp.getWriter().println("<script>alert('修改成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('修改失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findStudentByCLcode&nature=1&CLcode="+req.getParameter("Clcode")+"'</script>");
        //resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findStudentByCLcode&nature=1&CLcode="+req.getParameter("Clcode"));
    }
    protected void addStuBySno(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        student stu=new student(req.getParameter("Sno_new"),req.getParameter("Sname"),req.getParameter("Ssex"),null,req.getParameter("CLname"),null,req.getParameter("Spname_new"));
        System.out.println(stu.toString());
        int flag =adservice.addStudent(stu);
        if(flag!=0){
            resp.getWriter().println("<script>alert('添加成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('添加失败')</script>");
        }
        adminService adminService= new adminServiceImpl();
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findStudentByCLcode&nature=1&CLcode="+adminService.findCLcodeByCLname(req.getParameter("CLname"))+"'</script>");
    }
    protected void deleteStuBySno(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int flag=adservice.deleteStuBySno(req.getParameter("Sno"));
        if(flag!=0){
            resp.getWriter().println("<script>alert('删除成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('删除失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findStudentByCLcode&nature=1&CLcode="+req.getParameter("Clcode")+"'</script>");
        //resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findStudentByCLcode&nature=1&CLcode="+req.getParameter("Clcode"));
    }
    //用户管理模块
    protected void findAllUsr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<usr> users=adservice.findAllUsr();
        req.setAttribute("users",users);
        req.getRequestDispatcher("/admin/main2.jsp").forward(req,resp);
    }
    protected void findUsrById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        usr user=adservice.findUsrById(req.getParameter("id"));
        req.setAttribute("user",user);
        req.getRequestDispatcher("/admin/main2_update.jsp").forward(req,resp);
    }
    protected void updateUsr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Urole=WebUtils.parseInt(req.getParameter("Urole"),1);
        usr user= new usr(req.getParameter("email"),req.getParameter("password"),Urole,req.getParameter("Id"));
        int flag=adservice.updateUsr(user);
        if(flag!=0){
            resp.getWriter().println("<script>alert('修改成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('修改失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findAllUsr'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findAllUsr");
    }
    protected void addUsr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Urole=WebUtils.parseInt(req.getParameter("Urole"),1);
        usr user= new usr(req.getParameter("email"),req.getParameter("password"),Urole,req.getParameter("Id_new"));
        int flag=adservice.addUsr(user);
        if(flag!=0){
            resp.getWriter().println("<script>alert('添加成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('添加失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findAllUsr'</script>");
    }
    protected void deleteUsr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int flag=adservice.deleteUsr(req.getParameter("id"));
        if(flag!=0){
            resp.getWriter().println("<script>alert('删除成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('删除失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findAllUsr'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findAllUsr");
    }
    //班级管理模块
    protected void addClassByCLcode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CLcode=req.getParameter("Clcode");
        String CLname=req.getParameter("Clname");
        String Scode=req.getParameter("Scode");
        classes cla=new classes(CLcode,CLname,Scode);
        int flag=adservice.addClass(cla);
        if(flag!=0){
            resp.getWriter().println("<script>alert('添加成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('添加失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findClassByScode&nature=3&Scode="+req.getParameter("Scode")+"'</script>");
    }
    protected void updateClassByCLcode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CLname=req.getParameter("Clname");
        String Scode=req.getParameter("Scode");
        int flag=adservice.updateClassByCLcode(new classes(req.getParameter("Clcode"),CLname,Scode),req.getParameter("preClcode"));
        if(flag!=0){
            resp.getWriter().println("<script>alert('修改成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('修改失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findClassByScode&nature=3&Scode="+req.getParameter("Scode")+"'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findClassByScode&nature=3&Scode="+req.getParameter("Scode"));
    }
    protected void deleteClassByCLcode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int flag=adservice.deleteClassByCLcode(req.getParameter("CLcode"));
        if(flag!=0){
            resp.getWriter().println("<script>alert('删除成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('删除失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findClassByScode&nature=3&Scode="+req.getParameter("Scode")+"'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findClassByScode&nature=3&Scode="+req.getParameter("Scode"));
    }
    protected void findClassByCLcode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        classes cla =adservice.findClassByCLcode(req.getParameter("CLcode"));
        req.setAttribute("cla",cla);
//        System.out.println(cla);
        req.getRequestDispatcher("/admin/class_update.jsp").forward(req,resp);
    }
    //专业管理模块
    protected void findSingleSpecialityByScode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        speciality spe=adservice.findSingleSpecialityByScode(req.getParameter("Scode"));
        req.setAttribute("spe",spe);
        req.getRequestDispatcher("/admin/speciality_update.jsp").forward(req,resp);
    }
    protected void  deleteSpecByScode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int flag=adservice.deleteSpecByScode(req.getParameter("Scode"));
        if(flag!=0){
            resp.getWriter().println("<script>alert('删除成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('删除失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findSpecialityByCocode&nature=4&Cocode="+req.getParameter("Cocode")+"'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findSpecialityByCocode&nature=4&Cocode="+req.getParameter("Cocode"));
    }
    protected void  updateSpecByScode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String preScode=req.getParameter("preScode");
        speciality spe=new speciality(req.getParameter("Scode"),req.getParameter("Sname"),req.getParameter("Cocode"));
        int flag=adservice.updateSpec(spe,preScode);
        if(flag!=0){
            resp.getWriter().println("<script>alert('修改成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('修改失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findSpecialityByCocode&nature=4&Cocode="+req.getParameter("Cocode")+"'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findSpecialityByCocode&nature=4&Cocode="+req.getParameter("Cocode"));
    }
    protected void  addSpecByScode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        speciality spe=new speciality(req.getParameter("Scode"),req.getParameter("Sname"),req.getParameter("Cocode"));
        int flag=adservice.addSpeciality(spe);
        if(flag!=0){
            resp.getWriter().println("<script>alert('添加成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('添加失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findSpecialityByCocode&nature=4&Cocode="+req.getParameter("Cocode")+"'</script>");
    }
    //课程管理模块
    protected void findAllCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<course> courses=adservice.findAllCourse();
        req.setAttribute("courses",courses);
        req.getRequestDispatcher("/admin/main5.jsp").forward(req,resp);
    }
    protected void addCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Cno = req.getParameter("Cno");
        String Scode=req.getParameter("Scode");
        String Spname=req.getParameter("Spname");
        String Cname=req.getParameter("Cname");
        String Cproperty=req.getParameter("Cproperty");
        int Credit=WebUtils.parseInt(req.getParameter("Credit"),1);
        int Term=WebUtils.parseInt(req.getParameter("Term"),1);
        int Syear=WebUtils.parseInt(req.getParameter("Syear"),1);
        course cou=new course(Cno,Scode,Cname,Spname,Cproperty,Credit,Term,Syear);
        System.out.println(cou);
        int flag=adservice.addCourse(cou);
        System.out.println(cou);
        if(flag!=0){
            resp.getWriter().println("<script>alert('添加成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('添加失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findAllCourse'</script>");
    }
    protected void findEduByCno(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<edu> edus=adservice.findEduByCno(req.getParameter("Cno"));
        req.setAttribute("edus",edus);
        req.setAttribute("Cno",req.getParameter("Cno"));
        req.setAttribute("Cname",req.getParameter("Cname"));
        req.getRequestDispatcher("/admin/edu.jsp").forward(req,resp);
    }
    protected void findEduByCnoTnoCLcode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        edu edu=adservice.findSingleEdu(req.getParameter("Cno"),req.getParameter("Tno"),req.getParameter("Clcode"));
//        System.out.println(edu);
        req.setAttribute("edu",edu);
        req.getRequestDispatcher("/admin/edu_update.jsp").forward(req,resp);
    }
    protected void updateEdu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        edu edupre=new edu(req.getParameter("preTno"),null,req.getParameter("Cno"),null,req.getParameter("preCLcode"),null);
        String tname=adservice.findTnameByTno(req.getParameter("Tno"));
        String Clcode=adservice.findCLcodeByCLname(req.getParameter("CLname"));
        edu edu=new edu(req.getParameter("Tno"),tname,req.getParameter("Cno"),req.getParameter("Cname"),Clcode,req.getParameter("CLname"));
//        System.out.println(edupre);
//        System.out.println(edu);
        int flag=adservice.updateEdu(edu,edupre);
        if(flag!=0){
            resp.getWriter().println("<script>alert('修改成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('修改失败')</script>");
        }
        req.setAttribute("Cname",req.getParameter("Cname"));
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findEduByCno&Cno="+req.getParameter("Cno")+"'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findEduByCno&Cno="+req.getParameter("Cno"));
    }
    protected void addEdu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        edu edu=new edu(req.getParameter("Tno"),req.getParameter("Tname"),req.getParameter("Cno_new"),null,null,req.getParameter("CLname"));
        System.out.println(edu);
        int flag=adservice.addEdu(edu);
        System.out.println(edu);
        if(flag!=0){
            resp.getWriter().println("<script>alert('添加成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('添加失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findEduByCno&Cno="+req.getParameter("Cno_new")+"'</script>");
    }
    protected void deleteEdu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        edu edu=new edu(req.getParameter("Tno"),null,req.getParameter("Cno"),null,req.getParameter("Clcode"),null);
        int flag=adservice.deleteEdu(edu);
        if(flag!=0){
            resp.getWriter().println("<script>alert('删除成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('删除失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findEduByCno&Cno="+req.getParameter("Cno")+"'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findEduByCno&Cno="+req.getParameter("Cno"));
    }
    //学院管理模块
    protected void findCollegeByCocode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        college college=adservice.findCollegeByCocode(req.getParameter("Cocode"));
        req.setAttribute("college",college);
        req.getRequestDispatcher("/admin/college_update.jsp").forward(req,resp);
    }
    protected void updateCollegeByCocode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        college col=new college(req.getParameter("Cocode"),req.getParameter("Coname"));
        int flag=adservice.updateCollegeByCocode(col,req.getParameter("preCocode"));
        if(flag!=0){
            resp.getWriter().println("<script>alert('修改成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('修改失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findAllCollege&nature=6'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findAllCollege&nature=6");
    }
    protected void addCollegeByCocode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        college col=new college(req.getParameter("Cocode"),req.getParameter("Coname"));
        int flag=adservice.addCollege(col);
        if(flag!=0){
            resp.getWriter().println("<script>alert('添加成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('添加失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findAllCollege&nature=6'</script>");
    }
    protected void deleteCollegeByCocode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int flag=adservice.deleteCollegeByCocode(req.getParameter("Cocode"));
        if(flag!=0){
            resp.getWriter().println("<script>alert('删除成功')</script>");
        }
        else{
            resp.getWriter().println("<script>alert('删除失败')</script>");
        }
        resp.getWriter().println("<script>window.location.href='adminServlet?action=findAllCollege&nature=6'</script>");
//        resp.sendRedirect(req.getContextPath()+"//adminServlet?action=findAllCollege&nature=6");
    }
}
