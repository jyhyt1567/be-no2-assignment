package org.example.beno2assignment.schedule1.service;

import org.example.beno2assignment.schedule1.dto.CreateScheduleRequestDto;
import org.example.beno2assignment.schedule1.dto.ReadScheduleRequestDto;
import org.example.beno2assignment.schedule1.dto.ScheduleResponseDto;
import org.example.beno2assignment.schedule1.entity.Schedule;
import org.example.beno2assignment.schedule1.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getName(), requestDto.getTodo(), requestDto.getPassword());
        return scheduleRepository.createSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto) {
        return scheduleRepository.findSchedulesByConditions(requestDto);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleByIdorElseThrow(id);
    }
}
