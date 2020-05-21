package com.ccssoft.cloudadmin.controller;

import com.ccssoft.cloudadmin.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author moriarty
 * @date 2020/5/20 16:12
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @GetMapping("/see")
    public String registerUser( ){
        return "see";
    }
}
