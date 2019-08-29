package life.majiang.community.dto;

import lombok.Data;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/29 22:14
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
