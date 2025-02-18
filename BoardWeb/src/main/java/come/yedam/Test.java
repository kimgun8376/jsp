package come.yedam;

import java.util.List;

public class Test {
    public static void main(String[] args) {
    	
		StudnetDAO sdao = new StudnetDAO();
		List<Student> list = sdao.studentList();
		for (Student std : list) {
			System.out.println(std.toString());
		}
	}
}
