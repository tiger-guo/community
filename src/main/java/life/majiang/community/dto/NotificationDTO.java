package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/28 14:54
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User notifier;
    private String outerTitle;
    private Long outerId;
    private String type;
}
