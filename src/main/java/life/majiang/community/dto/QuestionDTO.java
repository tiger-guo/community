package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/17 22:39
 */
@Data
public class QuestionDTO {
    int id;
    String title;
    String description;
    Long gmtCreate;
    Long gmtModified;
    int creator;
    String commentCount;
    String viewCount;
    String likeCount;
    String tag;
    User user;
}
