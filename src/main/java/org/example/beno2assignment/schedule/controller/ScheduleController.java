package org.example.beno2assignment.schedule.controller;

import jakarta.validation.Valid;
import org.example.beno2assignment.schedule.dto.*;
import org.example.beno2assignment.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }


    //레벨 1 스케줄 생성 구현
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody @Valid CreateScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.createSchedule(requestDto), HttpStatus.CREATED);
    }

    //레벨 1 전체 조회 구현
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findSchedulesByConditions(@RequestParam(required = false) String name,
                                                                               @RequestParam(required = false) LocalDate modifiedAt,
                                                                               //레벨 3 구현
                                                                               @RequestParam(required = false) Long uid,
                                                                               //레벨 4 페이지네이션 구현
                                                                               @RequestParam(defaultValue = "1") Long p,
                                                                               @RequestParam(defaultValue = "5") Long pSize){
        ReadScheduleRequestDto requestDto = new ReadScheduleRequestDto(name, modifiedAt, uid, p, pSize);
        return new ResponseEntity<>(scheduleService.findSchedulesByConditions(requestDto), HttpStatus.OK);
    }

    //레벨 1 단건 조회 구현
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    //레벨 2 단건 수정 구현
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id,
                                                              @RequestBody @Valid UpdateScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.updateSchedule(id, requestDto), HttpStatus.OK);
    }

    //레벨 2 단건 삭제 구현
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               @RequestBody @Valid DeleteScheduleRequestDto requestDto){
        scheduleService.deleteSchedule(id, requestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
