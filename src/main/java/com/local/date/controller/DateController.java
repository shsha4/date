package com.local.date.controller;

import com.local.date.service.DateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DateController {

    private final DateService service;

    @GetMapping({"/", "index"})
    public String index() {
        List<HashMap<String, Object>> categoryList = service.getCategoryMaster();
        log.info(String.valueOf(categoryList));
        return "views/index";
    }

    @GetMapping("insert")
    public String insert(@RequestParam("getDate") String date) {
        log.info(date);
        if (date.equals("2021-06-24")) {
            return "views/insert";
        }else {
            return "views/index";
        }
    }
}