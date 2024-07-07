package utils;

public class R<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    //服务器成功返回用户的请求数据
    public static R ok(Object data){
        R r = new R();
        r.setCode(200);
        r.setMessage("ok");
        r.setData(data);
        return r;
    }
    public static R ok(String message){
        R r = new R();
        r.setCode(200);
        r.setMessage(message);
        return r;
    }
    //服务器返回错误消息的方法
    public static R error(String message){
        R r = new R();
        r.setCode(400);
        r.setMessage(message);
        return r;
    }
}
