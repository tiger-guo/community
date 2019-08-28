package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/24 0:21
 */
@Data
public class CommentBackDTO implements Serializable {
    private Long id;
    private Long parentId;
    private Integer type;
    private Integer commentCount;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
