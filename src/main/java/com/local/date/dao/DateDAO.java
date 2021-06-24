package com.local.date.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface DateDAO {
    List<HashMap<String, Object>> getCategoryMaster();
}
