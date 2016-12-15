package com.v5zhu.dubbo.consumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.v5zhu.dubbo.api.UserService;
import com.v5zhu.dubbo.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuxl@paxsz.com on 2016/7/27.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping(value = "/api/v2/")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity findByLoginName(@RequestParam("loginName") String loginName) {
        UserDto userDto = userService.findByLoginName(loginName);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "user", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        try {
            userService.createUser(userDto);
            JSONObject ok=new JSONObject();
            ok.put("success",true);
            ok.put("msg","新增用户成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        }catch (Exception e){
            JSONObject error=new JSONObject();
            error.put("success",false);
            error.put("msg",e.getMessage());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "user", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity modifyUser(@RequestParam("userId")Long userId,@RequestBody UserDto userDto) {
        try {
            userService.modifyUser(userId,userDto);
            JSONObject ok=new JSONObject();
            ok.put("success",true);
            ok.put("msg","修改用户成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        }catch (Exception e){
            JSONObject error=new JSONObject();
            error.put("success",false);
            error.put("msg",e.getMessage());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "user/{userId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deleteUser(@PathVariable("userId")Long userId) {
        try {
            userService.deleteUser(userId);
            JSONObject ok=new JSONObject();
            ok.put("success",true);
            ok.put("msg","删除用户成功");
            return new ResponseEntity(ok, HttpStatus.OK);
        }catch (Exception e){
            JSONObject error=new JSONObject();
            error.put("success",false);
            error.put("msg",e.getMessage());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
    }
}
