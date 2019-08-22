package life.majiang.community.dto;

import lombok.Data;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/15 21:41
 */
@Data
public class GithubUser {
    private String name;
    private String bio;
    private Long id;
    private String avatarUrl;
}
