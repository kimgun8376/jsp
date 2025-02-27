package come.yedam.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchVO {
     private int page;
     private String searchCondition;
     private String keyword;
 
}
