package org.example.beno2assignment.schedule.controller;

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

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody CreateScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.createSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findSchedulesByConditions(@RequestParam(required = false) String name,
                                                                               @RequestParam(required = false) LocalDate modifiedAt,
                                                                               @RequestParam(required = false) Long uid,
                                                                               @RequestParam(defaultValue = "1") Long p,
                                                                               @RequestParam(defaultValue = "5") Long pSize){
        ReadScheduleRequestDto requestDto = new ReadScheduleRequestDto(name, modifiedAt, uid, p, pSize);
        return new ResponseEntity<>(scheduleService.findSchedulesByConditions(requestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id,
                                                              @RequestBody UpdateScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.updateSchedule(id, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               @RequestBody DeleteScheduleRequestDto requestDto){
        scheduleService.deleteSchedule(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
