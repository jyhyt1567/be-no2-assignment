package org.example.beno2assignment.schedule1.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.beno2assignment.schedule1.dto.ReadScheduleRequestDto;
import org.example.beno2assignment.schedule1.dto.ScheduleResponseDto;
import org.example.beno2assignment.schedule1.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;
    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto createSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();

        LocalDateTime currentTime = LocalDateTime.now();

        parameters.put("name", schedule.getName());
        parameters.put("todo", schedule.getTodo());
        parameters.put("password",schedule.getPassword());
        parameters.put("createAt", currentTime);
        parameters.put("modifiedAt", currentTime);

        Number id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(id.longValue(), schedule.getTodo(), schedule.getName(), schedule.getCreateAt(), schedule.getModifiedAt());
    }

    @Override
    public List<ScheduleResponseDto> findSchedulesByConditions(ReadScheduleRequestDto requestDto) {

        if (requestDto.getName() != null && requestDto.getModifiedAt() == null){
            return jdbcTemplate.query("select * from schedule where name = ? order by modifiedAt desc", scheduleRowMapper(), requestDto.getName());
        }
        else if (requestDto.getName() == null && requestDto.getModifiedAt() != null){
            return jdbcTemplate.query("select * from schedule where modifiedAt BETWEEN ? and ? order by modifiedAt desc", scheduleRowMapper(), requestDto.getModifiedAt().atStartOfDay(), requestDto.getModifiedAt().atTime(LocalTime.MAX));
        }
        else if (requestDto.getName() != null && requestDto.getModifiedAt() != null){
            return jdbcTemplate.query("select * from schedule where name = ? and modifiedAt BETWEEN ? and ? order by modifiedAt desc", scheduleRowMapper(), requestDto.getName(), requestDto.getModifiedAt().atStartOfDay(), requestDto.getModifiedAt().atTime(LocalTime.MAX));
        }
        else{
            return jdbcTemplate.query("select * from schedule order by modifiedAt desc", scheduleRowMapper());
        }
    }

    @Override
    public ScheduleResponseDto findScheduleByIdorElseThrow(Long id) {
        List<ScheduleResponseDto> res = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(), id);
        return res
                .stream()
                .findAny()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디= "+id+"가 없습니다."));

    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("name"),
                        rs.getObject("createAt", LocalDateTime.class),
                        rs.getObject("modifiedAt", LocalDateTime.class)
                );
            }
        };
    }
}
