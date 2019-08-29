package life.majiang.community.exception;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/23 21:36
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在，请重新尝试！"),
    QUESTION_NOT_UPDATE(2000,"更新失败，请重新尝试！"),
    TARGET_PARAM_NOT_FOUND(2002,"为选择任何评论进行回复！"),
    NO_LOGIN(2003, "未登录不能进行评论，请先登陆"),
    TYPE_PARAM_WRONG(2004, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2005, "你操作的评论不存在" ),
    SYS_ERROR(2006,"服务器有问题，请稍后重试"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FATL(2008,"兄弟你想干啥！！！！"),
    NOTIFICANTION_NOT_FOUND(2009,"这条消息大概是丢了！"),
    FILE_UPLOAD_FAIL(2010,"图片上传失败！");

    private String message;
    private Integer code;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code = code;
    }


}
