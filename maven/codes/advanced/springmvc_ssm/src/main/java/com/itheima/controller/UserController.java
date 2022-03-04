package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.controller.results.Code;
import com.itheima.controller.results.Result;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.system.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result save(User user){
        boolean flag = userService.save(user);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERROR);
    }

    @PutMapping
    public Result update(User user){
        boolean flag = userService.update(user);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERROR);
    }

    @DeleteMapping("/{uuid}")
    public Result delete(@PathVariable Integer uuid){
        boolean flag = userService.delete(uuid);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERROR);
    }

    @GetMapping("/{uuid}")
    public Result get(@PathVariable Integer uuid){
        User user = userService.get(uuid);
        //模拟出现异常，使用条件控制，便于测试结果
        if (uuid == 10 ) throw new BusinessException("查询出错啦，请重试！",Code.GET_ERROR);
        return new Result(null != user ?Code.GET_OK: Code.GET_ERROR,user);
    }

    @GetMapping("/{page}/{size}")
    public Result getAll(@PathVariable Integer page, @PathVariable Integer size){
        PageInfo<User> all = userService.getAll(page, size);
        return new Result(null != all ?Code.GET_OK: Code.GET_ERROR,all);
    }

    @PostMapping("/login")
    public Result login(String userName,String password){
        User user = userService.login(userName,password);
        return new Result(null != user ?Code.GET_OK: Code.GET_ERROR,user);
    }

}
