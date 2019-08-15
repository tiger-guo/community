package life.majiang.community.dto;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/15 21:41
 */
public class GithubUser {
    private String name;
    private String bio;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
