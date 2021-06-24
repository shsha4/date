package com.local.date.service;

import com.local.date.dao.DateDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class DateService {

    private final DateDAO dao;

    public List<HashMap<String, Object>> getCategoryMaster() {
        List<HashMap<String, Object>> getData = dao.getCategoryMaster();
        return getData;
    }
}
