package com.local.date.dao;

import com.local.date.model.ScheduleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface DateDAO {
    List<HashMap<String, Object>> getCategoryMaster();
    boolean insertSchedule(ScheduleDTO dto);
    boolean insertPictures(ScheduleDTO dto);
    boolean insertCategories(ScheduleDTO dto);
    int getIndexSchedule(ScheduleDTO dto);
    List<ScheduleDTO> getDateList(String target_date);
    ScheduleDTO getSchedule(String target_date);
    List<String> getCategory(int idx);
    List<String> getPictures(int idx);
    boolean delPicture(String picture);
}
