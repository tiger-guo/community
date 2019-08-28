package life.majiang.community.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/24 0:21
 */
@Data
public class CommentDTO implements Serializable {

    private Long parentId;
    private String content;
    private Integer type;
}
