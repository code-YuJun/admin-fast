package com.fast.springboot.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * AjaxResult 测试类
 */
public class AjaxResultTest {

    /**
     * 测试构造函数 - 无参构造
     */
    @Test
    public void testConstructorNoArgs() {
        AjaxResult result = new AjaxResult();
        System.out.println("---------");
        System.out.println(result);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * 测试构造函数 - 带 code 和 msg
     */
    @Test
    public void testConstructorCodeMsg() {
        AjaxResult result = new AjaxResult(200, "操作成功");
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMsg());
        assertNull(result.getData());
    }

    /**
     * 测试构造函数 - 带 code、msg 和 data
     */
    @Test
    public void testConstructorCodeMsgData() {
        String data = "test data";
        AjaxResult result = new AjaxResult(200, "操作成功", data);
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMsg());
        assertEquals(data, result.getData());
    }

    /**
     * 测试构造函数 - data 为 null
     */
    @Test
    public void testConstructorCodeMsgNullData() {
        AjaxResult result = new AjaxResult(200, "操作成功", null);
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMsg());
        assertNull(result.getData());
    }

    /**
     * 测试 success() 无参方法
     */
    @Test
    public void testSuccessNoArgs() {
        AjaxResult result = AjaxResult.success();
        assertNotNull(result);
        assertEquals(AjaxResult.SUCCESS, result.getCode());
        assertEquals("操作成功", result.getMsg());
        assertNull(result.getData());
    }

    /**
     * 测试 success(String msg) 方法
     */
    @Test
    public void testSuccessWithMsg() {
        AjaxResult result = AjaxResult.success("自定义成功消息");
        assertNotNull(result);
        assertEquals(AjaxResult.SUCCESS, result.getCode());
        assertEquals("自定义成功消息", result.getMsg());
        assertNull(result.getData());
    }

    /**
     * 测试 success(Object data) 方法
     */
    @Test
    public void testSuccessWithData() {
        String data = "test data";
        AjaxResult result = AjaxResult.success(data);
        assertNotNull(result);
        assertEquals(AjaxResult.SUCCESS, result.getCode());
        assertEquals("操作成功", result.getMsg());
        assertEquals(data, result.getData());
    }

    /**
     * 测试 success(String msg, Object data) 方法
     */
    @Test
    public void testSuccessWithMsgAndData() {
        String msg = "查询成功";
        Integer data = 123;
        AjaxResult result = AjaxResult.success(msg, data);
        assertNotNull(result);
        assertEquals(AjaxResult.SUCCESS, result.getCode());
        assertEquals(msg, result.getMsg());
        assertEquals(data, result.getData());
    }

    /**
     * 测试 error() 无参方法
     */
    @Test
    public void testErrorNoArgs() {
        AjaxResult result = AjaxResult.error();
        assertNotNull(result);
        assertEquals(AjaxResult.ERROR, result.getCode());
        assertEquals("操作失败", result.getMsg());
        assertNull(result.getData());
    }

    /**
     * 测试 error(String msg) 方法
     */
    @Test
    public void testErrorWithMsg() {
        AjaxResult result = AjaxResult.error("自定义错误消息");
        assertNotNull(result);
        assertEquals(AjaxResult.ERROR, result.getCode());
        assertEquals("自定义错误消息", result.getMsg());
        assertNull(result.getData());
    }

    /**
     * 测试 error(int code, String msg) 方法
     */
    @Test
    public void testErrorWithCodeAndMsg() {
        AjaxResult result = AjaxResult.error(404, "资源未找到");
        assertNotNull(result);
        assertEquals(404, result.getCode());
        assertEquals("资源未找到", result.getMsg());
        assertNull(result.getData());
    }

    /**
     * 测试 error(String msg, Object data) 方法
     */
    @Test
    public void testErrorWithMsgAndData() {
        String msg = "验证失败";
        String data = "字段不能为空";
        AjaxResult result = AjaxResult.error(msg, data);
        assertNotNull(result);
        assertEquals(AjaxResult.ERROR, result.getCode());
        assertEquals(msg, result.getMsg());
        assertEquals(data, result.getData());
    }

    /**
     * 测试 put() 链式调用
     */
    @Test
    public void testPutChaining() {
        AjaxResult result = new AjaxResult()
            .put("code", 200)
            .put("msg", "操作成功")
            .put("extra", "extra data");
        assertNotNull(result);
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMsg());
        assertEquals("extra data", result.get("extra"));
    }

    /**
     * 测试常量值
     */
    @Test
    public void testConstants() {
        assertEquals(200, AjaxResult.SUCCESS);
        assertEquals(500, AjaxResult.ERROR);
    }
}