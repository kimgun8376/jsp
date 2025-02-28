package come.yedam;

import java.text.SimpleDateFormat;
import java.util.Date;

public class STest {
    public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("Mon dd, yyyy, hh:mm:ss ");
    	System.out.println(sdf.format(new Date()));
	}
}
