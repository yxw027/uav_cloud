package com.ccssoft.cloudmessagemachine.controller;

import com.ccssoft.cloudmessagemachine.service.BoxService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author moriarty
 * @date 2020/5/14 14:27
 */
@RequestMapping("box/")
@RestController
public class InstructionController {
    @Resource
    private BoxService boxService;

    @GetMapping("/restart/{id}")
    public String boxRestart (@PathVariable("id") Long id) {
        return boxService.restartBox(id);
    }
}
