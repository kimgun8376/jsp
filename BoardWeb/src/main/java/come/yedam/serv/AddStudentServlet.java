package come.yedam.serv;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import come.yedam.dao.StudnetDAO;
import come.yedam.vo.Student;

//Service()기능구현 
@WebServlet("/addStudentServ")
public class AddStudentServlet extends HttpServlet {
    
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        
        // 파라미터 값 받아오기
        String sno = req.getParameter("std_no");
        String sname = req.getParameter("std_name");
        String tel = req.getParameter("tel_no");
        String addr = req.getParameter("std_addr");
        
        // Student 객체 생성
        Student std = new Student();
        std.setStudentNo(sno);
        std.setStudentName(sname);  // 수정된 부분
        std.setPhone(tel);  // 수정된 부분
        std.setAddress(addr);  // 수정된 부분

        // StudentDAO 객체 생성
        StudnetDAO sdao = new StudnetDAO();  // 수정된 부분
        if (sdao.addStudent(std)) {  // 메소드 이름 수정
            resp.getWriter().print("처리 성공");
        } else {
            resp.getWriter().print("처리 실패");
        }
    }
}