package com.local.date.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.local.date.model.ScheduleDTO;
import com.local.date.service.DateService;
import com.local.date.util.UploadFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class DateRestController {
    private final DateService service;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("insert.do")
    public ResponseEntity<Boolean> insertAction(@ModelAttribute ScheduleDTO dto, MultipartFile[] files) {
        if (files != null) {
            List<String> imgs = new ArrayList<>();
            Arrays.stream(files).forEach(file -> {
                String uuid = UUID.randomUUID().toString().replace("-", "");
                String fileDate = dto.getDate().replace("-", "");
                String originalFileName = fileDate + "_" + uuid + ".jpeg";
                imgs.add(originalFileName);

                try {
                    UploadFileUtils.uploadFile(uploadPath, originalFileName, file.getBytes());
                } catch (Exception e) {
                    log.error(e);
                }
            });
            dto.setImgs(imgs);
        }

        boolean result = service.insertSchedule(dto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("dateList.do")
    public ResponseEntity<String> dateListAction(@RequestParam("target_date") String target_date) {
        List<ScheduleDTO> dateList = service.getDateList(target_date);
        String dateListJson = null;
        try {
            dateListJson = objectMapper.writeValueAsString(dateList);
        } catch (JsonProcessingException e) {
            log.error(e);
        }
        return new ResponseEntity<>(dateListJson, HttpStatus.OK);
    }

    @PostMapping("deletePicture.do")
    public ResponseEntity<Boolean> delPictureAction(@RequestParam("picture") String picture) {
        log.info(picture);
        return new ResponseEntity<>(service.delPicture(picture), HttpStatus.OK);
    }

    @PostMapping("deleteAction.do")
    public ResponseEntity<Boolean> delAction(@RequestParam("idx") int idx) {
        log.info("{}", "Delete : " + idx);
        boolean result = service.delSchedule(idx);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
