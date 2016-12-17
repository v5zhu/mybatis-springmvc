package com.v5zhu.distribute.controller;

import com.alibaba.fastjson.JSONObject;
import com.v5zhu.distribute.api.UserService;
import com.v5zhu.distribute.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody UserDto userDto,HttpServletRequest request) {
        int isok=userService.login(userDto);
        if(isok==1){
            UserDto userDto1= (UserDto) request.getSession().getAttribute("user");
            request.getSession().setAttribute("user",userDto);
        }else{
            return "error";
        }
        return "index";
    }

}
