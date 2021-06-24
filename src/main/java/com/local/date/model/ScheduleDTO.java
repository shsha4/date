package com.local.date.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleDTO {
    int idx;
    String date;
    String place;
    double date_lat;
    double date_lon;
    String memo;
}
