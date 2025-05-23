package org.example.beno2assignment.schedule.service;

import org.example.beno2assignment.schedule.dto.*;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto);
    List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto);
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto);
    void deleteSchedule(Long id, DeleteScheduleRequestDto requestDto);
}
