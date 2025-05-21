package org.example.beno2assignment.schedule1.repository;

import org.example.beno2assignment.schedule1.dto.ReadScheduleRequestDto;
import org.example.beno2assignment.schedule1.dto.ScheduleResponseDto;
import org.example.beno2assignment.schedule1.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto createSchedule(Schedule schedule);
    List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto);
    ScheduleResponseDto findScheduleByIdorElseThrow(Long id);

}
