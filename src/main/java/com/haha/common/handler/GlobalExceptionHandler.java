package com.haha.common.handler;

import com.haha.common.domain.JsonResponse;
import com.haha.common.domain.ReturnCode;
import com.haha.common.domain.ReturnMessage;
import com.haha.common.exception.SysInnerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @RestControllerAdvice  同意异常处理 必须引入,也可以写成ControllerAdvice  Advice:通知
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理系统内部异常
     * @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "内部异常")
     * @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
     * @ResponseStatus()
     * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 表示 如果返回默认的http json消息时,带的code 全部都置为500
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResponse handleSysInnerException(Exception exception) {
        // 要不要进行打印?????, 要打印,如果不打印,控制台不会自动打印异常
        log.info("进行统一异常处理 Exception= {}", exception);
        return new JsonResponse().returnCode(ReturnCode.ERROR).message(ReturnMessage.EXCEPTION);
    }

    /**
     * 处理系统内部异常
     * @param sysInnerException
     * @return
     */
    @ExceptionHandler(value = SysInnerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResponse handleSysInnerException(SysInnerException sysInnerException) {
        // 要不要进行打印????? 要打印,如果不打印,控制台不会自动打印异常
        log.info("进行统一异常处理 SysInnerException = {}", sysInnerException);
        String message = sysInnerException.getMessage();
        if (StringUtils.isBlank(message)) {
            message = ReturnMessage.EXCEPTION_SYS_INNER.getMessage();
        }

        return new JsonResponse().returnCode(ReturnCode.ERROR).message(message);
    }
}
