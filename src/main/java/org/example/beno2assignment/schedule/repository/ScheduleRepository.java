package org.example.beno2assignment.schedule.repository;

import org.example.beno2assignment.schedule.dto.ReadScheduleRequestDto;
import org.example.beno2assignment.schedule.dto.UpdateScheduleRequestDto;
import org.example.beno2assignment.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    //레벨 1 스케줄 생성 구현
    Schedule createSchedule(Schedule schedule);

    //레벨 1 전체 조회 구현
    List<Schedule> findSchedulesByConditions(ReadScheduleRequestDto requestDto);

    //레벨 1 단건 조회 구현
    Schedule findScheduleByIdorElseThrow(Long id);

    String findPasswordByIdorElseThrow(Long id);

    //레벨 2 단건 수정 구현
    int updateSchedule(Long id, UpdateScheduleRequestDto requestDto);

    //레벨 2 단건 삭제 구현
    int deleteSchedule(Long id);

}
