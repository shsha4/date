package com.local.date.service;

import com.local.date.dao.DateDAO;
import com.local.date.model.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class DateService {

    private final DateDAO dao;

    public List<HashMap<String, Object>> getCategoryMaster() {
        List<HashMap<String, Object>> getData = dao.getCategoryMaster();
        return getData;
    }

    @Transactional
    public boolean insertSchedule(ScheduleDTO dto) {
        boolean result = dao.insertSchedule(dto);
        if(result) {
            dto.setIdx(dao.getIndexSchedule(dto));
        }
        if(dto.getIdx() > 0 && dto.getImgs() != null) {
            result = dao.insertPictures(dto);
        }
        if(result && dto.getCategories() != null) {
            result = dao.insertCategories(dto);
        }
        return result;
    }

    public List<ScheduleDTO> getDateList(String target_date) {
        List<ScheduleDTO> dto = dao.getDateList(target_date);
        return dto;
    }

    public Optional<ScheduleDTO> getSchedule(String target_date) {
        Optional<ScheduleDTO> schedule = Optional.ofNullable(dao.getSchedule(target_date));
        if (schedule.isPresent()) {
            schedule.get().setImgs(dao.getPictures(schedule.get().getIdx()));
            schedule.get().setCategories(dao.getCategory(schedule.get().getIdx()));
        }
        return schedule;
    }

    public boolean delPicture(String picture) {
        boolean result = dao.delPicture(picture);
        return result;
    }

    public List<ScheduleDTO> getScheduleList() {
        Optional<List<ScheduleDTO>> list = Optional.ofNullable(dao.getScheduleList());
        return list.orElseGet(null);
    }

    public boolean delSchedule(int idx) {
        return dao.delSchedule(idx);
    }
}
