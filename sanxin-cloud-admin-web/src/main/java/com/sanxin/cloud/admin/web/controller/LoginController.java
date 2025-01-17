package com.sanxin.cloud.admin.web.controller;

import com.sanxin.cloud.admin.api.service.LoginService;
import com.sanxin.cloud.common.rest.RestResult;
import com.sanxin.cloud.config.redis.RedisUtilsService;
import com.sanxin.cloud.entity.SysConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author arno
 * @version 1.0
 * @date 2019-08-22
 */
@RestController
public class LoginController {
    protected final static Logger logger = LoggerFactory.getLogger(RedisUtilsService.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisUtilsService redisUtilsService;

    @PostMapping("/loginOut")
    public RestResult loginOut(){
        logger.info("输出sss");
        redisUtilsService.setKey("aaa","哈哈哈哈");
        System.out.print(redisUtilsService.getKey("aaa"));
        List<SysConfig> list=loginService.queryList();
        RestResult restResult=RestResult.success("退出成功",list);
        return  restResult;
    }
}
