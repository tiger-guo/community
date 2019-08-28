package life.majiang.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/28 23:27
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
