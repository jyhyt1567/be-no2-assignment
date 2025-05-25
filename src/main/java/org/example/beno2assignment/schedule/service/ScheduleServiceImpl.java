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

    @Override
    public ScheduleResponseDto createSchedule(CreateScheduleRequestDto requestDto) {

        Schedule schedule = null;
        Long uid = requestDto.getUid();
        if(uid == null){
            schedule = new Schedule(requestDto.getName(), requestDto.getTodo(), requestDto.getPassword(), null);
        }
        else{

            User user = userRepository.findUserByUidorElseThrow(uid);

            String name = requestDto.getName();

            String password = user.getPassword();
            String inputPassword = requestDto.getPassword();

            if(!password.equals(inputPassword)){
                throw new CustomException(ErrorCode.UNAUTHORIZED);
            }

            if(name == null){
                name = user.getName();
            }

            schedule = new Schedule(name, requestDto.getTodo(), password, uid);

        }
        return scheduleRepository.createSchedule(schedule).toResponseDto();
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto) {

        if(requestDto.getP() < 1 || requestDto.getPSize() < 1){
            return new ArrayList<ScheduleResponseDto>();
        }

        return scheduleRepository.findSchedulesByConditions(requestDto)
                .stream()
                .map(schedule->schedule.toResponseDto())
                .toList();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleByIdorElseThrow(id).toResponseDto();
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto) {

        String password = scheduleRepository.findScheduleByIdorElseThrow(id).getPassword();
        String inputPassword = requestDto.getPassword();

        if(!password.equals(inputPassword)){
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        if(requestDto.getName() == null && requestDto.getTodo() == null){
            throw new CustomException(ErrorCode.LACK_OF_REQUEST);
        }

        int updatedRow = scheduleRepository.updateSchedule(id, requestDto);

        if (updatedRow == 0) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        return scheduleRepository.findScheduleByIdorElseThrow(id).toResponseDto();
    }

    @Override
    public void deleteSchedule(Long id, DeleteScheduleRequestDto requestDto) {
        String password = scheduleRepository.findScheduleByIdorElseThrow(id).getPassword();
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
