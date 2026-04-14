package com.fast.springboot.domain;
import java.util.HashMap;

/**
 * 操作消息提醒
 */
public class AjaxResult extends HashMap<String, Object> {
    /** 成功状态码 */
    public static final int SUCCESS = 200;
    /** 错误状态码 */
    public static final int ERROR = 500;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息
     */
    public AjaxResult() {

    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * @param code 状态码
     * @param msg 返回消息
     */
    public AjaxResult(int code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     * @param code 状态码
     * @param msg 返回消息
     * @param data 数据对象
     */
    public AjaxResult(int code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        if (data != null) {
            super.put("data", data);
        }
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(SUCCESS, msg, data);
    }

    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回错误消息
     * @param code 状态码
     * @param msg 返回内容
     * @return 错误消息
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }
    /**
     * 返回错误消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(ERROR, msg, data);
    }
    /**
     * 返回错误消息
     * @return 错误消息
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }
    /**
     * 返回错误消息
     * @param msg 返回内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(ERROR, msg);
    }
    /**
     * 获取状态码
     * @return 状态码
     */
    public Integer getCode() {
        return (Integer) super.get("code");
    }
    /**
     * 获取消息
     * @return 消息
     */
    public String getMsg() {
        return (String) super.get("msg");
    }
    /**
     * 获取数据
     * @return 数据
     */
    public Object getData() {
        return super.get("data");
    }
    /**
     * 方便链式调用
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}