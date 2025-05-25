package org.example.beno2assignment.schedule.service;

import org.example.beno2assignment.schedule.dto.*;
import org.example.beno2assignment.schedule.entity.Schedule;
import org.example.beno2assignment.schedule.entity.User;
import org.example.beno2assignment.schedule.exception.CustomException;
import org.example.beno2assignment.schedule.exception.ErrorCode;
import org.example.beno2assignment.schedule.repository.ScheduleRepository;
import org.example.beno2assignment.schedule.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    //레벨 1 스케줄 생성 구현
    @Override
    public ScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto) {

        Schedule schedule = null;
        Long uid = requestDto.getUid();

        User user = userRepository.findUserByUidorElseThrow(uid);
        String password = user.getPassword();

        String inputPassword = requestDto.getPassword();

        if(!password.equals(inputPassword)){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        String name = requestDto.getName();

        if(name == null){
            name = user.getName();
        }

        schedule = new Schedule(name, requestDto.getTodo(), uid);
        return scheduleRepository.createSchedule(schedule).toResponseDto();
    }


    //레벨 1 전체 조회 구현
    @Override
    public List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto) {

        //레벨 4 페이지네이션 구현
        if(requestDto.getP() < 1 || requestDto.getPSize() < 1){
            return new ArrayList<ScheduleResponseDto>();
        }

        return scheduleRepository.findSchedulesByConditions(requestDto)
                .stream()
                .map(schedule->schedule.toResponseDto())
                .toList();
    }


    //레벨 1 단건 조회 구현
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleByIdorElseThrow(id).toResponseDto();
    }

    //레벨 1 단건 수정 구현
    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {

        String password = scheduleRepository.findPasswordByIdorElseThrow(id);
        String inputPassword = requestDto.getPassword();

        if(!password.equals(inputPassword)){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        if(requestDto.getTodo() == null && requestDto.getName() == null){
            throw new CustomException(ErrorCode.LACK_OF_REQUEST);
        }

        int updatedRow = scheduleRepository.updateSchedule(id, requestDto);

        if (updatedRow == 0) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        return scheduleRepository.findScheduleByIdorElseThrow(id).toResponseDto();
    }

    //레벨 2 단건 삭제 구현
    @Override
    public void deleteSchedule(Long id, DeleteScheduleRequestDto requestDto) {

        String password = scheduleRepository.findPasswordByIdorElseThrow(id);
        String inputPassword = requestDto.getPassword();

        if(!password.equals(inputPassword)){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        int deletedRow = scheduleRepository.deleteSchedule(id);

        if (deletedRow == 0) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
    }
}
