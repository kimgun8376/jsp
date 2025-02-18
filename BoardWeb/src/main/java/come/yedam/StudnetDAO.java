package come.yedam;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class StudnetDAO extends DAO {
	private int empNo;

	public boolean addStudnet(Student student) {
		String sql = "insert into tbl_student (student_no, student_naem, phone, address)"
				+ "values(?, ?, ?, ?)";
		
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt = setString(1, student.getStudentNo());
			psmt = setString(2, student.getStudentName());
			psmt = setString(3, student.getPhone());
			psmt = setString(4, student.getAddress());

			//퀴리실행
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // 등록실패
	}
	
	private PreparedStatement setString(int i, Object studentNo) {
		return null;
	}
	
	// 학생목록을 반환 메소드.참고)EmpDAO.search
	public List<Student> studentList() {
		String query = "select * from tbl_employees " + "where emp_no = ?";
		try {
			/*psmt = getConnect().prepareStatement(query);
			psmt =  */
						
			PreparedStatement stmt = getConnect().prepareStatement(query);
			stmt.setInt(1, empNo);
             
			ResultSet rs = stmt.executeQuery(); // 조회.
			if (rs.next()) { // 조회결과가 한건 있으면..
				Employee emp = new Employee();
				emp.setEmpNo(rs.getInt("emp_no")); // 칼럼값.
				emp.setEmpName(rs.getString("emp_name"));
				emp.setTelNo(rs.getString("tel_no"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getInt("salary"));

				return (List<Student>) emp; // 반환.
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 조회된 결과 없음(null)
	}
	// 등록.
		public boolean registerEmp(Employee emp) { // 등록.
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String query = "insert into tbl_employees ";
			query += "values (" + emp.getEmpNo() //
					+ ", '" + emp.getEmpName() //
					+ "', '" + emp.getTelNo() //
					+ "', '" + sdf.format(emp.getHireDate()) //
					+ "', " + emp.getSalary()//
					+ ")";
			try {
				Statement stmt = getConnect().createStatement();
				int r = stmt.executeUpdate(query);
				if (r > 0) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		} // end of registerEmp().
		
		// 목록.
		public List<Employee> search(Employee emp) {
			List<Employee> empList = new ArrayList<>();
			String qry = "select * from tbl_employees " //
//					+ "where emp_name = nvl('" + emp.getEmpNo() + "', emp_name) " //
					+ "where emp_name = nvl(?, emp_name)" // number, varchar2 에 따라 처리.
					+ "order by emp_no";

			try {
//				Statement stmt = getConnect().createStatement();
				PreparedStatement stmt = getConnect().prepareStatement(qry);
				stmt.setString(1, emp.getEmpName()); // 첫번째 ?에 사원이름을 대입.
				ResultSet rs = stmt.executeQuery();
				// 조회결과.
				while (rs.next()) {
					Employee empl = new Employee();
					empl.setEmpNo(rs.getInt("emp_no"));
					empl.setEmpName(rs.getString("emp_name"));
					empl.setHireDate(rs.getDate("hire_date"));
					empl.setSalary(rs.getInt("salary"));
					empl.setTelNo(rs.getString("tel_no"));

					empList.add(empl);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return empList;
		}

		public boolean addStudent(Student std) {
			// TODO Auto-generated method stub
			return false;
		}

}