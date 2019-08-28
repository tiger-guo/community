package life.majiang.community.exception;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/23 21:24
 */
public class CustomizeException  extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    public CustomizeException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode(){
        return code;
    }
}
