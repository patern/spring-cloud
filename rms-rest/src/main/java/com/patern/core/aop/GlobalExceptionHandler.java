package com.patern.core.aop;

import com.patern.exception.RmsException;
import com.patern.result.ResultBody;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * Created by patern on 2017/8/30.
 */
//表示 GlobalExceptionHandler 是一个全局的异常处理器.
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Log log = LogFactory.get();

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handle(Exception e) {
        if (e instanceof RmsException) {
            RmsException rmsException = (RmsException) e;
            return ResultBody.error(rmsException.getResponseInfo());
        } else {
            log.error("【系统异常】{}", e);
            return ResultBody.error("【系统异常】" + e.getMessage());
        }
    }

//    /**
//     * 拦截业务异常
//     */
//    @ExceptionHandler(RmsException.class)
//    @ResponseBody
//    public ResultBody notFount(RmsException e) {
//        return ResultBody.error(e.getResponseInfo());
//    }
}
