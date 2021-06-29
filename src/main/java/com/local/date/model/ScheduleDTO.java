package com.local.date.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private int idx;
    private String date;
    private String place;
    private double date_lat;
    private double date_lon;
    private String memo;
    private List<String> imgs;
    private List<String> categories;
}
