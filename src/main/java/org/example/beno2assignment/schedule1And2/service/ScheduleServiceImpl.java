package org.example.beno2assignment.schedule1And2.service;

import org.example.beno2assignment.schedule1And2.dto.*;
import org.example.beno2assignment.schedule1And2.entity.Schedule;
import org.example.beno2assignment.schedule1And2.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return scheduleRepository.createSchedule(schedule).toResponseDto();
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto) {
        return scheduleRepository.findSchedulesByConditions(requestDto)
                .stream()
                .map(schedule->schedule.toResponseDto())
                .toList();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleByIdorElseThrow(id).toResponseDto();
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {

        String password = scheduleRepository.findScheduleByIdorElseThrow(id).getPassword();
        String inputPassword = requestDto.getPassword();

        if(!password.equals(inputPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }

        if(requestDto.getName() == null && requestDto.getTodo() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 내용을 기입하시오.");
        }

        int updatedRow = scheduleRepository.updateSchedule(id, requestDto);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "없는 데이터 수정 요청입니다.");
        }

        return scheduleRepository.findScheduleByIdorElseThrow(id).toResponseDto();
    }

    @Override
    public void deleteSchedule(Long id, DeleteScheduleRequestDto requestDto) {
        String password = scheduleRepository.findScheduleByIdorElseThrow(id).getPassword();
        String inputPassword = requestDto.getPassword();
        if(!password.equals(inputPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }

        int deletedRow = scheduleRepository.deleteSchedule(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "없는 데이터 삭제 요청입니다.");
        }
    }
}
