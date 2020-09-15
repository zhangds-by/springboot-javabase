package com.zhangds.pool.validate.exception;


import com.zhangds.pool.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.*;
import java.util.Set;

@ControllerAdvice
public class ValidatorGlobalExceptionHandler implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(ValidatorGlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handle(ValidationException exception) {
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                // 打印验证不通过的信息
                logger.error(item.getMessage());
            }
        }
        return Result.fail("校验异常");
    }

}