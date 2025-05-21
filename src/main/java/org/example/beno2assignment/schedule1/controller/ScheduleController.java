package org.example.beno2assignment.schedule1.controller;

import org.example.beno2assignment.schedule1.dto.CreateScheduleRequestDto;
import org.example.beno2assignment.schedule1.dto.ReadScheduleRequestDto;
import org.example.beno2assignment.schedule1.dto.ScheduleResponseDto;
import org.example.beno2assignment.schedule1.service.ScheduleService;
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
                                                                               @RequestParam(required = false) LocalDate modifiedAt){
        ReadScheduleRequestDto requestDto = new ReadScheduleRequestDto(name, modifiedAt);
        return new ResponseEntity<>(scheduleService.findSchedulesByConditions(requestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }
}
