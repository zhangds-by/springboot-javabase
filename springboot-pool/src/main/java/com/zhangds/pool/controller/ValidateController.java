package com.zhangds.pool.controller;

import com.zhangds.pool.common.Result;
import com.zhangds.pool.validate.dto.CustomDTO;
import com.zhangds.pool.validate.dto.GroupCardDTO;
import com.zhangds.pool.validate.group.Insert;
import com.zhangds.pool.validate.util.ValidateUtil;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("validate")
public class ValidateController {

    @PostMapping("/insert")
    public Result insert(@RequestBody @Validated(Insert.class) GroupCardDTO card){
        return Result.success(card);
    }

    @PostMapping("/validateType")
    public Result validateType(@RequestBody @Validated CustomDTO customDTO){
        return Result.success(customDTO);
    }
}
