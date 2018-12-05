package com.ideacome.base.res;

import java.io.Serializable;

import com.ideacome.base.enums.BaseErrorEnum;
import com.ideacome.base.enums.ErrorEnum;

/**
 * 对 没错 这个就是国标 建议都用这个
 *
 * <p>
 * 这里我们做个约定 老接口就这样吧，新接口建议：
 * <li>1.只要接口返回用的是Result，那么Result必须不等于null
 * <li>2.只要接口返回用的code 返回10000 那么result.getData 必须不等于null （除非这个接口仅仅用于确认是否成功，无需获取数据）
 * 
 * @param <T>
 * @author 罗成
 */
public class Result<T extends Object> implements Serializable {

    private static final long serialVersionUID = -9147616776579758668L;
    private static final int SUCCESS_CODE = ErrorEnum.success.getKey();
    /** 错误编码 可以参照 {@link ErrorEnum} 里面的枚举，至少成功10000 需要在参照 */
    private int code;
    private String msg;
    private T data;

    public Result() {
        // 无参构造 Result
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> newSuccess() {
        return new Result<T>(SUCCESS_CODE, "Success", null);
    }

    public static <T> Result<T> newSuccess(T data) {
        return new Result<T>(SUCCESS_CODE, "Success", data);
    }

    public static <T> Result<T> newFailure(int errorCode, String errorMsg) {
        return new Result<T>(errorCode, errorMsg, null);
    }

    public static <T> Result<T> newFailure(BaseErrorEnum error) {
        return new Result<T>(error.getKey(), error.getValue(), null);
    }

    public static <T> Result<T> newResult(BaseErrorEnum errorEnum) {
        return new Result<T>(errorEnum.getKey(), errorEnum.getValue(), null);
    }

    public static <T> Result<T> newResult(BaseErrorEnum errorEnum, T data) {
        return new Result<T>(errorEnum.getKey(), errorEnum.getValue(), data);
    }

    public static <T> Result<T> newFailure(String errorMsg) {
        return new Result<T>(ErrorEnum.failure.getErrorCode(), errorMsg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return code == SUCCESS_CODE;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }

}
