package com.fast.springboot.controller;

import com.fast.springboot.domain.AjaxResult;
/**
 * web层通用数据处理
 * 这是所有控制器的基类，其他控制器可以继承它
 * 目的：把常用的方法存这里，避免每个控制器重复写
 */
public class BaseController {
    /**
     * 返回成功(无数据)
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }
    /**
     * 返回错误(无数据)
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }
    /**
     * 返回成功(带消息)
     */
    public AjaxResult success(String msg) {
        return AjaxResult.success(msg);
    }
    /**
     * 返回成功(带数据)
     */
    public AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }
    /**
     * 返回错误(带消息)
     */
    public AjaxResult error(String msg) {
        return AjaxResult.error(msg);
    }
    /**
     * 根据受到影响的行数判断操作是否成功
     * @param rows 数据库操作影响的行数
     * @return 成功或者失败
     * 例子:
     * int rows = userService.deleteUser(id) //删除用户
     * return toAjax(rows) //如果rows > 0代表删除成功, 否则返回失败
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}