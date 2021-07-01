package com.local.date.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.date.model.ScheduleDTO;
import com.local.date.service.DateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Log4j2
public class DateController {

    private final DateService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping({"/", "index"})
    public String index() {
        return "views/index";
    }

    @GetMapping("insert")
    public String insert(@RequestParam("getDate") String date, Model model, RedirectAttributes attributes) {
        List<HashMap<String, Object>> categoryList = service.getCategoryMaster();
        model.addAttribute("getDate", date);
        model.addAttribute("categoryList", categoryList);

        Optional<ScheduleDTO> schedule = service.getSchedule(date);
        if(schedule.isPresent()) {
            attributes.addAttribute("date", schedule.get().getDate());
            return "redirect:detail";
        } else {
            return "views/insert";
        }
    }

    @GetMapping("detail")
    public String detail(Model model, @RequestParam("date") String target_date) {
        Optional<ScheduleDTO> schedule = service.getSchedule(target_date);
        model.addAttribute("schedule", schedule.orElseGet(null));
        return "views/detail";
    }

    @GetMapping("nationMap")
    public String nationMap() {
        return "views/nationMap";
    }
}