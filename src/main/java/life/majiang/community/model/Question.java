package life.majiang.community.model;

import lombok.Data;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/16 23:01
 */

@Data
public class Question {
    Integer id;
    String title;
    String description;
    Long gmtCreate;
    Long gmtModified;
    int creator;
    String commentCount;
    String viewCount;
    String likeCount;
    String tag;
}
