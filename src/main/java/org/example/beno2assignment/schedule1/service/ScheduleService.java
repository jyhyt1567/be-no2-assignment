package org.example.beno2assignment.schedule1.service;

import org.example.beno2assignment.schedule1.dto.CreateScheduleRequestDto;
import org.example.beno2assignment.schedule1.dto.ReadScheduleRequestDto;
import org.example.beno2assignment.schedule1.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto);
    List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto);
    ScheduleResponseDto findScheduleById(Long id);
}
