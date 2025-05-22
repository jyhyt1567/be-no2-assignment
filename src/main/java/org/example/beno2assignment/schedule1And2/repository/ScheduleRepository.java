package org.example.beno2assignment.schedule1And2.repository;

import org.example.beno2assignment.schedule1And2.dto.ReadScheduleRequestDto;
import org.example.beno2assignment.schedule1And2.dto.UpdateScheduleRequestDto;
import org.example.beno2assignment.schedule1And2.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    Schedule createSchedule(Schedule schedule);
    List<Schedule> findSchedulesByConditions(ReadScheduleRequestDto requestDto);
    Schedule findScheduleByIdorElseThrow(Long id);
    int updateSchedule(Long id, UpdateScheduleRequestDto requestDto);
    int deleteSchedule(Long id);

}
