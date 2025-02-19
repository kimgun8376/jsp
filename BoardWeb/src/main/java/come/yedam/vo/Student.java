package come.yedam.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok의 @Data 어노테이션을 사용하여 getter, setter, toString 등을 자동으로 생성
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String studentNo; // 학생 번호
    private String studentName; // 학생 이름
    private String phone; // 전화번호
    private String address; // 주소
}
