package com.local.date.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class DateController {

    @GetMapping({"/", "index"})
    public String index() {
        return "views/index";
    }

    @GetMapping("insert")
    public String insert(@RequestParam("getDate") String date) {
        log.info(date);
        return "views/insert";
    }
}