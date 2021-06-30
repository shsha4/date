package com.local.date.service;

import com.local.date.dao.DateDAO;
import com.local.date.model.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
        if(dto.getIdx() > 0 && !dto.getImgs().isEmpty()) {
            result = dao.insertPictures(dto);
        }
        if(result && !dto.getCategories().isEmpty()) {
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
}
