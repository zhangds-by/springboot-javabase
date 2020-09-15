package com.zhangds.pool.validate.anno;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private CaseMode caseMode;

    /**
     * 获取注解中的属性
     * @param constraintAnnotation
     */
    @Override
    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    /**
     * 自定义校验逻辑
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (null == value || "".equals(value.trim())){
            return false;
        }

        switch (this.caseMode){
            case LOWER:
                return value.equals(value.toLowerCase());
            case UPPER:
                return value.equals(value.toUpperCase());
            default:
                return false;
        }
    }
}
