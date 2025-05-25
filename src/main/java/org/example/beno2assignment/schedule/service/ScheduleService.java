package org.example.beno2assignment.schedule.service;

import org.example.beno2assignment.schedule.dto.*;

import java.util.List;

public interface ScheduleService {

    //레벨 1 스케줄 생성 구현
    ScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto);

    //레벨 1 전체 조회 구현
    List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto);

    //레벨 1 단건 조회 구현
    ScheduleResponseDto findScheduleById(Long id);

    //레벨 2 단건 수정 구현
    ScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto);

    //레벨 2 단건 삭제 구현
    void deleteSchedule(Long id, DeleteScheduleRequestDto requestDto);
}
