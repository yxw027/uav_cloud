package com.ccssoft.cloudmessagemachine.controller;


import com.ccssoft.cloudmessagemachine.service.BoxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @author moriarty
 * @date 2020/5/14 14:27
 */
@RequestMapping("box/")
@Controller
public class InstructionController {
    @Resource
    private BoxService boxService;

    @ResponseBody
    @GetMapping("/restart/{id}")
    public String boxRestart (@PathVariable("id") Long id) {
        return boxService.restartBox(id);
    }

    @GetMapping("/map")
    public String mapp() {
        return "mapAndPlane";
    }
}
