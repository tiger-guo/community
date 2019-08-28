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
    Long id;
    String title;
    String description;
    Long gmtCreate;
    Long gmtModified;
    Long creator;
    Integer commentCount;
    Integer viewCount;
    Integer likeCount;
    String tag;
    User user;
}
